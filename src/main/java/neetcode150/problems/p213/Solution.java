package neetcode150.problems.p213;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 213. House Robber II  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/house-robber-ii/
 *
 * Problem:
 *   Like House Robber, but the houses form a circle, so the first and last are neighbours.
 *
 * Example:
 *   Input:  nums = [2, 3, 2]
 *   Output: 3
 *
 * Solution - run House Robber twice:
 *   The first and last houses can't both be robbed, so answer =
 *   max(rob houses 0..n-2, rob houses 1..n-1), each with the rolling two-variable DP.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().rob(new int[] {2, 3, 2})); // 3
        print(new Solution().rob(new int[] {1, 2, 3, 1})); // 4
        print(new Solution().rob(new int[] {1, 2, 3})); // 3
    }

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return Math.max(
            robHelper(nums, 0, nums.length - 2),
            robHelper(nums, 1, nums.length - 1)
        );
    }

    public int robHelper(int[] nums, int start, int end) {
        int rob1 = 0;
        int rob2 = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }
}
