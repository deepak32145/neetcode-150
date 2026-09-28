package neetcode150.problems.p102;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 102. Binary Tree Level Order Traversal  [Medium]  -  Trees
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Problem:
 *   Return node values level by level, left to right.
 *
 * Example:
 *   Input:  root = [3, 9, 20, null, null, 15, 7]
 *   Output: [[3], [9, 20], [15, 7]]
 *
 * Solution - BFS with a queue:
 *   Each loop processes exactly queue.size() nodes (one level), collects their values and
 *   enqueues their children.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().levelOrder(tree(3, 9, 20, null, null, 15, 7))); // [[3], [9, 20], [15, 7]]
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) return res;

        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}
