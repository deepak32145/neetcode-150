package neetcode150.problems.p25;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 25. Reverse Nodes in k-Group  [Hard]  -  Linked List
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Problem:
 *   Reverse the nodes of a linked list k at a time. A final group with fewer than k nodes stays
 *   as it is.
 *
 * Example:
 *   Input:  head = [1, 2, 3, 4, 5], k = 2
 *   Output: [2, 1, 4, 3, 5]
 *
 * Solution - recursion per group:
 *   Check that k nodes exist. If so, recursively reverse everything after this group first;
 *   that result becomes the tail target. Then reverse the current k nodes onto it one by one.
 *   If fewer than k nodes exist, return head unchanged.
 *   Time: O(n)   Space: O(n / k) recursion stack
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().reverseKGroup(list(1, 2, 3, 4, 5), 2)); // [2, 1, 4, 3, 5]
        print(new Solution().reverseKGroup(list(1, 2, 3, 4, 5), 3)); // [3, 2, 1, 4, 5]
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            cur = reverseKGroup(cur, k);
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
            }
            head = cur;
        }
        return head;
    }
}

// An iterative approach

// Time Complexity: O(n)
// Extra Space Complexity: O(1)
