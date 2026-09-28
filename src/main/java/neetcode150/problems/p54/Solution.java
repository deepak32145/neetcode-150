package neetcode150.problems.p54;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 54. Spiral Matrix  [Medium]  -  Math & Geometry
 * https://leetcode.com/problems/spiral-matrix/
 *
 * Problem:
 *   Return all elements of an m x n matrix in clockwise spiral order.
 *
 * Example:
 *   Input:  [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
 *   Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
 *
 * Solution - shrinking boundaries:
 *   Keep top/bottom rows (rb, re) and left/right columns (cb, ce). Walk the top row, right
 *   column, bottom row (if rows remain) and left column (if columns remain), shrinking the
 *   matching boundary after each side.
 *   Time: O(m * n)   Space: O(1) extra
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})); // [1, 2, 3, 6, 9, 8, 7, 4, 5]
        print(new Solution().spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})); // [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rb = 0;
        int re = matrix.length - 1;
        int cb = 0;
        int ce = matrix[0].length - 1;

        while (rb <= re && cb <= ce) {
            for (int j = cb; j <= ce; j++) {
                list.add(matrix[rb][j]);
            }
            rb++;

            for (int i = rb; i <= re; i++) {
                list.add(matrix[i][ce]);
            }
            ce--;

            if (rb <= re) {
                for (int j = ce; j >= cb; j--) {
                    list.add(matrix[re][j]);
                }
            }
            re--;

            if (cb <= ce) {
                for (int i = re; i >= rb; i--) {
                    list.add(matrix[i][cb]);
                }
            }
            cb++;
        }

        return list;
    }
}
