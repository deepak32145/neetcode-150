package neetcode150.problems.p134;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 134. Gas Station  [Medium]  -  Greedy
 * https://leetcode.com/problems/gas-station/
 *
 * Problem:
 *   Stations on a circle give gas[i] and it costs cost[i] to drive to the next one. Return the
 *   starting station from which you can drive a full circle, or -1 (the answer is unique).
 *
 * Example:
 *   Input:  gas = [1, 2, 3, 4, 5], cost = [3, 4, 5, 1, 2]
 *   Output: 3
 *
 * Solution - greedy:
 *   If total gas < total cost the trip is impossible. Otherwise scan once keeping a running
 *   tank; whenever it goes negative, no station up to i can be the start, so reset the tank and
 *   try i + 1.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().canCompleteCircuit(new int[] {1, 2, 3, 4, 5}, new int[] {3, 4, 5, 1, 2})); // 3
        print(new Solution().canCompleteCircuit(new int[] {2, 3, 4}, new int[] {3, 4, 3})); // -1
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sGas = 0, sCost = 0, res = 0, total = 0;
        for (int i = 0; i < gas.length; i++) {
            sGas += gas[i];
            sCost += cost[i];
        }
        if (sGas < sCost) return -1;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < 0) {
                total = 0;
                res = i + 1;
            }
        }
        return res;
    }
}
