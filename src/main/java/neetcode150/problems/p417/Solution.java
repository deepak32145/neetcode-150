package neetcode150.problems.p417;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 417. Pacific Atlantic Water Flow  [Medium]  -  Graphs
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * Problem:
 *   Water flows to a neighbour of equal or lower height. The Pacific touches the top and left
 *   edges, the Atlantic the bottom and right. Return every cell that can reach both oceans.
 *
 * Example:
 *   Input:  [[1, 2, 2, 3, 5], [3, 2, 3, 4, 4], [2, 4, 5, 3, 1], [6, 7, 1, 4, 5], [5, 1, 1, 2, 4]]
 *   Output: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
 *
 * Solution - DFS uphill from each ocean:
 *   Start from all Pacific border cells and move to neighbours that are >= the current height,
 *   marking pacific[][]; do the same from the Atlantic borders. Cells marked in both are the
 *   answer.
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        print(new Solution().pacificAtlantic(heights)); // [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
    }

    int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for (int i = 0; i < cols; i++) {
            dfs(heights, 0, i, Integer.MIN_VALUE, pacific);
            dfs(heights, rows - 1, i, Integer.MIN_VALUE, atlantic);
        }

        for (int i = 0; i < rows; i++) {
            dfs(heights, i, 0, Integer.MIN_VALUE, pacific);
            dfs(heights, i, cols - 1, Integer.MIN_VALUE, atlantic);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(List.of(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(
        int[][] heights,
        int i,
        int j,
        int prev,
        boolean[][] ocean
    ) {
        if (i < 0 || i >= ocean.length || j < 0 || j >= ocean[0].length) return;
        if (heights[i][j] < prev || ocean[i][j]) return;

        ocean[i][j] = true;
        for (int[] d : dir) {
            dfs(heights, i + d[0], j + d[1], heights[i][j], ocean);
        }
    }
}
