package neetcode150.problems.p684;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 684. Redundant Connection  [Medium]  -  Graphs
 * https://leetcode.com/problems/redundant-connection/
 *
 * Problem:
 *   A tree with nodes 1..n got one extra edge. Return an edge whose removal leaves a tree (the
 *   last such edge in the input).
 *
 * Example:
 *   Input:  edges = [[1, 2], [1, 3], [2, 3]]
 *   Output: [2, 3]
 *
 * Solution - union-find:
 *   Process edges in order. If both endpoints already have the same root, this edge closes a
 *   cycle -> it is the answer. Otherwise union them.
 *   Time: O(n * alpha(n)) with path compression (this version is simple recursion)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findRedundantConnection(new int[][] {{1, 2}, {1, 3}, {2, 3}})); // [2, 3]
        print(new Solution().findRedundantConnection(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}})); // [1, 4]
    }

    int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length];
        for (int i = 0; i < edges.length; i++) parent[i] = i + 1;

        for (int[] edge : edges) {
            if (find(edge[0]) == find(edge[1])) return edge; else union(
                edge[0],
                edge[1]
            );
        }

        return new int[2];
    }

    public int find(int x) {
        if (x == parent[x - 1]) return x;
        return find(parent[x - 1]);
    }

    public void union(int x, int y) {
        parent[find(y) - 1] = find(x);
    }
}
