package neetcode150.problems.p309;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 309. Best Time to Buy and Sell Stock with Cooldown  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * Problem:
 *   Buy and sell as many times as you like (holding at most one share), but after a sale you
 *   must wait one day before buying again. Return the max profit.
 *
 * Example:
 *   Input:  prices = [1, 2, 3, 0, 2]
 *   Output: 3                 (buy, sell, cooldown, buy, sell)
 *
 * Solution - DFS with memo on (day, buying?):
 *   On each day either do nothing (move to the next day) or act:
 *   - buying:  -price, then day + 1 in selling state
 *   - selling: +price, then skip to day + 2 (cooldown) in buying state
 *   Take the better option and cache it under "index-buying".
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxProfit(new int[] {1, 2, 3, 0, 2})); // 3
        print(new Solution().maxProfit(new int[] {1})); // 0
    }

    public int maxProfit(int[] prices) {
        Map<String, Integer> cache = new HashMap<>();
        return dfs(prices, cache, 0, true);
    }

    public int dfs(
        int[] prices,
        Map<String, Integer> cache,
        int index,
        boolean buying
    ) {
        if (index >= prices.length) {
            return 0;
        }
        String key = index + "-" + buying;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int cooldown = dfs(prices, cache, index + 1, buying);
        int buyOsell = Integer.MIN_VALUE;

        if (buying) {
            buyOsell = dfs(prices, cache, index + 1, !buying) - prices[index];
        } else {
            buyOsell = dfs(prices, cache, index + 2, !buying) + prices[index];
        }

        cache.put(key, Math.max(buyOsell, cooldown));
        return cache.get(key);
    }
}
