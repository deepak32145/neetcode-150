package neetcode150.problems.p72;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 72. Edit Distance  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/edit-distance/
 *
 * Problem:
 *   Return the minimum number of insert / delete / replace operations needed to turn word1
 *   into word2.
 *
 * Example:
 *   Input:  word1 = "horse", word2 = "ros"
 *   Output: 3                 (horse -> rorse -> rose -> ros)
 *
 * Solution - top-down recursion with memo on (m, n) = last indices:
 *   If either word is empty, the cost is the other's length. If the last chars match, move
 *   both back for free. Otherwise 1 + min(delete (m - 1, n), insert (m, n - 1),
 *   replace (m - 1, n - 1)).
 *   Time: O(m * n)   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().minDistance("horse", "ros")); // 3
        print(new Solution().minDistance("intention", "execution")); // 5
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length() - 1;
        int n = word2.length() - 1;
        int[][] dp = new int[m + 2][n + 2];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return helper(word1, word2, m, n, dp);
    }

    public int helper(String word1, String word2, int m, int n, int[][] dp) {
        //the strings are null
        if (m + 1 == 0 && n + 1 == 0) {
            return 0;
        }
        //one of the strings are null
        if (m + 1 == 0 || n + 1 == 0) {
            return Math.max(m + 1, n + 1);
        }
        //both values at the index are equal
        if (dp[m][n] != -1) return dp[m][n];
        if (word1.charAt(m) == word2.charAt(n)) {
            dp[m][n] = helper(word1, word2, m - 1, n - 1, dp);
            return dp[m][n];
        } else {
            //try deletion
            int delete = 1 + helper(word1, word2, m - 1, n, dp);
            //try insertion
            int insert = 1 + helper(word1, word2, m, n - 1, dp);
            //try replacing
            int replace = 1 + helper(word1, word2, m - 1, n - 1, dp);
            //now we'll choose the minimum out of these 3 and add 1 for the operation cost
            dp[m][n] = Math.min(Math.min(delete, insert), replace);
            return dp[m][n];
        }
    }
}
