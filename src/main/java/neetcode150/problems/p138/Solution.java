package neetcode150.problems.p138;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 138. Copy List with Random Pointer  [Medium]  -  Linked List
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * Problem:
 *   Each node has next and random pointers (random may point anywhere or be null). Return a
 *   deep copy of the list.
 *
 * Example:
 *   Input:  [[7, null], [13, 0], [11, 4], [10, 2], [1, 0]]   ([val, random index])
 *   Output: an identical list made of new nodes
 *
 * Solution - two passes with a hash map:
 *   Pass 1: map every original node -> a new node with the same value.
 *   Pass 2: copy.next = map.get(orig.next), copy.random = map.get(orig.random).
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node[] n = {new Node(7), new Node(13), new Node(11), new Node(10), new Node(1)};
        for (int i = 0; i < n.length - 1; i++) n[i].next = n[i + 1];
        n[1].random = n[0];
        n[2].random = n[4];
        n[3].random = n[2];
        n[4].random = n[0];
        Node copy = new Solution().copyRandomList(n[0]);
        StringBuilder sb = new StringBuilder();
        for (Node c = copy; c != null; c = c.next) {
            sb.append("[").append(c.val).append(", random=").append(c.random == null ? "null" : c.random.val).append("] ");
        }
        print(copy != n[0]); // true (deep copy)
        System.out.println(sb); // [7, random=null] [13, random=7] [11, random=1] [10, random=11] [1, random=7]
    }

    public Node copyRandomList(Node head) {
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
