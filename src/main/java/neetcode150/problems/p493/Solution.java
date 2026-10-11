package neetcode150.problems.p493;

import static neetcode150.common.Util.*;

/*
 * 493. Reverse Pairs  [Hard]
 * https://leetcode.com/problems/reverse-pairs/
 *
 * Problem:
 *   Count pairs (i, j) with i < j and nums[i] > 2 * nums[j].
 *
 * Example:
 *   Input:  nums = [1,3,2,3,1]   Output: 2   ((1,4) and (3,4))
 *
 * Solution - merge sort, counting before each merge:
 *   Split into halves, count inside each half recursively. Cross pairs have i in the left half and
 *   j in the right half. Both halves are sorted at that point, so for increasing nums[i] the
 *   boundary j (first right element NOT satisfying nums[i] > 2*nums[j]) only moves forward - a
 *   two-pointer sweep counts all cross pairs in O(n). Then do the normal merge.
 *   The doubling is done in long: 2 * nums[j] can overflow int.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.reversePairs(new int[] {1, 3, 2, 3, 1})); // 2
        print(s.reversePairs(new int[] {2, 4, 3, 5, 1})); // 3
        print(s.reversePairs(new int[] {2147483647, 2147483647, 2147483647})); // 0
    }

    public int reversePairs(int[] nums) {
        return sort(nums, new int[nums.length], 0, nums.length);
    }

    private int sort(int[] a, int[] tmp, int lo, int hi) {
        if (hi - lo <= 1) return 0;
        int mid = (lo + hi) >>> 1;
        int count = sort(a, tmp, lo, mid) + sort(a, tmp, mid, hi);

        // Count cross pairs: for each left value, how many right values are less than half of it.
        int j = mid;
        for (int i = lo; i < mid; i++) {
            while (j < hi && a[i] > 2L * a[j]) j++;
            count += j - mid;
        }

        // Standard merge of the two sorted halves.
        int i = lo, p = mid, k = lo;
        while (i < mid && p < hi) tmp[k++] = a[i] <= a[p] ? a[i++] : a[p++];
        while (i < mid) tmp[k++] = a[i++];
        while (p < hi) tmp[k++] = a[p++];
        System.arraycopy(tmp, lo, a, lo, hi - lo);
        return count;
    }
}
