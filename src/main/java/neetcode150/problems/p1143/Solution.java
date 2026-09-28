package neetcode150.problems.p1143;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1143. Longest Common Subsequence  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Problem:
 *   Return the length of the longest subsequence common to both strings.
 *
 * Example:
 *   Input:  text1 = "abcde", text2 = "ace"
 *   Output: 3                 ("ace")
 *
 * Solution - recursion with memo on (i, j):
 *   If s1[i] == s2[j]: 1 + LCS(i + 1, j + 1). Otherwise max(LCS(i + 1, j), LCS(i, j + 1)).
 *   Past either end -> 0. (Bottom-up table from the end works the same way.)
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().longestCommonSubsequence("abcde", "ace")); // 3
        print(new Solution().longestCommonSubsequence("abc", "abc")); // 3
        print(new Solution().longestCommonSubsequence("abc", "def")); // 0
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        return LCS(text1, text2, 0, 0, dp);
    }

    public int LCS(String s1, String s2, int i, int j, int[][] dp) {
        if (i >= s1.length() || j >= s2.length()) return 0; else if (
            dp[i][j] != 0
        ) return dp[i][j]; else if (s1.charAt(i) == s2.charAt(j)) return (
            1 + LCS(s1, s2, i + 1, j + 1, dp)
        ); else {
            dp[i][j] =
                Math.max(LCS(s1, s2, i + 1, j, dp), LCS(s1, s2, i, j + 1, dp));
            return dp[i][j];
        }
    }
}

// Iterative version
