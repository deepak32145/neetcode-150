package neetcode150.problems.p2;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 2. Add Two Numbers  [Medium]  -  Linked List
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Problem:
 *   Two non-negative numbers are stored as linked lists with digits in reverse order
 *   (ones digit first). Return their sum as a linked list in the same format.
 *
 * Example:
 *   Input:  l1 = [2, 4, 3], l2 = [5, 6, 4]      (342 + 465)
 *   Output: [7, 0, 8]                           (807)
 *
 * Solution - digit-by-digit addition with carry:
 *   Move through both lists together, adding the two digits (0 if a list ended) plus the
 *   carry q. Append sum % 10 as a new node, keep sum / 10 as the next carry. Keep going
 *   while either list or the carry remains.
 *   Note: this version walks to the tail for every append, so it is O(n^2); keeping a
 *   tail pointer (or a dummy head) makes it O(n).
 *   Time: O(n^2) as written   Space: O(n) for the result
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().addTwoNumbers(list(2, 4, 3), list(5, 6, 4))); // [7, 0, 8]
        print(new Solution().addTwoNumbers(list(9, 9, 9, 9, 9, 9, 9), list(9, 9, 9, 9))); // [8, 9, 9, 9, 0, 0, 0, 1]
    }

    public ListNode addTwoNumbers(ListNode first, ListNode second) {
        int q = 0;
        int r = 0;
        int sum = 0;
        ListNode head = null;
        ListNode temp = null;
        while (first != null || second != null || q>0) {
            sum =
                q +
                (
                    ((first != null) ? first.val : 0) +
                    ((second != null) ? second.val : 0)
                );
            r = sum % 10;
            q = sum / 10;
            ListNode newNode = new ListNode(r);
            if (head == null) {
                head = newNode;
            } else {
                temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.next = null;
            }
            if (first != null) {
                first = first.next;
            }
            if (second != null) {
                second = second.next;
            }
        }
        return head;
    }
}
