package neetcode150.problems.p152;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 152. Maximum Product Subarray  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Problem:
 *   Return the largest product of any contiguous non-empty subarray.
 *
 * Example:
 *   Input:  nums = [2, 3, -2, 4]
 *   Output: 6                 ([2, 3])
 *
 * Solution - track max and min product ending here:
 *   A negative number turns the smallest product into the largest, so keep both.
 *   For each n: max = max(n, max * n, min * n), min = min(n, max * n, min * n) (using the old
 *   max), and update the answer with max.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxProduct(new int[] {2, 3, -2, 4})); // 6
        print(new Solution().maxProduct(new int[] {-2, 0, -1})); // 0
    }

    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];

        int res = nums[0];
        int max = 1;
        int min = 1;

        for (int n : nums) {
            int tmp = max * n;
            max = Math.max(n, Math.max(tmp, min * n));
            min = Math.min(n, Math.min(tmp, min * n));
            res = Math.max(res, max);
        }
        return res;
    }
}
