package neetcode150.problems.p121;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 121. Best Time to Buy and Sell Stock  [Easy]  -  Sliding Window
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Problem:
 *   prices[i] is the price on day i. Buy once and sell once later; return the max profit (0 if
 *   no profit is possible).
 *
 * Example:
 *   Input:  prices = [7, 1, 5, 3, 6, 4]
 *   Output: 5                 (buy at 1, sell at 6)
 *
 * Solution - two pointers:
 *   left = buy day, right = sell day. If prices[right] > prices[left], record the profit;
 *   otherwise a cheaper buy day was found, so move left to right.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxProfit(new int[] {7, 1, 5, 3, 6, 4})); // 5
        print(new Solution().maxProfit(new int[] {7, 6, 4, 3, 1})); // 0
    }

    public int maxProfit(int[] prices) {
        int left = 0;
        int right = 1;
        int maxProfit = 0;
        while (right < prices.length) {
            if (prices[left] < prices[right]) {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
            } else {
                left = right;
            }
            right++;
        }
        return maxProfit;
    }
}
