package neetcode150.problems.p206;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 206. Reverse Linked List  [Easy]  -  Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Problem:
 *   Reverse a singly linked list.
 *
 * Example:
 *   Input:  [1, 2, 3, 4, 5]
 *   Output: [5, 4, 3, 2, 1]
 *
 * Solution - iterative pointer reversal:
 *   Walk the list; save next, point current.next at previous, then advance previous and
 *   current. previous ends as the new head.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().reverseList(list(1, 2, 3, 4, 5))); // [5, 4, 3, 2, 1]
        print(new Solution().reverseList(list(1, 2))); // [2, 1]
    }

    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode previous = null;
        ListNode nextCurrent = null;
    
        while (current != null) {
            nextCurrent = current.next;
            current.next = previous;
            previous = current;
            current = nextCurrent;
        }

        return previous;
    }
}

//Recursive version
