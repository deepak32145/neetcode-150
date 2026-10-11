package neetcode150.problems.p272;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 272. Closest Binary Search Tree Value II  [Hard, Premium]
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 *
 * Problem:
 *   Return the k values in a BST closest to a target (any order).
 *
 * Example:
 *   Input:  root = [4,2,5,1,3], target = 3.714286, k = 2   Output: [3, 4]
 *
 * Solution - in-order traversal with a sliding window of size k:
 *   In-order visits values in ascending order, so the k closest values are always contiguous.
 *   Keep a deque of at most k values. For each new value x:
 *     - window not full: append it.
 *     - window full: if x is closer to target than the oldest (front) value, slide (drop front,
 *       append x). Otherwise x and every later (even larger) value is farther than the front, so
 *       stop the traversal early.
 *   Time: O(n) worst, often much less   Space: O(k + h)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.closestKValues(tree(4, 2, 5, 1, 3), 3.714286, 2)); // [3, 4]
        print(new Solution().closestKValues(tree(1), 0.0, 1)); // [1]
    }

    private final Deque<Integer> window = new ArrayDeque<>();
    private boolean done;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        window.clear();
        done = false;
        inorder(root, target, k);
        return new ArrayList<>(window);
    }

    private void inorder(TreeNode node, double target, int k) {
        if (node == null || done) return;
        inorder(node.left, target, k);
        if (done) return;

        if (window.size() < k) {
            window.addLast(node.val);
        } else if (Math.abs(node.val - target) < Math.abs(window.peekFirst() - target)) {
            window.pollFirst(); // the oldest value is now farther than the new one
            window.addLast(node.val);
        } else {
            done = true; // values only get larger from here, so they only get farther
            return;
        }
        inorder(node.right, target, k);
    }
}
