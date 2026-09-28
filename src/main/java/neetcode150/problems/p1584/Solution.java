package neetcode150.problems.p1584;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1584. Min Cost to Connect All Points  [Medium]  -  Advanced Graphs
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 *
 * Problem:
 *   Connecting two points costs their Manhattan distance. Return the minimum cost to connect
 *   all points (a minimum spanning tree).
 *
 * Example:
 *   Input:  points = [[0, 0], [2, 2], [3, 10], [5, 2], [7, 0]]
 *   Output: 20
 *
 * Solution - Prim's algorithm with a min-heap:
 *   Start from point 0. Repeatedly take the cheapest edge to an unvisited point, add its cost,
 *   and push edges from that point to every unvisited point.
 *   Time: O(n^2 log n)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().minCostConnectPoints(new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}})); // 20
        print(new Solution().minCostConnectPoints(new int[][] {{3, 12}, {-2, 5}, {-4, 1}})); // 18
    }

    // Time Complexity: O(N^2 log(N)) where N is the length of points. N^2 comes from the fact we need to find the distance between a currNode and every other node to pick the shortest distance. log(N) comes from Priority Queue
    // Space Complexity: O(N^2)
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // edge weight, the index of next node
        pq.offer(new int[] { 0, 0 });
        int len = points.length;
        Set<Integer> visited = new HashSet<>();
        int cost = 0;

        // When visited.size() == points.len meaning that all the nodes has been connected.
        while (visited.size() < len) {
            int[] arr = pq.poll();

            int weight = arr[0];
            int currNode = arr[1];

            if (visited.contains(currNode)) continue;

            visited.add(currNode);
            cost += weight;

            for (int nextNode = 0; nextNode < len; nextNode++) {
                if (!visited.contains(nextNode)) {
                    int nextWeight =
                        Math.abs(points[nextNode][0] - points[currNode][0]) +
                        Math.abs(points[nextNode][1] - points[currNode][1]);
                    pq.offer(new int[] { nextWeight, nextNode });
                }
            }
        }

        return cost;
    }
}
