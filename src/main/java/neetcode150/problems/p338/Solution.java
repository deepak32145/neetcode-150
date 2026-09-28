package neetcode150.problems.p338;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 338. Counting Bits  [Easy]  -  Bit Manipulation
 * https://leetcode.com/problems/counting-bits/
 *
 * Problem:
 *   For every i from 0 to n, return how many 1 bits i has.
 *
 * Example:
 *   Input:  n = 5
 *   Output: [0, 1, 1, 2, 1, 2]
 *
 * Solution - DP on "drop the lowest set bit":
 *   i & (i - 1) is i with its lowest 1 bit cleared, which is a smaller number already computed,
 *   so res[i] = 1 + res[i & (i - 1)].
 *   Time: O(n)   Space: O(n) for the output
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().countBits(2)); // [0, 1, 1]
        print(new Solution().countBits(5)); // [0, 1, 1, 2, 1, 2]
    }

    public int[] countBits(int n) {
        int res[] = new int[n + 1];
        for (int i = 1; i <= n; i++)
            res[i] = 1 + res[i & (i - 1)];
        return res;
    }
}
