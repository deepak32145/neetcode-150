package neetcode150.problems.p5;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 5. Longest Palindromic Substring  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Problem:
 *   Given a string s, return the longest substring of s that is a palindrome.
 *
 * Example:
 *   Input:  s = "babad"
 *   Output: "bab"             ("aba" is also valid)
 *
 * Solution - expand around every center:
 *   Each palindrome has a center: a single char (odd length) or a gap between two chars
 *   (even length). For every index expand outward while both ends match, and keep the
 *   longest window seen.
 *   Time: O(n^2)   Space: O(1) extra (besides the result string)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().longestPalindrome("babad")); // "bab"
        print(new Solution().longestPalindrome("cbbd")); // "bb"
    }

    public String longestPalindrome(String s) {
        int strLength = s.length();

        if (strLength < 2) {
            return s;
        }

        int resultLength = 0;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            //Odd length
            int left = i, right = i;
            while (
                left >= 0 &&
                right < s.length() &&
                s.charAt(left) == s.charAt(right)
            ) {
                if ((right - left + 1) > resultLength) {
                    result = s.substring(left, right + 1);
                    resultLength = right - left + 1;
                }
                left -= 1;
                right += 1;
            }

            // even length
            left = i;
            right = i + 1;
            while (
                left >= 0 &&
                right < s.length() &&
                s.charAt(left) == s.charAt(right)
            ) {
                if ((right - left + 1) > resultLength) {
                    result = s.substring(left, right + 1);
                    resultLength = right - left + 1;
                }
                left -= 1;
                right += 1;
            }
        }

        return result;
    }
}

// Solution: A more Optimized Expand Around Center

// TIme Complexity: O(n^2)
// Extra Space Complexity: O(1)
