package neetcode150.problems.p87;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 87. Scramble String  [Hard]
 * https://leetcode.com/problems/scramble-string/
 *
 * Problem:
 *   s2 is a scramble of s1 if s1 can be split into two non-empty parts recursively and, at each
 *   split, the two parts may be swapped. Return whether s2 is a scramble of s1.
 *
 * Example:
 *   Input:  s1 = "great", s2 = "rgeat"   Output: true
 *
 * Solution - memoized recursion over (s1, s2) pairs:
 *   Try every split point i. Two ways the split can line up:
 *     - no swap: s1[0..i) ~ s2[0..i)   and s1[i..) ~ s2[i..)
 *     - swapped: s1[0..i) ~ s2[n-i..)  and s1[i..) ~ s2[0..n-i)
 *   Prune early: if the two strings are not anagrams (character counts differ) no split can work.
 *   Memoize on the pair so repeated substrings are solved once.
 *   Time: O(n^4) states*work   Space: O(n^3)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.isScramble("great", "rgeat")); // true
        print(s.isScramble("abcde", "caebd")); // false
        print(new Solution().isScramble("a", "a")); // true
    }

    private final Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        int n = s1.length();
        String key = s1 + "#" + s2;
        Boolean cached = memo.get(key);
        if (cached != null) return cached;

        // Anagram check: a scramble only permutes characters, so counts must match.
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                memo.put(key, false);
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            // Case 1: halves keep their order.
            boolean noSwap = isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i));
            // Case 2: halves are swapped, so the left part of s1 matches the right part of s2.
            boolean swapped = isScramble(s1.substring(0, i), s2.substring(n - i))
                    && isScramble(s1.substring(i), s2.substring(0, n - i));
            if (noSwap || swapped) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }
}
