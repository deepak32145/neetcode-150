package neetcode150.common;

import java.util.*;

/** Helpers for the per-problem main methods: build inputs and pretty-print outputs. */
public final class Util {

    private Util() {}

    /** Builds a linked list: list(1, 2, 3) -> 1 -> 2 -> 3. */
    public static ListNode list(int... vals) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    /** Builds a binary tree from LeetCode level-order notation: tree(3, 9, 20, null, null, 15, 7). */
    public static TreeNode tree(Integer... vals) {
        if (vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode node = q.poll();
            if (i < vals.length && vals[i] != null) {
                node.left = new TreeNode(vals[i]);
                q.add(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                node.right = new TreeNode(vals[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    /** Finds the first node with the given value (for problems that take node references). */
    public static TreeNode find(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        TreeNode left = find(root.left, val);
        return left != null ? left : find(root.right, val);
    }

    /** Builds a char grid from rows: board("11000", "11010", "00000"). */
    public static char[][] board(String... rows) {
        char[][] b = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) b[i] = rows[i].toCharArray();
        return b;
    }

    public static void print(Object o) {
        System.out.println(str(o));
    }

    public static String str(Object o) {
        if (o == null) return "null";
        if (o instanceof ListNode node) {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            for (int guard = 0; node != null && guard < 1000; node = node.next, guard++) sj.add(String.valueOf(node.val));
            return sj.toString();
        }
        if (o instanceof TreeNode root) return treeStr(root);
        if (o instanceof int[] a) return Arrays.toString(a);
        if (o instanceof long[] a) return Arrays.toString(a);
        if (o instanceof double[] a) return Arrays.toString(a);
        if (o instanceof boolean[] a) return Arrays.toString(a);
        if (o instanceof char[] a) return Arrays.toString(a);
        if (o instanceof Object[] a) {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            for (Object x : a) sj.add(str(x));
            return sj.toString();
        }
        if (o instanceof Collection<?> c) {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            for (Object x : c) sj.add(str(x));
            return sj.toString();
        }
        if (o instanceof String s) return "\"" + s + "\"";
        return String.valueOf(o);
    }

    /** Level-order with nulls, trailing nulls trimmed (LeetCode style). */
    private static String treeStr(TreeNode root) {
        List<String> out = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                out.add("null");
                continue;
            }
            out.add(String.valueOf(n.val));
            q.add(n.left);
            q.add(n.right);
        }
        int end = out.size();
        while (end > 0 && out.get(end - 1).equals("null")) end--;
        return "[" + String.join(", ", out.subList(0, end)) + "]";
    }
}
