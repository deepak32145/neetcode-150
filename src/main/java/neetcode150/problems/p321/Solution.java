package neetcode150.problems.p321;

import static neetcode150.common.Util.*;

/*
 * 321. Create Maximum Number  [Hard]
 * https://leetcode.com/problems/create-maximum-number/
 *
 * Problem:
 *   From digit arrays nums1 and nums2 build the lexicographically largest number of length k,
 *   preserving the relative order of digits within each array.
 *
 * Example:
 *   Input:  nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5   Output: [9,8,6,5,3]
 *
 * Solution - split k between the arrays, maximize each part, merge:
 *   For every split (i digits from nums1, k-i from nums2):
 *     1. maxSubsequence(nums, len): the largest subsequence of that length, via a monotonic stack
 *        that pops smaller digits while we still have "drops" to spend (n - len of them).
 *     2. merge the two subsequences into the largest interleaving. When the heads are equal,
 *        compare the REMAINING suffixes (not just the head) to decide which side to take from.
 *   Keep the best merged result across all splits.
 *   Time: O(k * (m+n) + k^2 * ...) ~ O(k * (m + n))   Space: O(k)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.maxNumber(new int[] {3, 4, 6, 5}, new int[] {9, 1, 2, 5, 8, 3}, 5)); // [9, 8, 6, 5, 3]
        print(s.maxNumber(new int[] {6, 7}, new int[] {6, 0, 4}, 5)); // [6, 7, 6, 0, 4]
        print(s.maxNumber(new int[] {3, 9}, new int[] {8, 9}, 3)); // [9, 8, 9]
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] best = new int[k];
        // i digits from nums1, k-i from nums2; both counts must be feasible.
        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] candidate = merge(maxSubsequence(nums1, i), maxSubsequence(nums2, k - i));
            if (compare(candidate, 0, best, 0) > 0) best = candidate;
        }
        return best;
    }

    /** Largest subsequence of length k, preserving order (monotonic decreasing stack). */
    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = 0;
        int drop = nums.length - k; // how many digits we are allowed to discard
        for (int num : nums) {
            while (top > 0 && drop > 0 && stack[top - 1] < num) {
                top--; // a larger digit arrived: replace smaller earlier ones while we can afford it
                drop--;
            }
            if (top < k) {
                stack[top++] = num;
            } else {
                drop--; // stack full: this digit is discarded
            }
        }
        return stack;
    }

    /** Largest interleaving of a and b (each keeps its internal order). */
    private int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0;
        for (int r = 0; r < res.length; r++) {
            // On ties compare full remaining suffixes so we never strand a larger digit behind a smaller one.
            res[r] = compare(a, i, b, j) > 0 ? a[i++] : b[j++];
        }
        return res;
    }

    /** Lexicographic comparison of a[i..] vs b[j..]; a shorter remaining suffix loses when it is a prefix. */
    private int compare(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length) {
            if (a[i] != b[j]) return a[i] - b[j];
            i++;
            j++;
        }
        return (a.length - i) - (b.length - j);
    }
}
