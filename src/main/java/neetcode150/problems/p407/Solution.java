package neetcode150.problems.p407;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 407. Trapping Rain Water II  [Hard]
 * https://leetcode.com/problems/trapping-rain-water-ii/
 *
 * Problem:
 *   Given an m x n height map, compute how much rain water is trapped.
 *
 * Example:
 *   Input:  [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]   Output: 4
 *
 * Solution - Dijkstra-style flood from the boundary using a min-heap:
 *   Water level at a cell is limited by the LOWEST point of the wall around it, so process cells
 *   from the lowest known boundary upward. Seed the heap with all border cells (they cannot hold
 *   water). Repeatedly pop the lowest boundary cell; for each unvisited neighbor:
 *       water += max(0, boundaryHeight - neighborHeight)
 *       push the neighbor with height max(neighborHeight, boundaryHeight)  (its effective wall)
 *   Because we always expand from the lowest wall, the boundary height seen is the true limiting
 *   level for that neighbor.
 *   Time: O(mn log(mn))   Space: O(mn)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.trapRainWater(new int[][] {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}})); // 4
        print(s.trapRainWater(new int[][] {{3, 3, 3, 3, 3}, {3, 2, 2, 2, 3}, {3, 2, 1, 2, 3}, {3, 2, 2, 2, 3}, {3, 3, 3, 3, 3}})); // 10
    }

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m < 3 || n < 3) return 0;

        boolean[][] seen = new boolean[m][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // {effective height, r, c}

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 || r == m - 1 || c == 0 || c == n - 1) {
                    heap.add(new int[] {heightMap[r][c], r, c});
                    seen[r][c] = true;
                }
            }
        }

        int water = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            for (int[] d : dirs) {
                int r = cur[1] + d[0], c = cur[2] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n || seen[r][c]) continue;
                seen[r][c] = true;
                // Neighbor sits inside the wall of height cur[0]: anything below it fills with water.
                water += Math.max(0, cur[0] - heightMap[r][c]);
                heap.add(new int[] {Math.max(cur[0], heightMap[r][c]), r, c});
            }
        }
        return water;
    }
}
