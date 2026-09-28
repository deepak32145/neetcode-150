package neetcode150.problems.p73;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 73. Set Matrix Zeroes  [Medium]  -  Math & Geometry
 * https://leetcode.com/problems/set-matrix-zeroes/
 *
 * Problem:
 *   If a cell is 0, set its entire row and column to 0. Do it in place.
 *
 * Example:
 *   Input:  [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
 *   Output: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
 *
 * Solution - use the first row / column as markers:
 *   Pass 1: for each zero at (i, j) mark matrix[0][j] = 0 and matrix[i][0] = 0 (a flag
 *   remembers whether row 0 itself had a zero). Pass 2: zero every inner cell whose row or
 *   column is marked. Finally handle column 0 and row 0.
 *   Time: O(m * n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        new Solution().setZeroes(matrix);
        print(matrix); // [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
    }

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRow = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    if (i == 0) {
                        firstRow = true;
                    } else {
                        matrix[i][0] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRow) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
