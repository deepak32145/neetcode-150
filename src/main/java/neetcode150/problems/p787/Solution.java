package neetcode150.problems.p787;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 787. Cheapest Flights Within K Stops  [Medium]  -  Advanced Graphs
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 *
 * Problem:
 *   Return the cheapest price from src to dst using at most k stops (k + 1 flights), or -1.
 *
 * Example:
 *   Input:  n = 4, flights = [[0,1,100], [1,2,100], [2,0,100], [1,3,600], [2,3,200]],
 *           src = 0, dst = 3, k = 1
 *   Output: 700               (0 -> 1 -> 3)
 *
 * Solution - Bellman-Ford limited to k + 1 rounds:
 *   Each round relaxes every flight once using prices from the previous round only (the temp
 *   copy), so after round r a price uses at most r flights.
 *   Time: O(k * E)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        print(new Solution().findCheapestPrice(4, flights, 0, 3, 1)); // 700
        int[][] triangle = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        print(new Solution().findCheapestPrice(3, triangle, 0, 2, 1)); // 200
        print(new Solution().findCheapestPrice(3, triangle, 0, 2, 0)); // 500
    }

    public int findCheapestPrice(
        int n,
        int[][] flights,
        int src,
        int dst,
        int k
    ) {
        // initialize an array with max value of size n
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        // price from source to source is always 0
        prices[src] = 0;

        for (int i = 0; i <= k; i++) {
            // make a copy of prices
            int[] temp = new int[n];
            temp = Arrays.copyOf(prices, prices.length);

            // loop through flights
            for (int j = 0; j < flights.length; j++) {
                int s = flights[j][0]; // from
                int d = flights[j][1]; // to
                int p = flights[j][2]; // price

                if (prices[s] == Integer.MAX_VALUE) {
                    continue;
                }

                if (prices[s] + p < temp[d]) {
                    temp[d] = prices[s] + p;
                }
            }

            // set prices to temp
            prices = temp;
        }

        if (prices[dst] != Integer.MAX_VALUE) {
            return prices[dst];
        }

        return -1;
    }
}
