package neetcode150.problems.p428;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 428. Serialize and Deserialize N-ary Tree  [Hard, Premium]
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
 *
 * Problem:
 *   Design serialize(root) -> string and deserialize(string) -> root for an N-ary tree.
 *
 * Example:
 *   Tree: 1 -> [3 -> [5, 6], 2, 4]   Serialized: "1 3 3 2 5 0 6 0 2 0 4 0"
 *
 * Solution - preorder with an explicit child count:
 *   Write each node as "value childCount" followed by its children recursively (preorder). The
 *   child count removes ambiguity about where a subtree ends, so no null markers are needed.
 *   deserialize walks the token stream with a shared cursor: read value, read count, then build
 *   `count` children recursively.
 *   Time: O(n)   Space: O(n)
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
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
        String data = codec.serialize(root);
        print(data); // 1 3 3 2 5 0 6 0 2 0 4 0
        print(codec.serialize(codec.deserialize(data)).equals(data)); // true (round trip)
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        write(root, sb);
        return sb.toString().trim();
    }

    private void write(Node node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.val).append(' ').append(node.children.size()).append(' ');
        for (Node child : node.children) write(child, sb);
    }

    private int pos;

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] tokens = data.split(" ");
        pos = 0;
        return read(tokens);
    }

    private Node read(String[] tokens) {
        Node node = new Node(Integer.parseInt(tokens[pos++]));
        int count = Integer.parseInt(tokens[pos++]);
        for (int i = 0; i < count; i++) node.children.add(read(tokens));
        return node;
    }
}
