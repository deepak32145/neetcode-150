package neetcode150.problems.p543;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 543. Diameter of Binary Tree  [Easy]  -  Trees
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Problem:
 *   Return the number of edges on the longest path between any two nodes.
 *
 * Example:
 *   Input:  root = [1, 2, 3, 4, 5]
 *   Output: 3                 (4 -> 2 -> 1 -> 3)
 *
 * Solution - DFS returning heights:
 *   dfs returns the height in edges (null = -1). At each node the longest path through it is
 *   (1 + left height) + (1 + right height); keep the max.
 *   Time: O(n)   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().diameterOfBinaryTree(tree(1, 2, 3, 4, 5))); // 3
        print(new Solution().diameterOfBinaryTree(tree(1, 2))); // 1
    }

    int result = -1;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return result;
    }

    private int dfs(TreeNode current) {
        if (current == null) {
            return -1;
        }
        int left = 1 + dfs(current.left);
        int right = 1 + dfs(current.right);
        result = Math.max(result, (left + right));
        return Math.max(left, right);
    }
}
