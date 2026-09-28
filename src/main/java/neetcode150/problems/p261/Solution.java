package neetcode150.problems.p261;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 261. Graph Valid Tree  [Medium]  -  Graphs   (LeetCode premium / LintCode 178)
 * https://leetcode.com/problems/graph-valid-tree/
 *
 * Problem:
 *   Given n nodes (0..n-1) and undirected edges, do they form a valid tree (connected, no
 *   cycles)?
 *
 * Example:
 *   Input:  n = 5, edges = [[0, 1], [0, 2], [0, 3], [1, 4]]           Output: true
 *   Input:  n = 5, edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]   Output: false
 *
 * Solution - DFS that ignores the edge back to the parent:
 *   Build an adjacency list and DFS from one node. Meeting an already visited node (other than
 *   the parent) means a cycle. Also every node must be visited (connected).
 *   Time: O(V + E)   Space: O(V + E)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}})); // true
        print(new Solution().validTree(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})); // false
    }

    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    public boolean validTree(int n, int[][] edges) {
        if (n == 0 || n == 1) return true;

        if (edges.length == 0) return false;

        for (var edge : edges) {
            var node1 = edge[0];
            var node2 = edge[1];
            adjacencyList.putIfAbsent(node1, new ArrayList<>());
            adjacencyList.putIfAbsent(node2, new ArrayList<>());
            adjacencyList.get(node1).add(node2);
            adjacencyList.get(node2).add(node1);
        }

        Set<Integer> visited = new HashSet<>();

        return (
            depthFirstSearch(edges[0][0], -1, visited) && visited.size() == n
        );
    }

    private boolean depthFirstSearch(
        int node,
        int previous,
        Set<Integer> visited
    ) {
        if (visited.contains(node)) return false;

        visited.add(node);

        for (var neighbor : adjacencyList.get(node)) {
            if (neighbor == previous) continue;

            if (!depthFirstSearch(neighbor, node, visited)) return false;
        }

        return true;
    }
}
