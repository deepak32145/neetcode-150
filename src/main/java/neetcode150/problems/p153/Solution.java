package neetcode150.problems.p153;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 153. Find Minimum in Rotated Sorted Array  [Medium]  -  Binary Search
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Problem:
 *   A sorted array of distinct values was rotated. Find its minimum in O(log n).
 *
 * Example:
 *   Input:  nums = [3, 4, 5, 1, 2]
 *   Output: 1
 *
 * Solution - binary search toward the rotation point:
 *   If nums[l] <= nums[r] the range is already sorted, so nums[l] is the minimum. Otherwise, if
 *   nums[mid] >= nums[l] the left part is sorted and the minimum is to the right of mid;
 *   else it is at mid or to its left.
 *   Time: O(log n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findMin(new int[] {3, 4, 5, 1, 2})); // 1
        print(new Solution().findMin(new int[] {4, 5, 6, 7, 0, 1, 2})); // 0
        print(new Solution().findMin(new int[] {11, 13, 15, 17})); // 11
    }

    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            if (nums[l] <= nums[r]) {
                return nums[l];
            }

            int mid = (l + r) / 2;
            if (nums[mid] >= nums[l]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return 0;
    }
}
