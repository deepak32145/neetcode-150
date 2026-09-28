package neetcode150.problems.p238;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 238. Product of Array Except Self  [Medium]  -  Arrays & Hashing
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Problem:
 *   Return an array where answer[i] = product of all nums except nums[i], in O(n), without
 *   division.
 *
 * Example:
 *   Input:  nums = [1, 2, 3, 4]
 *   Output: [24, 12, 8, 6]
 *
 * Solution - prefix and suffix products:
 *   First pass left to right: arr[i] = product of everything before i. Second pass right to
 *   left: multiply arr[i] by the running product of everything after i.
 *   productExceptSelfNumsAsPrefix does the same but stores the suffix products inside nums.
 *   Time: O(n)   Space: O(1) extra (output array not counted)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().productExceptSelf(new int[] {1, 2, 3, 4})); // [24, 12, 8, 6]
        print(new Solution().productExceptSelf(new int[] {-1, 1, 0, -3, 3})); // [0, 0, 9, 0, 0]
        print(new Solution().productExceptSelfNumsAsPrefix(new int[] {1, 2, 3, 4})); // [24, 12, 8, 6]
    }

    public int[] productExceptSelf(int[] nums) {
        int[] arr = new int[nums.length];
        int right = 1, left = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[i] = left;
            left *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            arr[i] *= right;
            right *= nums[i];
        }
        return arr;
    }

    public int[] productExceptSelfNumsAsPrefix(int[] nums) {
        int[] output = new int[nums.length];
        output[0] = 1;

        for (int i = 0; i < nums.length - 1; i++) output[i + 1] =
            output[i] * nums[i];

        for (int i = nums.length - 2; i >= 0; i--) {
            output[i] = nums[i + 1] * output[i];
            nums[i] = nums[i] * nums[i + 1];
        }
        return output;
    }
}
