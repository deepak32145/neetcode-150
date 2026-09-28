package neetcode150.problems.p1448;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1448. Count Good Nodes in Binary Tree  [Medium]  -  Trees
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 *
 * Problem:
 *   A node is good if no node on the path from the root to it has a greater value. Count them.
 *
 * Example:
 *   Input:  root = [3, 1, 4, 3, null, 1, 5]
 *   Output: 4                 (3, 4, 3 and 5)
 *
 * Solution - DFS carrying the max seen so far:
 *   A node is good if node.val >= max on its path. Recurse into children with
 *   max(max, node.val).
 *   Time: O(n)   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().goodNodes(tree(3, 1, 4, 3, null, 1, 5))); // 4
        print(new Solution().goodNodes(tree(3, 3, null, 4, 2))); // 3
        print(new Solution().goodNodes(tree(1))); // 1
    }

    public int goodNodes(TreeNode root) {
        return helper(root, -99999);
    }

    public int helper(TreeNode root, int max) {
        if (root == null) return 0;

        int res = root.val >= max ? 1 : 0;

        res += helper(root.left, Math.max(root.val, max));
        res += helper(root.right, Math.max(root.val, max));

        return res;
    }
}
