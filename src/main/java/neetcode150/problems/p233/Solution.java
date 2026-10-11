package neetcode150.problems.p233;

import static neetcode150.common.Util.*;

/*
 * 233. Number of Digit One  [Hard]
 * https://leetcode.com/problems/number-of-digit-one/
 *
 * Problem:
 *   Count how many times the digit 1 appears in all integers from 0 to n.
 *
 * Example:
 *   Input:  n = 13   Output: 6   (1, 10, 11, 12, 13 -> 1+1+2+1+1)
 *
 * Solution - count the contribution of each digit position independently:
 *   For position with place value m (1, 10, 100, ...), split n into
 *       high = n / (m*10),  cur = (n / m) % 10,  low = n % m
 *   Numbers 0..n have a '1' at this position:
 *       cur == 0 : high * m
 *       cur == 1 : high * m + low + 1
 *       cur >= 2 : (high + 1) * m
 *   Intuition: every full block of m*10 numbers contributes m ones at this position; the partial
 *   last block contributes depending on how far cur has gotten.
 *   Time: O(log n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.countDigitOne(13)); // 6
        print(s.countDigitOne(0)); // 0
        print(s.countDigitOne(100)); // 21
    }

    public int countDigitOne(int n) {
        long count = 0;
        for (long m = 1; m <= n; m *= 10) {
            long high = n / (m * 10);
            long cur = (n / m) % 10;
            long low = n % m;
            if (cur == 0) {
                count += high * m;
            } else if (cur == 1) {
                count += high * m + low + 1;
            } else {
                count += (high + 1) * m;
            }
        }
        return (int) count;
    }
}
