package neetcode150.problems.p133;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        this(0, new ArrayList<>());
    }

    public Node(int val) {
        this(val, new ArrayList<>());
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
