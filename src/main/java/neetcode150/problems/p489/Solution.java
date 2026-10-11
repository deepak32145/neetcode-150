package neetcode150.problems.p489;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 489. Robot Room Cleaner  [Hard, Premium]
 * https://leetcode.com/problems/robot-room-cleaner/
 *
 * Problem:
 *   A robot in an unknown room (grid of open cells and walls) with the API below must clean every
 *   reachable open cell. It does not know its position or the map; it can only move forward (if no
 *   wall), turn 90 degrees, and clean its current cell.
 *
 * Example:
 *   room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]]
 *   start = (1, 3)  ->  every reachable 1 gets cleaned
 *
 * Solution - DFS with relative coordinates and explicit backtracking:
 *   Treat the start as (0, 0) facing "up" and track (row, col, direction) relative to it. At each
 *   cell: clean it, mark it visited, then try the four directions in clockwise order. For each:
 *     - if the neighbor is unvisited and move() succeeds, recurse, then physically undo the move
 *       (turn 180, move, turn 180) so the robot returns to this cell facing the same way;
 *     - turn right to face the next direction.
 *   Visited uses relative coordinates, so the real map is never needed.
 *   Time: O(open cells * 4)   Space: O(open cells)
 *
 * Robot and MockRobot below are local stand-ins for LeetCode's provided interface. Delete them
 * when pasting into LeetCode.
 */
interface Robot {
    boolean move();      // moves forward one cell if there is no wall; false if blocked

    void turnLeft();

    void turnRight();

    void clean();
}

class MockRobot implements Robot {
    private final int[][] room;
    private int r, c, dir; // dir: 0=up 1=right 2=down 3=left
    final Set<String> cleaned = new HashSet<>();
    private static final int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    MockRobot(int[][] room, int r, int c) {
        this.room = room;
        this.r = r;
        this.c = c;
    }

    public boolean move() {
        int nr = r + D[dir][0], nc = c + D[dir][1];
        if (nr < 0 || nr >= room.length || nc < 0 || nc >= room[0].length || room[nr][nc] == 0) return false;
        r = nr;
        c = nc;
        return true;
    }

    public void turnLeft() {
        dir = (dir + 3) % 4;
    }

    public void turnRight() {
        dir = (dir + 1) % 4;
    }

    public void clean() {
        cleaned.add(r + "," + c);
    }
}

class Solution {
    public static void main(String[] args) {
        int[][] room = {
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1}};
        int open = 0;
        for (int[] row : room) for (int v : row) open += v;

        MockRobot robot = new MockRobot(room, 1, 3);
        new Solution().cleanRoom(robot);
        print("cleaned " + robot.cleaned.size() + " of " + open + " open cells"); // cleaned 30 of 30 open cells
    }

    // Clockwise order: up, right, down, left (relative to the start orientation).
    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        dfs(robot, new HashSet<>(), 0, 0, 0);
    }

    private void dfs(Robot robot, Set<String> visited, int r, int c, int dir) {
        visited.add(r + "," + c);
        robot.clean();

        for (int k = 0; k < 4; k++) {
            int nd = (dir + k) % 4; // the robot is currently facing direction nd
            int nr = r + DIRS[nd][0], nc = c + DIRS[nd][1];

            if (!visited.contains(nr + "," + nc) && robot.move()) {
                dfs(robot, visited, nr, nc, nd);
                goBack(robot); // return to (r, c) and restore the facing direction
            }
            robot.turnRight(); // face the next direction (after 4 turns we face `dir` again)
        }
    }

    /** Step back into the previous cell and end up facing the original way. */
    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
