package neetcode150.problems.p146;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 146. LRU Cache  [Medium]  -  Linked List
 * https://leetcode.com/problems/lru-cache/
 *
 * Problem:
 *   Design a Least Recently Used cache with a fixed capacity where get(key) and put(key, value)
 *   both run in O(1). When full, put evicts the least recently used key.
 *
 * Example:
 *   LRUCache(2); put(1,1); put(2,2); get(1) -> 1; put(3,3) evicts 2; get(2) -> -1;
 *   put(4,4) evicts 1; get(1) -> -1; get(3) -> 3; get(4) -> 4
 *
 * Solution - hash map + doubly linked list:
 *   The map gives key -> node in O(1). The list is ordered by recency: left sentinel side =
 *   least recent, right sentinel side = most recent. get/put move the node to the right end;
 *   when size > capacity, remove the node right after the left sentinel.
 *   Time: O(1) per operation   Space: O(capacity)
 */
class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        print(cache.get(1)); // 1
        cache.put(3, 3); // evicts key 2
        print(cache.get(2)); // -1
        cache.put(4, 4); // evicts key 1
        print(cache.get(1)); // -1
        print(cache.get(3)); // 3
        print(cache.get(4)); // 4
    }

    private Map<Integer, Node> cache;
    private int capacity;

    private Node left;
    private Node right;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        //left = LRU , right = most recent
        this.left = new Node(0, 0);
        this.right = new Node(0, 0);
        this.left.next = this.right;
        this.right.prev = this.left;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
            insert(cache.get(key));
            return cache.get(key).val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity) {
            // remove from the list and delte the LRU from the hashmap
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    // remove node from list
    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // insert node at right
    public void insert(Node node) {
        Node prev = this.right.prev;
        Node next = this.right;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    private class Node {

        private int key;
        private int val;

        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
