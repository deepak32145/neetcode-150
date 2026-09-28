package neetcode150.problems.p200;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 200. Number of Islands  [Medium]  -  Graphs
 * https://leetcode.com/problems/number-of-islands/
 *
 * Problem:
 *   Count the islands in a grid of '1' (land) and '0' (water). Islands connect horizontally
 *   and vertically.
 *
 * Example:
 *   Input:  ["11000", "11000", "00100", "00011"]
 *   Output: 3
 *
 * Solution - DFS "sink" each island:
 *   Scan the grid. On every '1' found, count one island and DFS through the connected land,
 *   turning it into '0' so it's never counted again.
 *   Time: O(m * n)   Space: O(m * n) recursion worst case
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().numIslands(board("11110", "11010", "11000", "00000"))); // 1
        print(new Solution().numIslands(board("11000", "11000", "00100", "00011"))); // 3
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (
            i < 0 ||
            j < 0 ||
            i >= grid.length ||
            j >= grid[0].length ||
            grid[i][j] == '0'
        ) {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
