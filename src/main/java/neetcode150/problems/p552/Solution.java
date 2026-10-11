package neetcode150.problems.p552;

import static neetcode150.common.Util.*;

/*
 * 552. Student Attendance Record II  [Hard]
 * https://leetcode.com/problems/student-attendance-record-ii/
 *
 * Problem:
 *   An attendance record is a string over {A, L, P}. It is rewardable if it has fewer than 2 'A'
 *   and no 3 consecutive 'L'. Count rewardable records of length n, modulo 1e9 + 7.
 *
 * Example:
 *   Input:  n = 2   Output: 8   (all 9 length-2 strings except "AA")
 *
 * Solution - DP over a tiny state machine:
 *   The future only depends on (absences so far in {0,1}, trailing consecutive lates in {0,1,2}),
 *   i.e. 6 states. For each day, from state (a, l) with `count` records:
 *     'P' -> (a, 0)
 *     'A' -> (1, 0)   only if a == 0
 *     'L' -> (a, l+1) only if l < 2
 *   Sum all states after n days.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.checkRecord(1)); // 3
        print(s.checkRecord(2)); // 8
        print(s.checkRecord(10101)); // 183236316
    }

    private static final int MOD = 1_000_000_007;

    public int checkRecord(int n) {
        long[][] dp = new long[2][3]; // dp[absences][trailingLates]
        dp[0][0] = 1;                 // the empty record

        for (int day = 0; day < n; day++) {
            long[][] next = new long[2][3];
            for (int a = 0; a <= 1; a++) {
                for (int l = 0; l <= 2; l++) {
                    long count = dp[a][l];
                    if (count == 0) continue;
                    next[a][0] = (next[a][0] + count) % MOD;                  // present: lates reset
                    if (a == 0) next[1][0] = (next[1][0] + count) % MOD;      // first absence
                    if (l < 2) next[a][l + 1] = (next[a][l + 1] + count) % MOD; // late, still < 3 in a row
                }
            }
            dp = next;
        }

        long total = 0;
        for (long[] row : dp) for (long v : row) total += v;
        return (int) (total % MOD);
    }
}
