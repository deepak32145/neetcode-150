package neetcode150.problems.p778;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 778. Swim in Rising Water  [Hard]  -  Advanced Graphs
 * https://leetcode.com/problems/swim-in-rising-water/
 *
 * Problem:
 *   At time t you can swim between adjacent cells if both heights are <= t. Return the minimum
 *   time to go from the top-left to the bottom-right of an n x n grid.
 *
 * Example:
 *   Input:  grid = [[0, 2], [1, 3]]
 *   Output: 3
 *
 * Solution - Dijkstra-style min-heap on cell height:
 *   Always expand the lowest reachable cell next. The answer is the highest height popped
 *   before reaching the target (the "bottleneck" of the best path).
 *   Time: O(n^2 log n)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().swimInWater(new int[][] {{0, 2}, {1, 3}})); // 3
        print(new Solution().swimInWater(new int[][] {
            {0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}})); // 16
    }

    private int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int swimInWater(int[][] grid) {
        int len = grid.length;

        if (len == 1) {
            return 0;
        }

        var seen = new boolean[len][len];
        seen[0][0] = true;

        var minHeap = new PriorityQueue<Integer[]>((a, b) -> a[0] - b[0]);
        minHeap.add(new Integer[] { grid[0][0], 0, 0 });

        int result = 0;

        while (!minHeap.isEmpty()) {
            var curr = minHeap.poll();

            result = Math.max(result, curr[0]);

            if (curr[1] == len - 1 && curr[2] == len - 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = curr[1] + dirs[i][0];
                int y = curr[2] + dirs[i][1];

                if (x < 0 || x >= len || y < 0 || y >= len || seen[x][y]) {
                    continue;
                }

                minHeap.add(new Integer[] { grid[x][y], x, y });
                seen[x][y] = true;
            }
        }

        return result;
    }
}
