package neetcode150.problems.p286;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 286. Walls and Gates  [Medium]  -  Graphs   (LeetCode premium / LintCode 663)
 * https://leetcode.com/problems/walls-and-gates/
 *
 * Problem:
 *   Grid cells: -1 = wall, 0 = gate, INF (2147483647) = empty room. Fill each room with the
 *   distance to its nearest gate (leave INF if unreachable).
 *
 * Example:
 *   Input:  [[INF, -1, 0, INF], [INF, INF, INF, -1], [INF, -1, INF, -1], [0, -1, INF, INF]]
 *   Output: [[3, -1, 0, 1], [2, 2, 1, -1], [1, -1, 2, -1], [0, -1, 3, 4]]
 *
 * Solution - multi-source BFS from all gates:
 *   Start the queue with every gate at once. Each room is filled the first time BFS reaches it,
 *   which is its shortest distance (neighbour = current + 1).
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] rooms = {{INF, -1, 0, INF}, {INF, INF, INF, -1}, {INF, -1, INF, -1}, {0, -1, INF, INF}};
        new Solution().wallsAndGates(rooms);
        print(rooms); // [[3, -1, 0, 1], [2, 2, 1, -1], [1, -1, 2, -1], [0, -1, 3, 4]]
    }

    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> q = new LinkedList<>();
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) q.add(new int[] { i, j });
            }
        }
        if (q.size() == 0) return;
        int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] dir : dirs) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (
                    x >= m ||
                    y >= n ||
                    x < 0 ||
                    y < 0 ||
                    rooms[x][y] != Integer.MAX_VALUE
                ) continue;
                q.add(new int[] { x, y });
                //since cur is basically the index of door (which is equal to 0)
                //So, we can just grab that value (rooms[row][col]) and add 1 to it and change it every time
                rooms[x][y] = rooms[row][col] + 1;
                //So, one level further from door (value 0) is equal to 1. Now, we do bfs from that position so value will be 2 and so on.
            }
        }
    }
}
