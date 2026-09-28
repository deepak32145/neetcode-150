package neetcode150.problems.p300;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 300. Longest Increasing Subsequence  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Problem:
 *   Return the length of the longest strictly increasing subsequence.
 *
 * Example:
 *   Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
 *   Output: 4                 ([2, 3, 7, 101])
 *
 * Solution - two versions:
 *   lengthOfLIS: LIS[i] = 1 + max LIS[j] over j > i with nums[j] > nums[i], filled from the
 *                right.                                        Time O(n^2), Space O(n)
 *   lengthOfLISBinarySearch (patience sorting): lis[k] = smallest tail of an increasing run of
 *                length k + 1. Binary-search where each number goes; replace or append.
 *                Its final size is the answer.                 Time O(n log n), Space O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18})); // 4
        print(new Solution().lengthOfLIS(new int[] {0, 1, 0, 3, 2, 3})); // 4
        print(new Solution().lengthOfLISBinarySearch(new int[] {10, 9, 2, 5, 3, 7, 101, 18})); // 4
        print(new Solution().lengthOfLISBinarySearch(new int[] {7, 7, 7, 7})); // 1
    }

    // Dynamic programming, O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;

        int[] LIS = new int[nums.length];
        Arrays.fill(LIS, 1);
        int maximumSoFar = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    LIS[i] = Math.max(1 + LIS[j], LIS[i]);
                }
            }
            maximumSoFar = Math.max(maximumSoFar, LIS[i]);
        }
        return maximumSoFar;
    }

    // Binary search, O(nlogn)
    public int lengthOfLISBinarySearch(int[] nums) {
        List<Integer> lis = new ArrayList<>(nums.length);

        for (int n : nums) {
            int i = Collections.binarySearch(lis, n);
            if (i < 0) i = -i - 1;

            if (i == lis.size())
                lis.add(n);
            else
                lis.set(i, n);
        }
        return lis.size();
    }
}
