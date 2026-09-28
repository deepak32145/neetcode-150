package neetcode150.problems.p371;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 371. Sum of Two Integers  [Medium]  -  Bit Manipulation
 * https://leetcode.com/problems/sum-of-two-integers/
 *
 * Problem:
 *   Return a + b without using the + or - operators.
 *
 * Example:
 *   Input:  a = 2, b = 3
 *   Output: 5
 *
 * Solution - XOR for the sum, AND for the carry:
 *   a ^ b adds the bits without carrying; (a & b) << 1 is the carry. Repeat with the carry as b
 *   until there is no carry left. Works for negative numbers with two's complement.
 *   Time: O(32) = O(1)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().getSum(1, 2)); // 3
        print(new Solution().getSum(2, 3)); // 5
        print(new Solution().getSum(-2, 3)); // 1
    }

    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = (a & b) << 1;
            a = (a ^ b);
            b = tmp;
        }
        return a;
    }
}
