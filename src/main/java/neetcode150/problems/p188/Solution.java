package neetcode150.problems.p188;

import java.util.Arrays;
import static neetcode150.common.Util.*;

/*
 * 188. Best Time to Buy and Sell Stock IV  [Hard]
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * Problem:
 *   Max profit with at most k buy/sell transactions.
 *
 * Example:
 *   Input:  k = 2, prices = [3,2,6,5,0,3]   Output: 7
 *
 * Solution - generalize the 2-transaction state machine to k transactions:
 *   buy[j]  = best balance holding a stock during the j-th transaction
 *   sell[j] = best balance having completed j transactions
 *   Shortcut: if k >= n/2 the limit never binds (a transaction needs 2 days), so just sum every
 *   positive day-to-day rise.
 *   Time: O(n*k)   Space: O(k)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.maxProfit(2, new int[] {2, 4, 1})); // 2
        print(s.maxProfit(2, new int[] {3, 2, 6, 5, 0, 3})); // 7
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2 || k == 0) return 0;

        // Unlimited transactions: collect every upward move.
        if (k >= n / 2) {
            int profit = 0;
            for (int i = 1; i < n; i++) profit += Math.max(0, prices[i] - prices[i - 1]);
            return profit;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int p : prices) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - p); // start transaction j after finishing j-1
                sell[j] = Math.max(sell[j], buy[j] + p);    // finish transaction j today
            }
        }
        return sell[k];
    }
}
