package neetcode150.problems.p499;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 499. The Maze III  [Hard, Premium]
 * https://leetcode.com/problems/the-maze-iii/
 *
 * Problem:
 *   A ball rolls in a maze (0 = empty, 1 = wall) in one of "u","d","l","r" and keeps rolling until
 *   it hits a wall. There is a hole; if the ball passes over the hole while rolling it falls in and
 *   stops there. Return the lexicographically smallest instruction string of the SHORTEST path
 *   (distance = cells traveled) from ball to hole, or "impossible".
 *
 * Example:
 *   maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]],
 *   ball = [4,3], hole = [0,1]   Output: "lul"
 *
 * Solution - Dijkstra where an edge is a full roll:
 *   Nodes are stopping positions. From a node, roll in each of the 4 directions until a wall (or
 *   until the hole is reached - then stop AT the hole). Edge weight = cells rolled; the edge label
 *   is the direction letter.
 *   Priority queue ordered by (distance, path string): the first time a position is popped it has
 *   the minimum distance, and among equal distances the lexicographically smallest path, so we can
 *   mark it final. If the popped position is the hole, its path is the answer.
 *   Time: O(mn * (m+n) log(mn))   Space: O(mn)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] maze = {{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}};
        print(s.findShortestWay(maze, new int[] {4, 3}, new int[] {0, 1})); // lul
        print(s.findShortestWay(maze, new int[] {4, 3}, new int[] {3, 0})); // impossible
        int[][] maze2 = {{0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1}};
        print(s.findShortestWay(maze2, new int[] {0, 4}, new int[] {3, 5})); // dldr
    }

    // Letters are in alphabetical order d < l < r < u (only matters for tie-breaking via the heap).
    private static final char[] LETTERS = {'d', 'l', 'r', 'u'};
    private static final int[][] DIRS = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        boolean[][] done = new boolean[m][n];

        // Entry: {distance, row, col, path}. Ordered by distance, then lexicographically by path.
        PriorityQueue<Object[]> heap = new PriorityQueue<>((a, b) -> {
            int cmp = Integer.compare((int) a[0], (int) b[0]);
            return cmp != 0 ? cmp : ((String) a[3]).compareTo((String) b[3]);
        });
        heap.add(new Object[] {0, ball[0], ball[1], ""});

        while (!heap.isEmpty()) {
            Object[] cur = heap.poll();
            int dist = (int) cur[0], r = (int) cur[1], c = (int) cur[2];
            String path = (String) cur[3];

            if (done[r][c]) continue; // a shorter / lexicographically smaller way got here first
            done[r][c] = true;
            if (r == hole[0] && c == hole[1]) return path;

            for (int d = 0; d < 4; d++) {
                int nr = r, nc = c, steps = 0;
                // Roll until the next cell is a wall/edge, or we drop into the hole.
                while (nr + DIRS[d][0] >= 0 && nr + DIRS[d][0] < m
                        && nc + DIRS[d][1] >= 0 && nc + DIRS[d][1] < n
                        && maze[nr + DIRS[d][0]][nc + DIRS[d][1]] == 0) {
                    nr += DIRS[d][0];
                    nc += DIRS[d][1];
                    steps++;
                    if (nr == hole[0] && nc == hole[1]) break; // falls in: stops on the hole
                }
                if (steps > 0 && !done[nr][nc]) {
                    heap.add(new Object[] {dist + steps, nr, nc, path + LETTERS[d]});
                }
            }
        }
        return "impossible";
    }
}
