package neetcode150.problems.p174;

import java.util.Arrays;
import static neetcode150.common.Util.*;

/*
 * 174. Dungeon Game  [Hard]
 * https://leetcode.com/problems/dungeon-game/
 *
 * Problem:
 *   Knight moves right/down from top-left to bottom-right; each cell adds or subtracts health.
 *   Health must stay >= 1 at all times. Return the minimum starting health.
 *
 * Example:
 *   Input:  [[-2,-3,3],[-5,-10,1],[10,30,-5]]   Output: 7
 *
 * Solution - DP from the goal backwards:
 *   dp[i][j] = min health needed when ENTERING cell (i,j) to still reach the end.
 *   From (i,j) the knight continues to the cheaper of right/down, so
 *       dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])
 *   Going backwards is essential: a forward DP would not know how much health future damage needs.
 *   One row of state is enough. Time: O(m*n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.calculateMinimumHP(new int[][] {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}})); // 7
        print(s.calculateMinimumHP(new int[][] {{0}})); // 1
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = 1; // after the princess's cell the knight just needs to be alive (>= 1)

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int need = Math.min(dp[j], dp[j + 1]) - dungeon[i][j]; // dp[j] = below, dp[j+1] = right
                dp[j] = Math.max(1, need);
            }
        }
        return dp[0];
    }
}
