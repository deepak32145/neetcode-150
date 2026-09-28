package neetcode150.problems.p131;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 131. Palindrome Partitioning  [Medium]  -  Backtracking
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * Problem:
 *   Split s into pieces so that every piece is a palindrome. Return all such partitions.
 *
 * Example:
 *   Input:  s = "aab"
 *   Output: [["a", "a", "b"], ["aa", "b"]]
 *
 * Solution - recursion on prefixes:
 *   For every prefix s[0..i] that is a palindrome, partition the remaining suffix recursively
 *   and put the prefix in front of each returned partition. The empty string has one (empty)
 *   partition.
 *   Time: O(n * 2^n)   Space: O(n * 2^n) for the results
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().partition("aab")); // [["a", "a", "b"], ["aa", "b"]]
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s.equals("")) {
            res.add(new ArrayList<String>());
            return res;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, i + 1)) {
                for (List<String> list : partition(s.substring(i + 1))) {
                    list.add(0, s.substring(0, i + 1));
                    res.add(list);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s, int n) {
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) return false;
        }
        return true;
    }
}
