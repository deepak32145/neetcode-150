package neetcode150.problems.p432;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 432. All O`one Data Structure  [Hard]
 * https://leetcode.com/problems/all-oone-data-structure/
 *
 * Problem:
 *   Design AllOne with inc(key), dec(key), getMaxKey() and getMinKey(), all in O(1).
 *   dec removes the key when its count reaches 0. getMax/getMinKey return "" when empty.
 *
 * Example:
 *   inc("hello"), inc("hello"), getMaxKey() -> "hello", getMinKey() -> "hello",
 *   inc("leet"), getMaxKey() -> "hello", getMinKey() -> "leet"
 *
 * Solution - doubly linked list of count buckets + hash map key -> bucket:
 *   Buckets are ordered by count ascending between two sentinels (head = min side, tail = max
 *   side). Each bucket stores the SET of keys that currently have exactly that count. Counts only
 *   change by +/-1, so a key always moves to the ADJACENT bucket (creating it if missing):
 *     inc: bucket(count) -> bucket(count + 1)  (next)
 *     dec: bucket(count) -> bucket(count - 1)  (prev), or out of the structure when count hits 0
 *   Empty buckets are unlinked, so min/max are always head.next / tail.prev.
 *   Every operation touches O(1) buckets.
 */
class AllOne {
    public static void main(String[] args) {
        AllOne a = new AllOne();
        a.inc("hello");
        a.inc("hello");
        print(a.getMaxKey()); // hello
        print(a.getMinKey()); // hello
        a.inc("leet");
        print(a.getMaxKey()); // hello
        print(a.getMinKey()); // leet
        a.dec("hello");
        a.dec("hello");
        print(a.getMaxKey()); // leet
    }

    private static class Bucket {
        final int count;
        final Set<String> keys = new LinkedHashSet<>();
        Bucket prev, next;

        Bucket(int count) {
            this.count = count;
        }
    }

    private final Bucket head = new Bucket(0);  // sentinel before the smallest count
    private final Bucket tail = new Bucket(0);  // sentinel after the largest count
    private final Map<String, Bucket> where = new HashMap<>();

    public AllOne() {
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        Bucket cur = where.get(key);
        if (cur == null) {
            // New key goes to the count-1 bucket, right after head.
            Bucket one = head.next.count == 1 && head.next != tail ? head.next : insertAfter(head, 1);
            one.keys.add(key);
            where.put(key, one);
            return;
        }
        Bucket nextBucket = cur.next;
        if (nextBucket == tail || nextBucket.count != cur.count + 1) {
            nextBucket = insertAfter(cur, cur.count + 1);
        }
        nextBucket.keys.add(key);
        where.put(key, nextBucket);
        removeKey(cur, key);
    }

    public void dec(String key) {
        Bucket cur = where.get(key);
        if (cur == null) return;

        if (cur.count == 1) {
            where.remove(key); // count reaches 0: key leaves the structure
        } else {
            Bucket prevBucket = cur.prev;
            if (prevBucket == head || prevBucket.count != cur.count - 1) {
                prevBucket = insertAfter(cur.prev, cur.count - 1);
            }
            prevBucket.keys.add(key);
            where.put(key, prevBucket);
        }
        removeKey(cur, key);
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    private void removeKey(Bucket b, String key) {
        b.keys.remove(key);
        if (b.keys.isEmpty()) { // unlink empty buckets so head.next / tail.prev stay meaningful
            b.prev.next = b.next;
            b.next.prev = b.prev;
        }
    }

    private Bucket insertAfter(Bucket node, int count) {
        Bucket b = new Bucket(count);
        b.prev = node;
        b.next = node.next;
        node.next.prev = b;
        node.next = b;
        return b;
    }
}
