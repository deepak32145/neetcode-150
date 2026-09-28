package neetcode150.problems.p416;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 416. Partition Equal Subset Sum  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Problem:
 *   Can nums be split into two subsets with equal sums?
 *
 * Example:
 *   Input:  nums = [1, 5, 11, 5]
 *   Output: true              ([1, 5, 5] and [11])
 *
 * Solution - 0/1 knapsack to sum / 2 (odd total -> false):
 *   canPartition:   dp[i][j] = can the first i numbers make sum j
 *                   = dp[i - 1][j] or dp[i - 1][j - nums[i - 1]].   Space O(n * sum)
 *   canPartition1D: the same with a 1-D array, iterating j downward so each number is used at
 *                   most once; returns as soon as the target is reachable. Space O(sum)
 *   Time: O(n * sum)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().canPartition(new int[] {1, 5, 11, 5})); // true
        print(new Solution().canPartition(new int[] {1, 2, 3, 5})); // false
        print(new Solution().canPartition1D(new int[] {1, 5, 11, 5})); // true
        print(new Solution().canPartition1D(new int[] {1, 2, 3, 5})); // false
    }

    // TC = O(n*sum), SC = O(n*sum)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0) dp[i][j] = false; else if (j == 0) dp[i][j] =
                        true;
                } else if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    // TC = O(n*sum), SC = O(sum)
    public boolean canPartition1D(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target];
        dp[0] = true;

        for (int no : nums) {
            for (int i = target; i >= no; i--) {
                if (dp[i - no] == true) {
                    if (i == target) return true;
                    dp[i] = true;
                }
            }
        }
        return false;
    }
}
