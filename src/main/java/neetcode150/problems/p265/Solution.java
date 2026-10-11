package neetcode150.problems.p265;

import static neetcode150.common.Util.*;

/*
 * 265. Paint House II  [Hard, Premium]
 * https://leetcode.com/problems/paint-house-ii/
 *
 * Problem:
 *   n houses in a row, k colors; costs[i][c] = cost of painting house i with color c.
 *   Adjacent houses must differ in color. Return the minimum total cost.
 *
 * Example:
 *   Input:  costs = [[1,5,3],[2,9,4]]   Output: 5   (house0 color0 = 1, house1 color2 = 4)
 *
 * Solution - DP keeping only the two cheapest totals of the previous house:
 *   dp[i][c] = costs[i][c] + min(dp[i-1][c'] for c' != c). Instead of scanning all k colors for
 *   that min, remember min1 (smallest previous total, with its color idx) and min2 (second
 *   smallest). If c == idx we must use min2, otherwise min1.
 *   Time: O(n*k)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.minCostII(new int[][] {{1, 5, 3}, {2, 9, 4}})); // 5
        print(s.minCostII(new int[][] {{1, 3}, {2, 4}})); // 5
    }

    public int minCostII(int[][] costs) {
        int k = costs[0].length;
        int min1 = 0, min2 = 0, idx = -1; // best / second-best totals so far and color of the best

        for (int[] house : costs) {
            int newMin1 = Integer.MAX_VALUE, newMin2 = Integer.MAX_VALUE, newIdx = -1;
            for (int c = 0; c < k; c++) {
                // Cannot reuse the previous house's best color; fall back to the second best.
                int cost = house[c] + (c == idx ? min2 : min1);
                if (cost < newMin1) {
                    newMin2 = newMin1;
                    newMin1 = cost;
                    newIdx = c;
                } else if (cost < newMin2) {
                    newMin2 = cost;
                }
            }
            min1 = newMin1;
            min2 = newMin2;
            idx = newIdx;
        }
        return min1;
    }
}
