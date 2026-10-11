package neetcode150.problems.p327;

import static neetcode150.common.Util.*;

/*
 * 327. Count of Range Sum  [Hard]
 * https://leetcode.com/problems/count-of-range-sum/
 *
 * Problem:
 *   Count subarrays whose sum lies in [lower, upper].
 *
 * Example:
 *   Input:  nums = [-2,5,-1], lower = -2, upper = 2   Output: 3
 *
 * Solution - prefix sums + merge sort counting:
 *   Let pre[k] = sum of the first k numbers. Subarray (i..j-1) has sum pre[j] - pre[i], so we count
 *   pairs i < j with lower <= pre[j] - pre[i] <= upper.
 *   Merge sort pre[]: before merging a sorted left half and sorted right half, for each left value
 *   pre[i] the matching right values form a contiguous window [l, r):
 *       l = first j with pre[j] - pre[i] >= lower,   r = first j with pre[j] - pre[i] > upper
 *   Both l and r only move forward as i increases (left half is sorted), so counting is linear.
 *   Use long: prefix sums overflow int.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.countRangeSum(new int[] {-2, 5, -1}, -2, 2)); // 3
        print(s.countRangeSum(new int[] {0}, 0, 0)); // 1
    }

    private int lower, upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        long[] pre = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) pre[i + 1] = pre[i] + nums[i];
        return sort(pre, new long[pre.length], 0, pre.length);
    }

    private int sort(long[] a, long[] tmp, int lo, int hi) {
        if (hi - lo <= 1) return 0;
        int mid = (lo + hi) >>> 1;
        int count = sort(a, tmp, lo, mid) + sort(a, tmp, mid, hi);

        // Both halves are now sorted: slide a window [l, r) over the right half for each left value.
        int l = mid, r = mid;
        for (int i = lo; i < mid; i++) {
            while (l < hi && a[l] - a[i] < lower) l++;
            while (r < hi && a[r] - a[i] <= upper) r++;
            count += r - l;
        }

        // Standard merge so the parent call sees a sorted range.
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) tmp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i < mid) tmp[k++] = a[i++];
        while (j < hi) tmp[k++] = a[j++];
        System.arraycopy(tmp, lo, a, lo, hi - lo);
        return count;
    }
}
