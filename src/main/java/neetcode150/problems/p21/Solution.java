package neetcode150.problems.p21;

import neetcode150.common.ListNode;
import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 21. Merge Two Sorted Lists  [Easy]  -  Linked List
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Problem:
 *   Merge two sorted linked lists into one sorted list by splicing their nodes together.
 *
 * Example:
 *   Input:  list1 = [1, 2, 4], list2 = [1, 3, 4]
 *   Output: [1, 1, 2, 3, 4, 4]
 *
 * Solution - dummy head + iterative merge:
 *   Keep a tail pointer `prev` starting at a dummy node. Attach the smaller of the two current
 *   heads and advance that list. When one list runs out, attach the rest of the other.
 *   Time: O(m + n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().mergeTwoLists(list(1, 2, 4), list(1, 3, 4))); // [1, 1, 2, 3, 4, 4]
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        final ListNode root = new ListNode();
        ListNode prev = root;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 != null ? list1 : list2;
        return root.next;
    }
}

// Solution using Recursion
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
