package neetcode150.problems.p363;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 363. Max Sum of Rectangle No Larger Than K  [Hard]
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 * Problem:
 *   Find the maximum sum of a sub-rectangle of the matrix such that the sum is <= k.
 *
 * Example:
 *   Input:  matrix = [[1,0,1],[0,-2,3]], k = 2   Output: 2
 *
 * Solution - fix a row band, reduce to "max subarray sum <= k":
 *   For every pair of rows (top, bottom), collapse the band into a 1D array colSum[] by adding
 *   rows. A rectangle in the band is a subarray of colSum. For a running prefix sum `pre`, the best
 *   subarray ending here has sum pre - earlier, and we want the largest value <= k, i.e. the
 *   SMALLEST earlier prefix that is >= pre - k. A TreeSet gives that via ceiling() in O(log n).
 *   Time: O(m^2 * n log n)   Space: O(n)
 *   (If the matrix is much wider than tall, iterate over the smaller dimension; here rows are
 *   used as given.)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.maxSumSubmatrix(new int[][] {{1, 0, 1}, {0, -2, 3}}, 2)); // 2
        print(s.maxSumSubmatrix(new int[][] {{2, 2, -1}}, 3)); // 3
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int best = Integer.MIN_VALUE;

        for (int top = 0; top < m; top++) {
            int[] colSum = new int[n];
            for (int bottom = top; bottom < m; bottom++) {
                for (int c = 0; c < n; c++) colSum[c] += matrix[bottom][c];

                TreeSet<Integer> prefixes = new TreeSet<>();
                prefixes.add(0); // empty prefix
                int pre = 0;
                for (int c = 0; c < n; c++) {
                    pre += colSum[c];
                    // Want smallest earlier prefix p with pre - p <= k, i.e. p >= pre - k.
                    Integer p = prefixes.ceiling(pre - k);
                    if (p != null) best = Math.max(best, pre - p);
                    prefixes.add(pre);
                }
            }
        }
        return best;
    }
}
