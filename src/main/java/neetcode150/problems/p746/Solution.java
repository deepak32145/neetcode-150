package neetcode150.problems.p746;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 746. Min Cost Climbing Stairs  [Easy]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 *
 * Problem:
 *   cost[i] is paid when you step on stair i; you can climb 1 or 2 steps and may start at stair 0
 *   or 1. Return the minimum cost to go past the top.
 *
 * Example:
 *   Input:  cost = [10, 15, 20]
 *   Output: 15                (start at 1, pay 15, jump 2 to the top)
 *
 * Solution - DP from the top, in place:
 *   cost[i] += min(cost of the next stair, cost of the one after). Answer = min(cost[0], cost[1]).
 *   Time: O(n)   Space: O(1) (reuses the input array)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().minCostClimbingStairs(new int[] {10, 15, 20})); // 15
        print(new Solution().minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
    }

    public int minCostClimbingStairs(int[] cost) {
        int one = 0;
        int two = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            cost[i] += Math.min(one, two);
            two = one;
            one = cost[i];
        }

        return Math.min(cost[0], cost[1]);
    }
}
