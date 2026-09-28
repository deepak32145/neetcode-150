package neetcode150.problems.p70;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 70. Climbing Stairs  [Easy]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/climbing-stairs/
 *
 * Problem:
 *   You climb 1 or 2 steps at a time. In how many distinct ways can you reach the top of n
 *   stairs?
 *
 * Example:
 *   Input:  n = 3
 *   Output: 3                 (1+1+1, 1+2, 2+1)
 *
 * Solution - Fibonacci, bottom up:
 *   ways(n) = ways(n - 1) + ways(n - 2). Keep only the last two values (a, b) and roll forward.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().climbStairs(2)); // 2
        print(new Solution().climbStairs(3)); // 3
        print(new Solution().climbStairs(5)); // 8
    }

    public int climbStairs(int n) {
        int a = 1;
        int b = 1;
        int c;

        for (int i = 0; i < n - 1; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}

//bottom up
