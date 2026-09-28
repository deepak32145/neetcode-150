package neetcode150.problems.p230;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 230. Kth Smallest Element in a BST  [Medium]  -  Trees
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Problem:
 *   Return the k-th smallest value (1-indexed) in a binary search tree.
 *
 * Example:
 *   Input:  root = [5, 3, 6, 2, 4, null, null, 1], k = 3
 *   Output: 3
 *
 * Solution - in-order traversal:
 *   In-order visits a BST in sorted order. Collect the values in a list and return element
 *   k - 1. (An iterative in-order that stops at the k-th node needs only O(h) space.)
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().kthSmallest(tree(3, 1, 4, null, 2), 1)); // 1
        print(new Solution().kthSmallest(tree(5, 3, 6, 2, 4, null, null, 1), 3)); // 3
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
