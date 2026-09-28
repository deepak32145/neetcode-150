package neetcode150.problems.p226;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 226. Invert Binary Tree  [Easy]  -  Trees
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Problem:
 *   Mirror a binary tree (swap every node's left and right children).
 *
 * Example:
 *   Input:  [4, 2, 7, 1, 3, 6, 9]
 *   Output: [4, 7, 2, 9, 6, 3, 1]
 *
 * Solution - recursion:
 *   Build a new node whose right child is the inverted left subtree and whose left child is the
 *   inverted right subtree. (Swapping children in place works the same way.)
 *   Time: O(n)   Space: O(n) for the new tree, O(h) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().invertTree(tree(4, 2, 7, 1, 3, 6, 9))); // [4, 7, 2, 9, 6, 3, 1]
        print(new Solution().invertTree(tree(2, 1, 3))); // [2, 3, 1]
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        node.right = invertTree(root.left);
        node.left = invertTree(root.right);
        return node;
    }
}
