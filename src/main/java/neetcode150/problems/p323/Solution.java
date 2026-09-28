package neetcode150.problems.p323;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 323. Number of Connected Components in an Undirected Graph  [Medium]  -  Graphs  (LeetCode premium)
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 *
 * Problem:
 *   Given n nodes and undirected edges, return the number of connected components.
 *
 * Example:
 *   Input:  n = 5, edges = [[0, 1], [1, 2], [3, 4]]
 *   Output: 2
 *
 * Solution - union-find (disjoint set):
 *   Start with n components. For each edge, union its endpoints; a successful union merges two
 *   components, so decrement the count. find uses path halving, union by size (rank).
 *   Time: O(n + E * alpha(n))   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); // 2
        print(new Solution().countComponents(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}})); // 1
    }

    private int[] parent;
    private int[] rank;

    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int result = n;
        for (int i = 0; i < edges.length; i++) {
            if (union(edges[i][0], edges[i][1]) == 1) {
                result--;
            }
        }

        return result;
    }

    private int find(int node) {
        int result = node;

        while (parent[result] != result) {
            parent[result] = parent[parent[result]];
            result = parent[result];
        }

        return result;
    }

    private int union(int n1, int n2) {
        int p1 = this.find(n1);
        int p2 = this.find(n2);

        if (p1 == p2) {
            return 0;
        }

        if (rank[p2] > rank[p1]) {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        } else {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        }

        return 1;
    }
}
