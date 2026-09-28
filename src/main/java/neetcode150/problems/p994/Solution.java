package neetcode150.problems.p994;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 994. Rotting Oranges  [Medium]  -  Graphs
 * https://leetcode.com/problems/rotting-oranges/
 *
 * Problem:
 *   0 = empty, 1 = fresh, 2 = rotten. Each minute rotten oranges rot their fresh neighbours.
 *   Return the minutes until no fresh orange remains, or -1 if that never happens.
 *
 * Example:
 *   Input:  [[2, 1, 1], [1, 1, 0], [0, 1, 1]]
 *   Output: 4
 *
 * Solution - multi-source BFS by minutes:
 *   Queue every rotten orange and count the fresh ones. Each BFS level is one minute: rot the
 *   fresh neighbours and enqueue them. Stop when nothing is fresh; if some are left -> -1.
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().orangesRotting(new int[][] {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}})); // 4
        print(new Solution().orangesRotting(new int[][] {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}})); // -1
        print(new Solution().orangesRotting(new int[][] {{0, 2}})); // 0
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < m; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (grid[i][j] == 2) queue.offer(new int[] { i, j }); else if (
                    grid[i][j] == 1
                ) fresh += 1;
            }
        }

        int count = 0;
        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!queue.isEmpty() && fresh != 0) {
            count += 1;
            int sz = queue.size();
            for (int i = 0; i < sz; i += 1) {
                int[] rotten = queue.poll();
                int r = rotten[0], c = rotten[1];
                for (int[] dir : dirs) {
                    int x = r + dir[0], y = c + dir[1];
                    if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[] { x, y });
                        fresh -= 1;
                    }
                }
            }
        }
        return fresh == 0 ? count : -1;
    }
}
