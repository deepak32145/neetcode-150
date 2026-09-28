package neetcode150.problems.p518;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 518. Coin Change II  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/coin-change-ii/
 *
 * Problem:
 *   Count the combinations of coins (unlimited supply) that make up amount.
 *
 * Example:
 *   Input:  amount = 5, coins = [1, 2, 5]
 *   Output: 4                 (5, 2+2+1, 2+1+1+1, 1+1+1+1+1)
 *
 * Solution - unbounded knapsack, coins in the outer loop:
 *   dp[a] = number of ways to make a; dp[0] = 1. For each coin, for a from coin to amount:
 *   dp[a] += dp[a - coin]. Looping coins outside counts combinations, not permutations.
 *   Time: O(amount * coins)   Space: O(amount)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().change(5, new int[] {1, 2, 5})); // 4
        print(new Solution().change(3, new int[] {2})); // 0
        print(new Solution().change(10, new int[] {10})); // 1
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        // if amount is 0, there is only 1 way of making change (no money)
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (coin <= i) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        return dp[amount];
    }
}
