package neetcode150.problems.p124;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 124. Binary Tree Maximum Path Sum  [Hard]  -  Trees
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Problem:
 *   A path is any sequence of connected nodes (no node repeated, it need not pass the root).
 *   Return the maximum sum of any non-empty path.
 *
 * Example:
 *   Input:  root = [-10, 9, 20, null, null, 15, 7]
 *   Output: 42                (15 -> 20 -> 7)
 *
 * Solution - post-order DFS:
 *   For each node compute the best downward gain of each child (negative gains become 0).
 *   A path that bends at this node is val + left + right - update the global max with it.
 *   Return val + max(left, right) to the parent (a path can only go down one side).
 *   Time: O(n)   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxPathSum(tree(1, 2, 3))); // 6
        print(new Solution().maxPathSum(tree(-10, 9, 20, null, null, 15, 7))); // 42
    }

    public int maxPathSum(TreeNode root) {
        int[] res = { Integer.MIN_VALUE };
        maxPathSum(root, res);
        return res[0];
    }

    public int maxPathSum(TreeNode root, int[] res) {
        if (root == null) return 0;

        int left = Math.max(0, maxPathSum(root.left, res));
        int right = Math.max(0, maxPathSum(root.right, res));
        res[0] = Math.max(res[0], root.val + left + right);

        return root.val + Math.max(left, right);
    }
}
