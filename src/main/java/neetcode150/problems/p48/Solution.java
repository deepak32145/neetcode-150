package neetcode150.problems.p48;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 48. Rotate Image  [Medium]  -  Math & Geometry
 * https://leetcode.com/problems/rotate-image/
 *
 * Problem:
 *   Rotate an n x n matrix 90 degrees clockwise, in place.
 *
 * Example:
 *   Input:  [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
 *   Output: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
 *
 * Solution - rotate layer by layer, four cells at a time:
 *   For each ring (l to r) and each offset i, cycle four cells: save top-left, move
 *   bottom-left -> top-left, bottom-right -> bottom-left, top-right -> bottom-right,
 *   saved -> top-right. Then shrink the ring.
 *   (Alternative: transpose, then reverse each row.)
 *   Time: O(n^2)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        new Solution().rotate(matrix);
        print(matrix); // [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    }

    public void rotate(int[][] matrix) {
        int l = 0;
        int r = matrix.length - 1;
        
        while ( l < r )
        {
            for(int i = 0; i < r - l; i++)
            {
                int top = l;
                int bottom = r;
                 //save the topleft
                int topLeft = matrix[top][l + i];

                //move bottom left into top left
                matrix[top][l + i] = matrix[bottom - i][l];

                // move bottom right into bottom left
                matrix[bottom - i][l] = matrix[bottom][r - i];

                // move top right into bottom right
                matrix[bottom][r - i] = matrix[top + i][r];

                // move top left into top right
                matrix[top + i][r] = topLeft;
                
            }
            
            r -= 1;
            l += 1;
        }
    }
}
