package neetcode150.problems.p514;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 514. Freedom Trail  [Hard]
 * https://leetcode.com/problems/freedom-trail/
 *
 * Problem:
 *   A circular dial `ring` has a fixed 12:00 pointer. Spell `key` one character at a time: rotate
 *   the ring (1 step per position, either direction) to bring the needed character to 12:00, then
 *   press the button (1 step). Return the minimum total steps; the ring starts at index 0.
 *
 * Example:
 *   Input:  ring = "godding", key = "gd"   Output: 4
 *
 * Solution - DP over (key index, ring position):
 *   dp[j] = min steps to have typed the key so far with ring position j at 12:00.
 *   For the next key character, for every ring position j holding that character take the best
 *   previous position p:   new[j] = min over p of dp[p] + circularDistance(p, j) + 1
 *   where circularDistance = min(|p-j|, n - |p-j|) and +1 is the button press.
 *   Positions are grouped per character up front, so each key character only scans the ring
 *   positions where that character occurs.
 *   Time: O(k * n^2) worst   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findRotateSteps("godding", "gd")); // 4
        print(s.findRotateSteps("godding", "godding")); // 13
    }

    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        Map<Character, List<Integer>> positions = new HashMap<>();
        for (int i = 0; i < n; i++) positions.computeIfAbsent(ring.charAt(i), c -> new ArrayList<>()).add(i);

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // before typing anything the pointer sits at ring index 0

        for (char ch : key.toCharArray()) {
            int[] next = new int[n];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int j : positions.get(ch)) {
                for (int p = 0; p < n; p++) {
                    if (dp[p] == Integer.MAX_VALUE) continue; // unreachable previous state
                    int gap = Math.abs(p - j);
                    int rotate = Math.min(gap, n - gap); // shorter way around the ring
                    next[j] = Math.min(next[j], dp[p] + rotate + 1); // +1 for the button press
                }
            }
            dp = next;
        }

        int best = Integer.MAX_VALUE;
        for (int v : dp) best = Math.min(best, v);
        return best;
    }
}
