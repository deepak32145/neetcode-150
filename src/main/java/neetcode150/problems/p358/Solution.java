package neetcode150.problems.p358;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 358. Rearrange String k Distance Apart  [Hard, Premium]
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
 *
 * Problem:
 *   Rearrange s so that equal characters are at least k positions apart. Return any valid
 *   rearrangement or "" if impossible.
 *
 * Example:
 *   Input:  s = "aabbcc", k = 3   Output: "abcabc"
 *
 * Solution - greedy with a max-heap and a cooldown queue:
 *   Always place the available character with the highest remaining count (the most constrained
 *   one). After placing it, it must wait k-1 more placements, so park it in a FIFO queue. Once the
 *   queue holds k characters, the oldest has served its cooldown and returns to the heap (if it
 *   still has copies). If the heap runs dry before the string is complete, no arrangement exists.
 *   Time: O(n log 26)   Space: O(26)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.rearrangeString("aabbcc", 3)); // abcabc
        print(s.rearrangeString("aaabc", 3)); // ""
        print(s.rearrangeString("aaadbbcc", 2)); // abacabcd
    }

    public String rearrangeString(String s, int k) {
        if (k <= 1) return s;

        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;

        // Max-heap by remaining count; ties broken alphabetically for deterministic output.
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] != b[1] ? b[1] - a[1] : a[0] - b[0]);
        for (int c = 0; c < 26; c++) {
            if (count[c] > 0) heap.add(new int[] {c, count[c]});
        }

        Queue<int[]> cooldown = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            sb.append((char) ('a' + cur[0]));
            cur[1]--;
            cooldown.add(cur);

            if (cooldown.size() >= k) { // the char placed k steps ago may be used again
                int[] ready = cooldown.poll();
                if (ready[1] > 0) heap.add(ready);
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
