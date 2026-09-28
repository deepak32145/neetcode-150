package neetcode150.problems.p695;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 695. Max Area of Island  [Medium]  -  Graphs
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Problem:
 *   In a 0/1 grid, return the area (cell count) of the largest island; 0 if there is none.
 *
 * Example:
 *   Input:  the 8 x 13 example grid in main
 *   Output: 6
 *
 * Solution - DFS that sinks and counts:
 *   From each land cell, DFS returns 1 + the areas of its four neighbours and sets visited cells
 *   to 0 so they're not counted again. Track the max.
 *   Time: O(m * n)   Space: O(m * n) recursion worst case
 */
class Solution {
    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        print(new Solution().maxAreaOfIsland(grid)); // 6
        print(new Solution().maxAreaOfIsland(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0}})); // 0
    }

  int maxArea = 0;
  
  public int maxAreaOfIsland(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        maxArea = Math.max(maxArea, 
                    maxAreaOfIsland(grid, i, j));
      }
    }

    return maxArea;
  }

  public int maxAreaOfIsland(int[][] grid, int r, int c) {
    if (r < 0 || c < 0 || 
        r == grid.length || 
        c == grid[0].length || 
        grid[r][c] == 0) {
          return 0;  
    }

    grid[r][c] = 0;
    
    return (1 + maxAreaOfIsland(grid, r + 1, c) +
                maxAreaOfIsland(grid, r - 1, c) +
                maxAreaOfIsland(grid, r, c + 1) +
                maxAreaOfIsland(grid, r, c - 1));
  }
}
