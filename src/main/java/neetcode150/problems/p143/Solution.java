package neetcode150.problems.p143;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 143. Reorder List  [Medium]  -  Linked List
 * https://leetcode.com/problems/reorder-list/
 *
 * Problem:
 *   Reorder L0 -> L1 -> ... -> Ln into L0 -> Ln -> L1 -> Ln-1 -> ... in place.
 *
 * Example:
 *   Input:  [1, 2, 3, 4, 5]
 *   Output: [1, 5, 2, 4, 3]
 *
 * Solution - three steps:
 *   1) Find the middle with slow / fast pointers and cut the list there.
 *   2) Reverse the second half.
 *   3) Merge the halves by alternating nodes.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        ListNode a = list(1, 2, 3, 4);
        new Solution().reorderList(a);
        print(a); // [1, 4, 2, 3]
        ListNode b = list(1, 2, 3, 4, 5);
        new Solution().reorderList(b);
        print(b); // [1, 5, 2, 4, 3]
    }

    public void reorderList(ListNode head) {
        
        //Find middle of list using a slow and fast pointer approach
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //Reverse the second half of the list using a tmp variable
        ListNode second = slow.next;
        ListNode prev = slow.next = null;
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }

        //Re-assign the pointers to match the pattern
        ListNode first = head;
        second = prev;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }
}
