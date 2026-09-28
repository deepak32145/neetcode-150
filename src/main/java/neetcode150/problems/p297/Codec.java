package neetcode150.problems.p297;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 297. Serialize and Deserialize Binary Tree  [Hard]  -  Trees
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Problem:
 *   Design serialize(tree) -> string and deserialize(string) -> tree so the tree round-trips.
 *
 * Example:
 *   Input:  root = [1, 2, 3, null, null, 4, 5]
 *   Serialized: "1,2,N,N,3,4,N,N,5,N,N"   ->   deserializes back to the same tree
 *
 * Solution - preorder DFS with null markers:
 *   serialize writes node values in preorder and "N" for every null child, comma separated.
 *   deserialize reads the tokens in the same order with a shared index i: "N" -> null,
 *   otherwise create the node and build its left, then right subtree.
 *   Time: O(n)   Space: O(n)
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    public static void main(String[] args) {
        Codec codec = new Codec();
        String data = codec.serialize(tree(1, 2, 3, null, null, 4, 5));
        print(data);
        print(codec.deserialize(data)); // [1, 2, 3, null, null, 4, 5]
    }

    private int i;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        serializeDFS(root, list);

        return String.join(",", list);
    }

    private void serializeDFS(TreeNode root, List<String> list) {
        if (root == null) {
            list.add("N");
            return;
        }
        list.add(String.valueOf(root.val));
        serializeDFS(root.left, list);
        serializeDFS(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        return deserializeDFS(tokens);
    }

    private TreeNode deserializeDFS(String[] tokens) {
        String token = tokens[this.i];
        if (token.equals("N")) {
            this.i++;
            return null;
        }
        var node = new TreeNode(Integer.parseInt(token));
        this.i++;
        node.left = deserializeDFS(tokens);
        node.right = deserializeDFS(tokens);
        return node;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
