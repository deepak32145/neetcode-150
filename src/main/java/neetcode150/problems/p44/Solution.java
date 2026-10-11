package neetcode150.problems.p44;

import static neetcode150.common.Util.*;

/*
 * 44. Wildcard Matching  [Hard]
 * https://leetcode.com/problems/wildcard-matching/
 *
 * Problem:
 *   Match s against p where '?' matches one char and '*' matches any sequence (including empty).
 *
 * Example:
 *   Input:  s = "adceb", p = "*a*b"
 *   Output: true
 *
 * Solution - 2D DP:
 *   dp[i][j] = s[0..i) matches p[0..j). '?' / equal letters copy the diagonal; '*' is
 *   empty (dp[i][j-1]) or eat one more s char (dp[i-1][j]).
 *   Time: O(m*n)   Space: O(m*n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isMatch("aa", "a")); // false
        print(new Solution().isMatch("adceb", "*a*b")); // true
    }

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                else if (pc == '?' || pc == s.charAt(i - 1)) dp[i][j] = dp[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }
}
