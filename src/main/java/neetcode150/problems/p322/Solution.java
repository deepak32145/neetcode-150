package neetcode150.problems.p322;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 322. Coin Change  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/coin-change/
 *
 * Problem:
 *   Return the fewest coins that make up amount (unlimited coins of each type), or -1.
 *
 * Example:
 *   Input:  coins = [1, 2, 5], amount = 11
 *   Output: 3                 (5 + 5 + 1)
 *
 * Solution - bottom-up DP:
 *   dp[a] = fewest coins for amount a. dp[0] = 0, everything else starts at amount + 1
 *   ("impossible"). dp[a] = min over coins of 1 + dp[a - coin].
 *   Time: O(amount * coins)   Space: O(amount)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().coinChange(new int[] {1, 2, 5}, 11)); // 3
        print(new Solution().coinChange(new int[] {2}, 3)); // -1
        print(new Solution().coinChange(new int[] {1}, 0)); // 0
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 0 || coins.length == 0 || coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] != amount + 1 ? dp[amount] : -1;
    }
}
