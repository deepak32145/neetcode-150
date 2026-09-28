package neetcode150.problems.p53;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 53. Maximum Subarray  [Medium]  -  Greedy
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Problem:
 *   Find the contiguous (non-empty) subarray with the largest sum and return that sum.
 *
 * Example:
 *   Input:  nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *   Output: 6                 ([4, -1, 2, 1])
 *
 * Solution - Kadane's algorithm:
 *   Keep a running sum; update the max after adding each number. If the running sum drops below
 *   0 it can only hurt what follows, so reset it to 0.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        print(new Solution().maxSubArray(new int[] {5, 4, -1, 7, 8})); // 23
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];

        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int n : nums) {
            sum += n;
            max = Math.max(max, sum);

            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
