package neetcode150.problems.p471;

import static neetcode150.common.Util.*;

/*
 * 471. Encode String with Shortest Length  [Hard, Premium]
 * https://leetcode.com/problems/encode-string-with-shortest-length/
 *
 * Problem:
 *   Encode s with the rule k[encoded] (encoded repeated k times) so the result is as short as
 *   possible. If encoding does not make it shorter, return the original.
 *
 * Example:
 *   Input:  s = "aabcaabcd"   Output: "2[aabc]d"
 *
 * Solution - interval DP:
 *   dp[i][j] = shortest encoding of s[i..j]. For every substring, candidates are:
 *     1. Split into two parts: dp[i][k] + dp[k+1][j].
 *     2. Whole-substring repetition: if sub = p repeated r times (smallest period p), then
 *        r + "[" + dp[i][i+p-1] + "]". The period is found with the classic trick
 *        (sub + sub).indexOf(sub, 1): the first re-occurrence position is the smallest period when
 *        it divides the length.
 *   Strings shorter than 5 are never worth encoding ("1[a]" style encodings are not shorter).
 *   Process by increasing length so sub-intervals are ready.
 *   Time: O(n^3) states*splits (+ string work)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.encode("aaa")); // aaa
        print(s.encode("aaaaa")); // 5[a]
        print(s.encode("aaaaaaaaaa")); // a9[a]  (same length as "10[a]"; any shortest encoding is accepted)
        print(s.encode("aabcaabcd")); // 2[aabc]d
    }

    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);
                dp[i][j] = sub; // default: leave as is

                if (len < 5) continue; // encoding cannot beat a string this short

                // Option 1: best split into two independently encoded halves.
                for (int k = i; k < j; k++) {
                    String candidate = dp[i][k] + dp[k + 1][j];
                    if (candidate.length() < dp[i][j].length()) dp[i][j] = candidate;
                }

                // Option 2: sub is a whole repetition of a shorter pattern.
                int period = (sub + sub).indexOf(sub, 1); // smallest shift mapping sub onto itself
                if (period < len && len % period == 0) {
                    String candidate = (len / period) + "[" + dp[i][i + period - 1] + "]";
                    if (candidate.length() < dp[i][j].length()) dp[i][j] = candidate;
                }
            }
        }
        return dp[0][n - 1];
    }
}
