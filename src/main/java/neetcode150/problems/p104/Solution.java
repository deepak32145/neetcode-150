package neetcode150.problems.p104;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 104. Maximum Depth of Binary Tree  [Easy]  -  Trees
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Problem:
 *   Return the number of nodes on the longest root-to-leaf path.
 *
 * Example:
 *   Input:  root = [3, 9, 20, null, null, 15, 7]
 *   Output: 3
 *
 * Solution - recursive DFS:
 *   depth(null) = 0, depth(node) = 1 + max(depth(left), depth(right)).
 *   Time: O(n)   Space: O(h)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxDepth(tree(3, 9, 20, null, null, 15, 7))); // 3
        print(new Solution().maxDepth(tree(1, null, 2))); // 2
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
