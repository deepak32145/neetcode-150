package neetcode150.problems.p315;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 315. Count of Smaller Numbers After Self  [Hard]
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * Problem:
 *   counts[i] = number of elements to the right of nums[i] that are strictly smaller.
 *
 * Example:
 *   Input:  nums = [5,2,6,1]   Output: [2,1,1,0]
 *
 * Solution - merge sort on indices, counting during the merge:
 *   Sort an array of INDICES by value (so we can still attribute counts to original positions).
 *   When merging a left half and a right half (left indices are all before right indices in the
 *   original array), every time we take an element from the right half, it is smaller than all the
 *   left elements not yet taken. So when we take a left element, the number of right elements taken
 *   so far is exactly how many smaller elements lie to its right within this merge. Ties favor
 *   the left element so equal values are not counted as smaller.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.countSmaller(new int[] {5, 2, 6, 1})); // [2, 1, 1, 0]
        print(s.countSmaller(new int[] {-1})); // [0]
        print(s.countSmaller(new int[] {-1, -1})); // [0, 0]
    }

    private int[] nums;
    private int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        counts = new int[n];
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        mergeSort(idx, new int[n], 0, n);

        List<Integer> res = new ArrayList<>(n);
        for (int c : counts) res.add(c);
        return res;
    }

    private void mergeSort(int[] idx, int[] tmp, int lo, int hi) {
        if (hi - lo <= 1) return;
        int mid = (lo + hi) >>> 1;
        mergeSort(idx, tmp, lo, mid);
        mergeSort(idx, tmp, mid, hi);

        int i = lo, j = mid, k = lo, rightTaken = 0;
        while (i < mid || j < hi) {
            if (j == hi || (i < mid && nums[idx[i]] <= nums[idx[j]])) {
                counts[idx[i]] += rightTaken; // right elements already placed are smaller than this one
                tmp[k++] = idx[i++];
            } else {
                rightTaken++;
                tmp[k++] = idx[j++];
            }
        }
        System.arraycopy(tmp, lo, idx, lo, hi - lo);
    }
}
