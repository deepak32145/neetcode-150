package neetcode150.problems.p336;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 336. Palindrome Pairs  [Hard]
 * https://leetcode.com/problems/palindrome-pairs/
 *
 * Problem:
 *   Given unique words, return all index pairs (i, j), i != j, where words[i] + words[j] is a
 *   palindrome.
 *
 * Example:
 *   Input:  ["abcd","dcba","lls","s","sssll"]   Output: [[0,1],[1,0],[3,2],[2,4]]
 *
 * Solution - split each word into prefix + suffix and look up the reverse of the other part:
 *   For word w and a split w = left + right:
 *     - if left is a palindrome, then reverse(right) + w  = reverse(right) + left + right is a
 *       palindrome, so any word equal to reverse(right) goes in FRONT:   pair (that, w)
 *     - if right is a palindrome, then w + reverse(left) = left + right + reverse(left) is a
 *       palindrome, so any word equal to reverse(left) goes BEHIND:      pair (w, that)
 *   A HashMap of word -> index makes each lookup O(1). The right-palindrome case skips the split
 *   with an empty right part (j == len) because the left-palindrome case with an empty left part
 *   already produced the same pair from the other word; this also handles the empty-string word.
 *   Time: O(n * L^2)   Space: O(n * L)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.palindromePairs(new String[] {"abcd", "dcba", "lls", "s", "sssll"})); // [[1,0],[0,1],[3,2],[2,4]] (any order)
        print(s.palindromePairs(new String[] {"bat", "tab", "cat"})); // [[1,0],[0,1]]
        print(s.palindromePairs(new String[] {"a", ""})); // [[1,0],[0,1]]
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < words.length; i++) index.put(words[i], i);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            int len = w.length();
            for (int j = 0; j <= len; j++) {
                String left = w.substring(0, j);
                String right = w.substring(j);

                if (isPalindrome(left)) {
                    Integer other = index.get(reverse(right));
                    if (other != null && other != i) res.add(List.of(other, i)); // reverse(right) + w
                }
                if (j != len && isPalindrome(right)) { // j != len avoids duplicating the case above
                    Integer other = index.get(reverse(left));
                    if (other != null && other != i) res.add(List.of(i, other)); // w + reverse(left)
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
