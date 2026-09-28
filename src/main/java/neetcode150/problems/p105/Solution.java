package neetcode150.problems.p105;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 105. Construct Binary Tree from Preorder and Inorder Traversal  [Medium]  -  Trees
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Problem:
 *   Rebuild a binary tree (unique values) from its preorder and inorder traversals.
 *
 * Example:
 *   Input:  preorder = [3, 9, 20, 15, 7], inorder = [9, 3, 15, 20, 7]
 *   Output: [3, 9, 20, null, null, 15, 7]
 *
 * Solution - recursive split:
 *   preorder[0] is the root. Its position `mid` in inorder splits the left subtree (mid nodes)
 *   from the right subtree. Recurse on the matching slices of both arrays (Arrays.copyOfRange).
 *   (A value -> index map plus index bounds avoids the copies and makes it O(n).)
 *   Time: O(n^2)   Space: O(n^2) for the slices
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().buildTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7})); // [3, 9, 20, null, null, 15, 7]
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        int mid = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) mid = i;
        }

        root.left =
            buildTree(
                Arrays.copyOfRange(preorder, 1, mid + 1),
                Arrays.copyOfRange(inorder, 0, mid)
            );
        root.right =
            buildTree(
                Arrays.copyOfRange(preorder, mid + 1, preorder.length),
                Arrays.copyOfRange(inorder, mid + 1, inorder.length)
            );

        return root;
    }
}

// Solution without using Array copies
