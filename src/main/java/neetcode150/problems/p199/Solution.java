package neetcode150.problems.p199;

import neetcode150.common.TreeNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 199. Binary Tree Right Side View  [Medium]  -  Trees
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Problem:
 *   Standing to the right of the tree, return the values you can see from top to bottom (the
 *   rightmost node of each level).
 *
 * Example:
 *   Input:  root = [1, 2, 3, null, 5, null, 4]
 *   Output: [1, 3, 4]
 *
 * Solution - BFS, right child first:
 *   Traverse level by level, enqueueing right before left. The first node polled on each
 *   level is the rightmost one.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().rightSideView(tree(1, 2, 3, null, 5, null, 4))); // [1, 3, 4]
        print(new Solution().rightSideView(tree(1, null, 3))); // [1, 3]
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) return list;
        bfs(list, root);
        return list;
    }

    public void bfs(List<Integer> list, TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = q.poll();
                if (i == 0) list.add(cur.val);
                if (cur.right != null) q.offer(cur.right);
                if (cur.left != null) q.offer(cur.left);
            }
        }
    }
}
