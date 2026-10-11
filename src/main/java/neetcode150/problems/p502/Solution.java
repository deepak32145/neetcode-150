package neetcode150.problems.p502;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 502. IPO  [Hard]
 * https://leetcode.com/problems/ipo/
 *
 * Problem:
 *   You start with capital w and may complete at most k distinct projects. Project i needs
 *   capital[i] to start and yields profits[i] (added to your capital). Maximize final capital.
 *
 * Example:
 *   Input:  k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]   Output: 4
 *
 * Solution - greedy with two heaps (or sort + one heap):
 *   Capital only grows, so a project that is affordable now stays affordable. Each round, among
 *   ALL currently affordable projects pick the one with the largest profit.
 *   - Sort projects by required capital.
 *   - Sweep a pointer adding every newly affordable project's profit to a max-heap.
 *   - Poll the biggest profit, add to w. Stop after k picks or when nothing is affordable.
 *   Time: O(n log n + k log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findMaximizedCapital(2, 0, new int[] {1, 2, 3}, new int[] {0, 1, 1})); // 4
        print(s.findMaximizedCapital(3, 0, new int[] {1, 2, 3}, new int[] {0, 1, 2})); // 6
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(capital[a], capital[b])); // cheapest-to-start first

        PriorityQueue<Integer> affordable = new PriorityQueue<>(Collections.reverseOrder()); // max-heap of profits
        int next = 0;
        for (int round = 0; round < k; round++) {
            // Unlock every project whose required capital we can now meet.
            while (next < n && capital[order[next]] <= w) {
                affordable.add(profits[order[next]]);
                next++;
            }
            if (affordable.isEmpty()) break; // nothing else can be started
            w += affordable.poll();          // take the most profitable available project
        }
        return w;
    }
}
