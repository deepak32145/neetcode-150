package neetcode150.problems.p133;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 133. Clone Graph  [Medium]  -  Graphs
 * https://leetcode.com/problems/clone-graph/
 *
 * Problem:
 *   Return a deep copy of a connected undirected graph given one of its nodes. Each node has a
 *   value and a list of neighbours.
 *
 * Example:
 *   Input:  adjList = [[2, 4], [1, 3], [2, 4], [1, 3]]   (a square 1-2-3-4-1)
 *   Output: the same structure made of brand-new nodes
 *
 * Solution - DFS with a map original value -> copy:
 *   If a node was already copied, return its copy (this also handles cycles). Otherwise create
 *   the copy, store it in the map before visiting neighbours, then clone each neighbour.
 *   Time: O(V + E)   Space: O(V)
 */
class Solution {
    public static void main(String[] args) {
        // graph: 1 - 2 - 3 - 4 - 1
        Node[] nodes = new Node[5];
        for (int i = 1; i <= 4; i++) nodes[i] = new Node(i);
        int[][] adj = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        for (int i = 1; i <= 4; i++) {
            for (int n : adj[i - 1]) nodes[i].neighbors.add(nodes[n]);
        }
        Node copy = new Solution().cloneGraph(nodes[1]);
        print(copy != nodes[1]); // true (deep copy)
        print(copy.neighbors.stream().map(n -> n.val).toList()); // [2, 4]
    }

    public HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (map.containsKey(node.val)) return map.get(node.val);
        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(node.val, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }
}
