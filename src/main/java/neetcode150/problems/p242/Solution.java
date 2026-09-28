package neetcode150.problems.p242;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 242. Valid Anagram  [Easy]  -  Arrays & Hashing
 * https://leetcode.com/problems/valid-anagram/
 *
 * Problem:
 *   Is t an anagram of s (same letters with the same counts)?
 *
 * Example:
 *   Input:  s = "anagram", t = "nagaram"
 *   Output: true
 *
 * Solution - one count array:
 *   For each position, count +1 for s's letter and -1 for t's letter. All 26 counts must end at
 *   0. Different lengths -> false immediately.
 *   Time: O(n)   Space: O(1) (26 counters)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isAnagram("anagram", "nagaram")); // true
        print(new Solution().isAnagram("rat", "car")); // false
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] store = new int[26];

        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }

        for (int n : store) if (n != 0) return false;

        return true;
    }
}
