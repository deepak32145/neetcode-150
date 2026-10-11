package neetcode150.problems.p41;

import static neetcode150.common.Util.*;

/*
 * 41. First Missing Positive  [Hard]
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Problem:
 *   Find the smallest missing positive integer in an unsorted array, in O(n) time and O(1) extra space.
 *
 * Example:
 *   Input:  [3, 4, -1, 1]
 *   Output: 2
 *
 * Solution - place each value v at index v-1:
 *   Swap until nums[i] is in range and already at the right slot. Then scan for the first
 *   index i where nums[i] != i+1; that i+1 is missing (or n+1 if the array is 1..n).
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().firstMissingPositive(new int[] {1, 2, 0})); // 3
        print(new Solution().firstMissingPositive(new int[] {3, 4, -1, 1})); // 2
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int t = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = t;
            }
        }
        for (int i = 0; i < n; i++) if (nums[i] != i + 1) return i + 1;
        return n + 1;
    }
}
