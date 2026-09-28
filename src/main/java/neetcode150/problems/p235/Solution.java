package neetcode150.problems.p235;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 235. Lowest Common Ancestor of a Binary Search Tree  [Medium]  -  Trees
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Problem:
 *   Given a BST and two of its nodes p and q, return their lowest common ancestor.
 *
 * Example:
 *   Input:  root = [6, 2, 8, 0, 4, 7, 9, null, null, 3, 5], p = 2, q = 8
 *   Output: 6
 *
 * Solution - follow the BST property:
 *   If both values are smaller than root, the LCA is in the left subtree; if both are larger,
 *   in the right subtree. Otherwise they split here (or root is one of them) -> root is the LCA.
 *   Time: O(h)   Space: O(h) recursion
 */
class Solution {
    public static void main(String[] args) {
        TreeNode root = tree(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        print(new Solution().lowestCommonAncestor(root, find(root, 2), find(root, 8)).val); // 6
        print(new Solution().lowestCommonAncestor(root, find(root, 2), find(root, 4)).val); // 2
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (p.val == root.val || q.val == root.val) {
            return root;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } 
        
        return root;
    }
}
