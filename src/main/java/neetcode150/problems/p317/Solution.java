package neetcode150.problems.p317;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 317. Shortest Distance from All Buildings  [Hard, Premium]
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 *
 * Problem:
 *   Grid: 0 = empty, 1 = building, 2 = obstacle. Build a house on an empty cell so the sum of
 *   walking distances to ALL buildings is minimal. Return that sum, or -1 if impossible.
 *
 * Example:
 *   Input:  [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]   Output: 7
 *
 * Solution - BFS outward from every building, accumulating distances:
 *   For each building run a BFS over empty cells, adding the distance to total[r][c] and
 *   incrementing reach[r][c]. A cell is a valid house site only if reach == number of buildings.
 *   Optimization: BFS for a building can only enter cells that all previous buildings reached
 *   (reach == number of previous buildings processed), which prunes unreachable regions early.
 *   Answer = min total[r][c] over valid cells.
 *   Time: O(B * m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.shortestDistance(new int[][] {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}})); // 7
        print(s.shortestDistance(new int[][] {{1, 0}})); // 1
        print(s.shortestDistance(new int[][] {{1}})); // -1
    }

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] total = new int[m][n]; // sum of distances from all buildings processed so far
        int[][] reach = new int[m][n]; // how many buildings have reached this cell
        int buildings = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    bfs(grid, r, c, total, reach, buildings);
                    buildings++;
                }
            }
        }

        int best = Integer.MAX_VALUE;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0 && reach[r][c] == buildings) best = Math.min(best, total[r][c]);
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    private void bfs(int[][] grid, int sr, int sc, int[][] total, int[][] reach, int processed) {
        int m = grid.length, n = grid[0].length;
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc});
        seen[sr][sc] = true;

        for (int dist = 1; !q.isEmpty(); dist++) {
            for (int size = q.size(); size > 0; size--) {
                int[] cur = q.poll();
                for (int[] d : DIRS) {
                    int r = cur[0] + d[0], c = cur[1] + d[1];
                    if (r < 0 || r >= m || c < 0 || c >= n || seen[r][c]) continue;
                    if (grid[r][c] != 0) continue;            // buildings and obstacles block movement
                    if (reach[r][c] != processed) continue;   // some earlier building cannot reach it
                    seen[r][c] = true;
                    total[r][c] += dist;
                    reach[r][c]++;
                    q.add(new int[] {r, c});
                }
            }
        }
    }
}
