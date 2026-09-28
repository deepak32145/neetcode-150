package neetcode150.problems.p23;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 23. Merge k Sorted Lists  [Hard]  -  Linked List / Heap
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * Problem:
 *   Merge k sorted linked lists into one sorted list.
 *
 * Example:
 *   Input:  lists = [[1, 4, 5], [1, 3, 4], [2, 6]]
 *   Output: [1, 1, 2, 3, 4, 4, 5, 6]
 *
 * Solution - min-heap of list heads:
 *   Put each non-empty head in a min-heap ordered by value. Repeatedly poll the smallest node,
 *   append it to the result, and push its next node (if any).
 *   Time: O(N log k), N = total nodes   Space: O(k)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().mergeKLists(new ListNode[] {list(1, 4, 5), list(1, 3, 4), list(2, 6)})); // [1, 1, 2, 3, 4, 4, 5, 6]
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return dummy.next;
    }
}

//  Solution using Iterative Merge Sort
//  Time Complexity:         O(n*log(k))
//  Extra Space Complexity:  O(1)
