package neetcode150.problems.p431;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 431. Encode N-ary Tree to Binary Tree  [Hard, Premium]
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 *
 * Problem:
 *   Design encode(naryRoot) -> binaryRoot and decode(binaryRoot) -> naryRoot that are inverses.
 *
 * Example:
 *   N-ary 1 -> [3 -> [5, 6], 2, 4]  encodes to a binary tree and decodes back to the same tree.
 *
 * Solution - "left-child, right-sibling" representation:
 *   For an N-ary node:
 *     binary.left  = encoding of its FIRST child
 *     each child's binary.right = encoding of its NEXT sibling
 *   So a node's whole children list becomes a chain hanging off binary.left going via .right.
 *   decode reverses it: the children of a binary node are found by taking .left, then following
 *   .right pointers, decoding each one recursively.
 *   Time: O(n)   Space: O(h)
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}

class Codec {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node three = new Node(3);
        three.children.add(new Node(5));
        three.children.add(new Node(6));
        root.children.add(three);
        root.children.add(new Node(2));
        root.children.add(new Node(4));

        Codec codec = new Codec();
        TreeNode encoded = codec.encode(root);
        print(encoded); // binary tree in level-order
        Node decoded = codec.decode(encoded);
        print(show(decoded)); // 1(3(5,6),2,4)
        print(show(root).equals(show(decoded))); // true
    }

    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        if (!root.children.isEmpty()) {
            // First child hangs on .left; the rest chain along .right of the previous sibling.
            TreeNode sibling = encode(root.children.get(0));
            node.left = sibling;
            for (int i = 1; i < root.children.size(); i++) {
                sibling.right = encode(root.children.get(i));
                sibling = sibling.right;
            }
        }
        return node;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) return null;
        Node node = new Node(root.val);
        // Walk the sibling chain starting at .left.
        for (TreeNode child = root.left; child != null; child = child.right) {
            node.children.add(decode(child));
        }
        return node;
    }

    /** Compact text form of an n-ary tree, used only by the local main. */
    private static String show(Node n) {
        StringBuilder sb = new StringBuilder().append(n.val);
        if (!n.children.isEmpty()) {
            StringJoiner sj = new StringJoiner(",", "(", ")");
            for (Node c : n.children) sj.add(show(c));
            sb.append(sj);
        }
        return sb.toString();
    }
}
