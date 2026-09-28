package neetcode150.problems.p115;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 115. Distinct Subsequences  [Hard]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Problem:
 *   Count how many distinct subsequences of s equal t.
 *
 * Example:
 *   Input:  s = "rabbbit", t = "rabbit"
 *   Output: 3                 (choose which of the three b's to drop)
 *
 * Solution - recursion with memo on (sIdx, tIdx):
 *   If t is fully matched -> 1. If s ran out -> 0. If s[sIdx] == t[tIdx] we may use it
 *   (sIdx + 1, tIdx + 1) or skip it (sIdx + 1, tIdx); otherwise we must skip it.
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().numDistinct("rabbbit", "rabbit")); // 3
        print(new Solution().numDistinct("babgbag", "bag")); // 5
    }

    public int numDistinct(String s, String t) {
        int n = s.length() + 1;
        int m = t.length() + 1;
        int[][] memo = new int[n][m];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return recursion(s, t, 0, 0, memo);
    }

    public int recursion(String s, String t, int sIdx, int tIdx, int[][] memo) {
        if (memo[sIdx][tIdx] != -1) {
            return memo[sIdx][tIdx];
        }

        if (tIdx >= t.length()) {
            return 1;
        }

        if (sIdx >= s.length()) {
            return 0;
        }

        if (t.charAt(tIdx) == s.charAt(sIdx)) {
            memo[sIdx][tIdx] =
                recursion(s, t, sIdx + 1, tIdx + 1, memo) +
                recursion(s, t, sIdx + 1, tIdx, memo);
            return memo[sIdx][tIdx];
        }

        memo[sIdx][tIdx] = recursion(s, t, sIdx + 1, tIdx, memo);
        return memo[sIdx][tIdx];
    }
}
