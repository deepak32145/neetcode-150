package neetcode150.problems.p207;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 207. Course Schedule  [Medium]  -  Graphs
 * https://leetcode.com/problems/course-schedule/
 *
 * Problem:
 *   prerequisites[i] = [a, b] means b must be taken before a. Can all numCourses be finished?
 *   (i.e. is the dependency graph free of cycles?)
 *
 * Example:
 *   Input:  numCourses = 2, prerequisites = [[1, 0], [0, 1]]
 *   Output: false             (0 and 1 depend on each other)
 *
 * Solution - DFS cycle detection with 3 states:
 *   visited: 0 = not visited, 2 = on the current DFS path, 1 = fully processed (safe).
 *   Reaching a node that is on the current path (2) means a cycle.
 *   Time: O(V + E)   Space: O(V + E)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().canFinish(2, new int[][] {{1, 0}})); // true
        print(new Solution().canFinish(2, new int[][] {{1, 0}, {0, 1}})); // false
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (isCyclic(adj, visited, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCyclic(List<List<Integer>> adj, int[] visited, int curr) {
        if (visited[curr] == 2) {
            return true;
        }

        visited[curr] = 2;
        for (int i = 0; i < adj.get(curr).size(); i++) {
            if (visited[adj.get(curr).get(i)] != 1) {
                if (isCyclic(adj, visited, adj.get(curr).get(i))) {
                    return true;
                }
            }
        }
        visited[curr] = 1;
        return false;
    }
}
