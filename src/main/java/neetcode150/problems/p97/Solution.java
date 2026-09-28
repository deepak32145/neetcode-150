package neetcode150.problems.p97;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 97. Interleaving String  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/interleaving-string/
 *
 * Problem:
 *   Is s3 formed by interleaving s1 and s2 (keeping each string's own character order)?
 *
 * Example:
 *   Input:  s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *   Output: true
 *
 * Solution - bottom-up 2-D DP:
 *   dp[i][j] = can s1[i..] and s2[j..] form s3[i + j..]. dp[len1][len2] = true. Fill backwards:
 *   dp[i][j] is true if s1[i] == s3[i + j] and dp[i + 1][j], or s2[j] == s3[i + j] and
 *   dp[i][j + 1]. Lengths must add up first.
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac")); // true
        print(new Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc")); // false
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[s1.length()][s2.length()] = true;

        for (int i = dp.length - 1; i >= 0; i--) for (
            int j = dp[0].length - 1;
            j >= 0;
            j--
        ) {
            if (
                i < s1.length() &&
                s1.charAt(i) == s3.charAt(i + j) &&
                dp[i + 1][j]
            ) {
                dp[i][j] = true;
            }
            if (
                j < s2.length() &&
                s2.charAt(j) == s3.charAt(i + j) &&
                dp[i][j + 1]
            ) {
                dp[i][j] = true;
            }
        }

        return dp[0][0];
    }
}
