package neetcode150.problems.p460;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 460. LFU Cache  [Hard]
 * https://leetcode.com/problems/lfu-cache/
 *
 * Problem:
 *   Design a Least Frequently Used cache with get(key) and put(key, value) in O(1). When full,
 *   evict the least frequently used key; ties are broken by evicting the least RECENTLY used.
 *
 * Example:
 *   LFUCache(2): put(1,1) put(2,2) get(1)=1 put(3,3) [evicts 2] get(2)=-1 get(3)=3
 *                put(4,4) [evicts 1] get(1)=-1 get(3)=3 get(4)=4
 *
 * Solution - three hash maps plus a running minimum frequency:
 *   values:  key -> value
 *   freqOf:  key -> use count
 *   buckets: use count -> keys with that count, in insertion order (LinkedHashSet gives O(1)
 *            add/remove and "oldest first" iteration, which is exactly LRU within a frequency)
 *   minFreq: smallest frequency that currently has a key.
 *   Touching a key moves it from bucket f to bucket f+1; if bucket f was minFreq and is now
 *   empty, minFreq becomes f+1. Eviction removes the first key of buckets[minFreq]. Inserting a
 *   new key always resets minFreq to 1.
 */
class LFUCache {
    public static void main(String[] args) {
        LFUCache c = new LFUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        print(c.get(1)); // 1
        c.put(3, 3);     // evicts key 2
        print(c.get(2)); // -1
        print(c.get(3)); // 3
        c.put(4, 4);     // evicts key 1 (freq tie with 3, but 1 is older)
        print(c.get(1)); // -1
        print(c.get(3)); // 3
        print(c.get(4)); // 4
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Integer> values = new HashMap<>();
    private final Map<Integer, Integer> freqOf = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> buckets = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) return -1;
        touch(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (values.containsKey(key)) {
            values.put(key, value);
            touch(key);
            return;
        }

        if (values.size() == capacity) {
            // Evict the least frequently used key; within that frequency, the oldest one.
            LinkedHashSet<Integer> lowest = buckets.get(minFreq);
            int evict = lowest.iterator().next();
            lowest.remove(evict);
            if (lowest.isEmpty()) buckets.remove(minFreq);
            values.remove(evict);
            freqOf.remove(evict);
        }

        values.put(key, value);
        freqOf.put(key, 1);
        buckets.computeIfAbsent(1, f -> new LinkedHashSet<>()).add(key);
        minFreq = 1; // a brand-new key is always the least frequently used
    }

    /** Record one more use of key: move it from bucket f to bucket f + 1. */
    private void touch(int key) {
        int f = freqOf.get(key);
        LinkedHashSet<Integer> bucket = buckets.get(f);
        bucket.remove(key);
        if (bucket.isEmpty()) {
            buckets.remove(f);
            if (minFreq == f) minFreq = f + 1; // the minimum bucket emptied, key now lives one higher
        }
        freqOf.put(key, f + 1);
        buckets.computeIfAbsent(f + 1, x -> new LinkedHashSet<>()).add(key); // most recent at the end
    }
}
