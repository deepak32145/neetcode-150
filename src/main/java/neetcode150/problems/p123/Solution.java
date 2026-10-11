package neetcode150.problems.p123;

import static neetcode150.common.Util.*;

/*
 * 123. Best Time to Buy and Sell Stock III  [Hard]
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Problem:
 *   Max profit with at most two buy/sell transactions (must sell before buying again).
 *
 * Example:
 *   Input:  prices = [3,3,5,0,0,3,1,4]   Output: 6   (buy 0 sell 3, buy 1 sell 4)
 *
 * Solution - four-state machine, one pass:
 *   Track the best "money in hand" after each event:
 *     buy1  = best balance after the first buy  (negative: we spent cash)
 *     sell1 = best balance after the first sell
 *     buy2  = best balance after the second buy (funded by sell1's profit)
 *     sell2 = best balance after the second sell  <- the answer
 *   Each price updates the states in order, so a state only uses earlier events.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.maxProfit(new int[] {3, 3, 5, 0, 0, 3, 1, 4})); // 6
        print(s.maxProfit(new int[] {1, 2, 3, 4, 5})); // 4
        print(s.maxProfit(new int[] {7, 6, 4, 3, 1})); // 0
    }

    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int p : prices) {
            buy1 = Math.max(buy1, -p);          // best price to enter the first trade
            sell1 = Math.max(sell1, buy1 + p);  // cash out the first trade
            buy2 = Math.max(buy2, sell1 - p);   // reinvest first profit in the second trade
            sell2 = Math.max(sell2, buy2 + p);  // cash out the second trade
        }
        return sell2;
    }
}
