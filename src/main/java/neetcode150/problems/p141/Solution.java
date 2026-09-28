package neetcode150.problems.p141;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 141. Linked List Cycle  [Easy]  -  Linked List
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * Problem:
 *   Does the linked list contain a cycle?
 *
 * Example:
 *   Input:  head = [3, 2, 0, -4], tail connects to index 1
 *   Output: true
 *
 * Solution - Floyd's tortoise and hare:
 *   slow moves 1 step, fast moves 2. If there is a cycle fast eventually meets slow; if fast
 *   reaches null there is no cycle.
 *   Time: O(n)   Space: O(1)
 */
public class Solution {
    public static void main(String[] args) {
        ListNode head = list(3, 2, 0, -4);
        head.next.next.next.next = head.next; // tail -> node at index 1
        print(new Solution().hasCycle(head)); // true
        print(new Solution().hasCycle(list(1))); // false
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
