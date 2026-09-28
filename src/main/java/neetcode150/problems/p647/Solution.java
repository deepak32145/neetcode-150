package neetcode150.problems.p647;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 647. Palindromic Substrings  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/palindromic-substrings/
 *
 * Problem:
 *   Count the substrings of s that are palindromes.
 *
 * Example:
 *   Input:  s = "aaa"
 *   Output: 6                 ("a", "a", "a", "aa", "aa", "aaa")
 *
 * Solution - expand around centers:
 *   For each index expand an odd-length center (i, i) and an even-length center (i, i + 1);
 *   every successful expansion step is one more palindrome.
 *   Time: O(n^2)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().countSubstrings("abc")); // 3
        print(new Solution().countSubstrings("aaa")); // 6
    }

    public int countSubstrings(String s) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            res += countSubstrings(s, i, i);
            res += countSubstrings(s, i, i + 1);
        }

        return res;
    }

    public int countSubstrings(String s, 
                               int start, int end) {
        int res = 0;
    
        while (start >= 0 && end < s.length() 
               && s.charAt(start) == s.charAt(end)) {
            ++res;
            --start;
            ++end;
        }

        return res;
    }
}
