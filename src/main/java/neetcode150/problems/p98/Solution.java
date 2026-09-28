package neetcode150.problems.p98;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 98. Validate Binary Search Tree  [Medium]  -  Trees
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Problem:
 *   Check whether a binary tree is a valid BST (left subtree < node < right subtree, strictly,
 *   for every node).
 *
 * Example:
 *   Input:  root = [5, 1, 4, null, null, 3, 6]
 *   Output: false             (4 is in 5's right subtree but is smaller than 5)
 *
 * Solution - DFS with (min, max) bounds:
 *   Each node must lie strictly inside its allowed range. Going left the max becomes node.val;
 *   going right the min becomes node.val. null bounds mean unbounded.
 *   Time: O(n)   Space: O(h) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isValidBST(tree(2, 1, 3))); // true
        print(new Solution().isValidBST(tree(5, 1, 4, null, null, 3, 6))); // false
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        if (
            (min != null && root.val <= min) || max != null && root.val >= max
        ) {
            return false;
        }

        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
}
