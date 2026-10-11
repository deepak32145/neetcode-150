package neetcode150.problems.p410;

import static neetcode150.common.Util.*;

/*
 * 410. Split Array Largest Sum  [Hard]
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * Problem:
 *   Split nums into k non-empty contiguous subarrays to MINIMIZE the largest subarray sum.
 *
 * Example:
 *   Input:  nums = [7,2,5,10,8], k = 2   Output: 18   ([7,2,5] and [10,8])
 *
 * Solution - binary search on the answer:
 *   The answer lies between max(nums) (a piece must hold the biggest element) and sum(nums)
 *   (one piece). For a candidate limit L, greedily pack elements left to right, starting a new
 *   piece whenever adding the next element would exceed L; count pieces. If pieces <= k, L is
 *   feasible (and we try smaller), otherwise it is too small. Feasibility is monotone in L, so
 *   binary search finds the smallest feasible L.
 *   Time: O(n log(sum))   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.splitArray(new int[] {7, 2, 5, 10, 8}, 2)); // 18
        print(s.splitArray(new int[] {1, 2, 3, 4, 5}, 2)); // 9
    }

    public int splitArray(int[] nums, int k) {
        long lo = 0, hi = 0;
        for (int x : nums) {
            lo = Math.max(lo, x);
            hi += x;
        }
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (piecesNeeded(nums, mid) <= k) hi = mid; // limit is big enough: try tighter
            else lo = mid + 1;                          // too many pieces: need a larger limit
        }
        return (int) lo;
    }

    /** Fewest contiguous pieces so that every piece sum is <= limit (greedy is optimal). */
    private int piecesNeeded(int[] nums, long limit) {
        int pieces = 1;
        long sum = 0;
        for (int x : nums) {
            if (sum + x > limit) {
                pieces++;
                sum = 0;
            }
            sum += x;
        }
        return pieces;
    }
}
