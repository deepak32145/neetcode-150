package neetcode150.problems.p981;

import java.util.*;
import neetcode150.common.Pair;
import static neetcode150.common.Util.*;

/*
 * 981. Time Based Key-Value Store  [Medium]  -  Binary Search
 * https://leetcode.com/problems/time-based-key-value-store/
 *
 * Problem:
 *   set(key, value, timestamp) stores a value; get(key, timestamp) returns the value with the
 *   largest timestamp <= the given one, or "".
 *
 * Example:
 *   set("foo", "bar", 1); get("foo", 1) -> "bar"; get("foo", 3) -> "bar";
 *   set("foo", "bar2", 4); get("foo", 4) -> "bar2"; get("foo", 5) -> "bar2"
 *
 * Solution - map of key -> list of (value, timestamp) + binary search:
 *   Timestamps arrive in increasing order, so each list is already sorted. get binary-searches
 *   for the last entry with timestamp <= the query.
 *   Time: set O(1), get O(log n)   Space: O(n)
 */
class TimeMap {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        print(timeMap.get("foo", 1)); // "bar"
        print(timeMap.get("foo", 3)); // "bar"
        timeMap.set("foo", "bar2", 4);
        print(timeMap.get("foo", 4)); // "bar2"
        print(timeMap.get("foo", 5)); // "bar2"
    }

    HashMap<String, List<Pair<String, Integer>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) map.put(key, new ArrayList<>());
        map.get(key).add(new Pair(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Pair<String, Integer>> list = map.get(key);
        return search(list, timestamp);
    }

    public String search(List<Pair<String, Integer>> list, int timestamp) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (list.get(mid).getValue() <= timestamp) start = mid; else end =
                mid - 1;
        }
        return list.get(start).getValue() <= timestamp
            ? list.get(start).getKey()
            : "";
    }
}
