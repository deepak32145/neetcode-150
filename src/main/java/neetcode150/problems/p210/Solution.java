package neetcode150.problems.p210;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 210. Course Schedule II  [Medium]  -  Graphs
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * Problem:
 *   Return an order in which to take all courses given the prerequisites, or [] if impossible.
 *
 * Example:
 *   Input:  numCourses = 4, prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
 *   Output: [0, 1, 2, 3]      ([0, 2, 1, 3] is also valid)
 *
 * Solution - Kahn's algorithm (BFS topological sort):
 *   Build edges pre -> post and count each course's indegree. Start with all indegree-0
 *   courses; each time one is taken, decrement its dependents and enqueue those that reach 0.
 *   If fewer than numCourses were taken there is a cycle -> [].
 *   Time: O(V + E)   Space: O(V + E)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findOrder(2, new int[][] {{1, 0}})); // [0, 1]
        print(new Solution().findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}})); // [0, 1, 2, 3] or [0, 2, 1, 3]
        print(new Solution().findOrder(2, new int[][] {{1, 0}, {0, 1}})); // []
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        //creating the adjlist
        for (int i = 0; i < prerequisites.length; i++) {
            int post = prerequisites[i][0];
            int pre = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(
                pre,
                new ArrayList<Integer>()
            );
            lst.add(post);
            adjList.put(pre, lst);

            indegree[post] += 1;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        if (i == numCourses) {
            return topologicalOrder;
        }

        return new int[0];
    }
}
