package neetcode150.problems.p19;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 19. Remove Nth Node From End of List  [Medium]  -  Linked List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Problem:
 *   Remove the n-th node from the end of a linked list and return the head.
 *
 * Example:
 *   Input:  head = [1, 2, 3, 4, 5], n = 2
 *   Output: [1, 2, 3, 5]
 *
 * Solution - two pointers with a gap of n:
 *   Start both pointers at a dummy node before head. Move `second` n steps ahead, then move
 *   both until second reaches the last node. Now `first` sits right before the node to
 *   delete: first.next = first.next.next.
 *   Time: O(L) single pass   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().removeNthFromEnd(list(1, 2, 3, 4, 5), 2)); // [1, 2, 3, 5]
        print(new Solution().removeNthFromEnd(list(1, 2), 1)); // [1]
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;

        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode first = temp, second = temp;

        while (n > 0) {
            second = second.next;
            n--;
        }

        while (second.next != null) {
            second = second.next;
            first = first.next;
        }

        first.next = first.next.next;
        return temp.next;
    }
}
