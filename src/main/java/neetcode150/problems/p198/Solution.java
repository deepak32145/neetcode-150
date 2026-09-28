package neetcode150.problems.p198;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 198. House Robber  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/house-robber/
 *
 * Problem:
 *   You can't rob two adjacent houses. Return the maximum money you can rob.
 *
 * Example:
 *   Input:  nums = [2, 7, 9, 3, 1]
 *   Output: 12                (2 + 9 + 1)
 *
 * Solution - dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]):
 *   rob:    rolling two variables.
 *   robDP:  the same recurrence with a full dp array.
 *   robDP2: rolling variables without the small-size special cases.
 *   Time: O(n)   Space: O(1) (O(n) for robDP)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().rob(new int[] {1, 2, 3, 1})); // 4
        print(new Solution().rob(new int[] {2, 7, 9, 3, 1})); // 12
        print(new Solution().robDP(new int[] {2, 7, 9, 3, 1})); // 12
        print(new Solution().robDP2(new int[] {2, 7, 9, 3, 1})); // 12
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int robWithOutIncludingLastHouse = 0, robWithIncludingLastHouse = 0;

        for (int n : nums) {
            int temp = Math.max(
                robWithOutIncludingLastHouse + n,
                robWithIncludingLastHouse
            );
            robWithOutIncludingLastHouse = robWithIncludingLastHouse;
            robWithIncludingLastHouse = temp;
        }
        return robWithIncludingLastHouse;
    }

    public int robDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    // DP with O(1) space
    public int robDP2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int dp0 = 0, dp1 = 0, curr;

        for (int i = 0; i < nums.length; i++) {
            curr = Math.max(dp0 + nums[i], dp1);
            dp0 = dp1;
            dp1 = curr;
        }
        return Math.max(dp0, dp1);
    }
}
