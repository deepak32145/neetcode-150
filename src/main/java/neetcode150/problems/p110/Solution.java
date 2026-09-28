package neetcode150.problems.p110;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 110. Balanced Binary Tree  [Easy]  -  Trees
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * Problem:
 *   A tree is height-balanced if for every node the heights of its two subtrees differ by at
 *   most 1. Check it.
 *
 * Example:
 *   Input:  root = [3, 9, 20, null, null, 15, 7]   Output: true
 *   Input:  root = [1, 2, 2, 3, 3, null, null, 4, 4] Output: false
 *
 * Solution - top-down height check:
 *   At each node compare height(left) and height(right), then check both children recursively.
 *   (A bottom-up DFS that returns -1 for "unbalanced" does it in one pass, O(n).)
 *   Time: O(n^2) worst, O(n log n) for balanced trees   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isBalanced(tree(3, 9, 20, null, null, 15, 7))); // true
        print(new Solution().isBalanced(tree(1, 2, 2, 3, 3, null, null, 4, 4))); // false
    }

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int lh = height(root.left);
        int rh = height(root.right);

        return 1 + Math.max(lh,rh);
    }

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
}
