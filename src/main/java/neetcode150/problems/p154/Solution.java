package neetcode150.problems.p154;

import static neetcode150.common.Util.*;

/*
 * 154. Find Minimum in Rotated Sorted Array II  [Hard]
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Problem:
 *   Find the minimum of a rotated sorted array that may contain duplicates.
 *
 * Example:
 *   Input:  nums = [2,2,2,0,1]   Output: 0
 *
 * Solution - binary search against the right end:
 *   Compare nums[mid] with nums[hi]:
 *     nums[mid] > nums[hi]  -> the minimum is strictly right of mid       -> lo = mid + 1
 *     nums[mid] < nums[hi]  -> mid could be the minimum, look left        -> hi = mid
 *     nums[mid] == nums[hi] -> cannot tell which side; hi is redundant    -> hi--
 *   The hi-- case is why the worst case degrades to O(n) (e.g. all equal).
 *   Time: O(log n) average, O(n) worst   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findMin(new int[] {1, 3, 5})); // 1
        print(s.findMin(new int[] {2, 2, 2, 0, 1})); // 0
        print(s.findMin(new int[] {3, 1, 3})); // 1
    }

    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                hi--; // equal: dropping hi never loses the minimum (nums[mid] holds the same value)
            }
        }
        return nums[lo];
    }
}
