package neetcode150.problems.p4;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 4. Median of Two Sorted Arrays  [Hard]  -  Binary Search
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Problem:
 *   Given two sorted arrays nums1 (size m) and nums2 (size n), return the median of the
 *   combined values.
 *
 * Example:
 *   Input:  nums1 = [1, 2], nums2 = [3, 4]
 *   Output: 2.5               (merged = [1, 2, 3, 4], median = (2 + 3) / 2)
 *
 * Solution - merge then pick the middle:
 *   Merge both arrays like merge sort into one sorted array, then return the middle element
 *   (odd total) or the average of the two middle elements (even total).
 *   The optimal approach binary-searches a partition of the smaller array: O(log(min(m, n))).
 *   Time: O(m + n)   Space: O(m + n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findMedianSortedArrays(new int[] {1, 3}, new int[] {2})); // 2.0
        print(new Solution().findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4})); // 2.5
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m+n];
        int i = 0, j = 0;
        int k = 0;
        while (i<m && j<n) {
            if (nums1[i]<nums2[j]) nums[k++] = nums1[i++];
            else nums[k++] = nums2[j++];
        }
        for (; i<m; i++) nums[k++] = nums1[i];
        for (; j<n; j++) nums[k++] = nums2[j];
        if ((m+n)%2 == 0) {
            return ((float)nums[(m+n-1)/2]+(float)nums[(m+n)/2])/(float)2;
        } else return (float)nums[(m+n-1)/2];
    }
}

/* Optimized solution (Logarithmic) */

// Runtime: O(log(min(m,n)))
// Extra Space: O(1)
