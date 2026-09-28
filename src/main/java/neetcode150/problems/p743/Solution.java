package neetcode150.problems.p743;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 743. Network Delay Time  [Medium]  -  Advanced Graphs
 * https://leetcode.com/problems/network-delay-time/
 *
 * Problem:
 *   times[i] = (u, v, w) is a directed edge taking w time. A signal starts at node k. Return the
 *   time until all n nodes receive it, or -1 if some never do.
 *
 * Example:
 *   Input:  times = [[2, 1, 1], [2, 3, 1], [3, 4, 1]], n = 4, k = 2
 *   Output: 2
 *
 * Solution - Bellman-Ford:
 *   paths[i] = shortest known time to node i. Relax every edge n times (one round is enough for
 *   each extra edge on a path). The answer is the largest shortest time; any unreachable node
 *   -> -1. (Dijkstra with a heap: O(E log V).)
 *   Time: O(n * E)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2)); // 2
        print(new Solution().networkDelayTime(new int[][] {{1, 2, 1}}, 2, 1)); // 1
        print(new Solution().networkDelayTime(new int[][] {{1, 2, 1}}, 2, 2)); // -1
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // initialize an array with max value of size n
        int[] paths = new int[n];
        Arrays.fill(paths, Integer.MAX_VALUE);

        paths[k - 1] = 0;

        for (int i = 0; i < n; i++) {
            // make a copy of paths
            int[] temp = new int[n];
            temp = Arrays.copyOf(paths, paths.length);

            // loop through times
            for (int j = 0; j < times.length; j++) {
                int src = times[j][0]; // source
                int tgt = times[j][1]; // target
                int time = times[j][2]; // time

                if (
                    temp[src - 1] != Integer.MAX_VALUE &&
                    temp[src - 1] + time < temp[tgt - 1]
                ) {
                    temp[tgt - 1] = temp[src - 1] + time;
                }
            }

            // set paths to temp
            paths = temp;
        }

        int result = Integer.MIN_VALUE;

        // calculate max value
        for (int i = 0; i < n; i++) {
            if (paths[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, paths[i]);
        }

        // return result
        return result;
    }
}
