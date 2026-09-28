package neetcode150.problems.p312;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 312. Burst Balloons  [Hard]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/burst-balloons/
 *
 * Problem:
 *   Bursting balloon i earns nums[left] * nums[i] * nums[right] using its current neighbours
 *   (outside the array counts as 1). Return the max coins for bursting them all.
 *
 * Example:
 *   Input:  nums = [3, 1, 5, 8]
 *   Output: 167
 *
 * Solution - interval DP, choose the LAST balloon to burst in a range:
 *   burst(left, right) = max over i in [left, right] of
 *       nums[i] * nums[left - 1] * nums[right + 1]   (i's neighbours when it is last)
 *     + burst(left, i - 1) + burst(i + 1, right).
 *   Missing neighbours at the array ends count as 1. Memoized in dp[left][right].
 *   Time: O(n^3)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxCoins(new int[] {3, 1, 5, 8})); // 167
        print(new Solution().maxCoins(new int[] {1, 5})); // 10
    }

    public int maxCoins(int[] nums) {
        var dp = new int[nums.length][nums.length];

        return burst(nums, 0, nums.length - 1, dp);
    }

    private int burst(int[] nums, int left, int right, int[][] dp) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        for (int i = left; i <= right; i++) {
            int coins = nums[i];

            if (left - 1 >= 0) {
                coins *= nums[left - 1];
            }
            if (right + 1 < nums.length) {
                coins *= nums[right + 1];
            }

            coins +=
                burst(nums, left, i - 1, dp) + burst(nums, i + 1, right, dp);
            dp[left][right] = Math.max(dp[left][right], coins);
        }

        return dp[left][right];
    }
}
