package neetcode150.problems.p347;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 347. Top K Frequent Elements  [Medium]  -  Arrays & Hashing
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Problem:
 *   Return the k most frequent elements (any order).
 *
 * Example:
 *   Input:  nums = [1, 1, 1, 2, 2, 3], k = 2
 *   Output: [1, 2]
 *
 * Solution - frequency map + min-heap of size k:
 *   Count each number. Push (number, count) entries into a min-heap by count and pop whenever it
 *   holds more than k, so only the k most frequent remain. (Bucket sort by count: O(n).)
 *   Time: O(n log k)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2)); // [1, 2]
        print(new Solution().topKFrequent(new int[] {1}, 1)); // [1]
    }

    /**
     * Time Complexity: O(nlog(k))
     * Space Complexity: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] arr = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) ->
            a.getValue() - b.getValue()
        );
        for (Map.Entry<Integer, Integer> it : map.entrySet()) {
            pq.add(it);
            if (pq.size() > k) pq.poll();
        }
        int i = k;
        while (!pq.isEmpty()) {
            arr[--i] = pq.poll().getKey();
        }
        return arr;
    }
}
