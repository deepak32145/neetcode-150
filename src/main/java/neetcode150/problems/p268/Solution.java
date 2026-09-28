package neetcode150.problems.p268;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 268. Missing Number  [Easy]  -  Bit Manipulation / Math
 * https://leetcode.com/problems/missing-number/
 *
 * Problem:
 *   nums holds n distinct numbers from the range [0, n]. Return the one that is missing.
 *
 * Example:
 *   Input:  nums = [3, 0, 1]
 *   Output: 2
 *
 * Solution - Gauss sum:
 *   Expected total = n * (n + 1) / 2. Missing number = expected total - actual sum.
 *   (XOR of all indices and values works too and can't overflow.)
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().missingNumber(new int[] {3, 0, 1})); // 2
        print(new Solution().missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1})); // 8
    }

    public int missingNumber(int[] nums) {
        int sum = 0;
        int total = nums.length * (nums.length + 1) / 2;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return total - sum;
    }
}
