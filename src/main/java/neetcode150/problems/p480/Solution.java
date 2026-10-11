package neetcode150.problems.p480;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 480. Sliding Window Median  [Hard]
 * https://leetcode.com/problems/sliding-window-median/
 *
 * Problem:
 *   Return the median of every window of size k as it slides across nums.
 *
 * Example:
 *   Input:  nums = [1,3,-1,-3,5,3,6,7], k = 3   Output: [1,-1,-1,3,5,6]
 *
 * Solution - two balanced ordered sets (a "lower half" and an "upper half"):
 *   lo holds the smaller half, hi the larger half, with |lo| = |hi| or |lo| = |hi| + 1, so the
 *   median is lo.last() (odd k) or the mean of lo.last() and hi.first() (even k).
 *   Store INDICES and order them by (value, index): this keeps duplicates distinct so a specific
 *   element can be removed when it leaves the window.
 *   Each step: add the new index, remove the outgoing one, then rebalance by moving one element
 *   between the sets until sizes are right.
 *   Time: O(n log k)   Space: O(k)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.medianSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
        print(s.medianSlidingWindow(new int[] {1, 2, 3, 4, 2, 3, 1, 4, 2}, 3)); // [2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0]
        print(s.medianSlidingWindow(new int[] {2147483647, 2147483647}, 2)); // [2.147483647E9]
    }

    private int[] nums;
    private TreeSet<Integer> lo;
    private TreeSet<Integer> hi;

    public double[] medianSlidingWindow(int[] nums, int k) {
        this.nums = nums;
        // Order indices by value, then by index so equal values do not collide.
        Comparator<Integer> byValue = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
        lo = new TreeSet<>(byValue);
        hi = new TreeSet<>(byValue);

        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            add(i);
            if (i >= k) remove(i - k); // element that just left the window
            if (i >= k - 1) res[i - k + 1] = median(k);
        }
        return res;
    }

    private void add(int i) {
        if (lo.isEmpty() || nums[i] <= nums[lo.last()]) lo.add(i); else hi.add(i);
        rebalance();
    }

    private void remove(int i) {
        if (!lo.remove(i)) hi.remove(i);
        rebalance();
    }

    /** Keep |lo| == |hi| or |lo| == |hi| + 1. */
    private void rebalance() {
        while (lo.size() > hi.size() + 1) hi.add(lo.pollLast());
        while (lo.size() < hi.size()) lo.add(hi.pollFirst());
    }

    private double median(int k) {
        if (k % 2 == 1) return nums[lo.last()];
        return ((double) nums[lo.last()] + nums[hi.first()]) / 2.0; // double avoids int overflow
    }
}
