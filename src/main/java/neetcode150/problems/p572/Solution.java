package neetcode150.problems.p572;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 572. Subtree of Another Tree  [Easy]  -  Trees
 * https://leetcode.com/problems/subtree-of-another-tree/
 *
 * Problem:
 *   Is there a node in root whose subtree is identical to subRoot?
 *
 * Example:
 *   Input:  root = [3, 4, 5, 1, 2], subRoot = [4, 1, 2]
 *   Output: true
 *
 * Solution - "same tree" check at every node:
 *   If the tree at root equals subRoot, done; otherwise try root.left and root.right.
 *   Time: O(m * n)   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isSubtree(tree(3, 4, 5, 1, 2), tree(4, 1, 2))); // true
        print(new Solution().isSubtree(tree(3, 4, 5, 1, 2, null, null, null, null, 0), tree(4, 1, 2))); // false
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null || isSameTree(root, subRoot)) return true;
        if (root == null) return false;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
    
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
