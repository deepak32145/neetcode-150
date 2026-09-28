package neetcode150.problems.p74;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 74. Search a 2D Matrix  [Medium]  -  Binary Search
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Problem:
 *   Each row is sorted and each row's first value is larger than the previous row's last.
 *   Return whether target exists.
 *
 * Example:
 *   Input:  [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 3
 *   Output: true
 *
 * Solution - two versions:
 *   searchMatrix:  start at the top-right corner; go left if the value is too big, down if
 *                  too small.                                   O(m + n)
 *   searchMatrix2: treat the matrix as one sorted array of m * n values and binary-search it
 *                  (index k -> row k / cols, col k % cols).    O(log(m * n))
 *   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        print(new Solution().searchMatrix(matrix, 3)); // true
        print(new Solution().searchMatrix(matrix, 13)); // false
        print(new Solution().searchMatrix2(matrix, 3)); // true
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
		 return true;
	    } else if (matrix[i][j] > target) {
		 j--;
	    } else {
		 i++;
	    }
        }
        return false;
    }

    //    Time: O(log(mn)) | Space: O(1)
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length == 0) return false;

        int rows = matrix.length;
        int columns = matrix[0].length;

        int low = 0;
        int high = rows * columns;

        while(low < high) {
            int mid = (low+high)/2;

            if(matrix[mid/columns][mid%columns] == target) {
                return true;
            } else if (matrix[mid/columns][mid%columns] < target) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return false;
    }
}
