package neetcode150.problems.p100;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 100. Same Tree  [Easy]  -  Trees
 * https://leetcode.com/problems/same-tree/
 *
 * Problem:
 *   Check whether two binary trees have the same structure and values.
 *
 * Example:
 *   Input:  p = [1, 2, 3], q = [1, 2, 3]   Output: true
 *   Input:  p = [1, 2],    q = [1, null, 2] Output: false
 *
 * Solution - recursive DFS:
 *   Both null -> true. One null or different values -> false. Otherwise both left subtrees and
 *   both right subtrees must be the same.
 *   Time: O(n)   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isSameTree(tree(1, 2, 3), tree(1, 2, 3))); // true
        print(new Solution().isSameTree(tree(1, 2), tree(1, null, 2))); // false
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs(p, q);
    }

    private boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) return false;

        boolean left = dfs(p.left, q.left);
        boolean right = dfs(p.right, q.right);

        return left && right;
    }
}
