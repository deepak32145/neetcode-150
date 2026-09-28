package neetcode150.problems.p50;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 50. Pow(x, n)  [Medium]  -  Math & Geometry
 * https://leetcode.com/problems/powx-n/
 *
 * Problem:
 *   Compute x raised to the power n (n can be negative).
 *
 * Example:
 *   Input:  x = 2.0, n = 10     Output: 1024.0
 *   Input:  x = 2.0, n = -2     Output: 0.25
 *
 * Solution - fast exponentiation (recursive squaring):
 *   x^n = (x*x)^(n/2) when n is even, x * (x*x)^(n/2) when odd. For negative n use 1/x; even
 *   negatives are halved first so Integer.MIN_VALUE never overflows when negated.
 *   Time: O(log n)   Space: O(log n) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().myPow(2.0, 10)); // 1024.0
        print(new Solution().myPow(2.1, 3)); // 9.261 (prints 9.261000000000001, floating point)
        print(new Solution().myPow(2.0, -2)); // 0.25
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        //Make the negative values positive
        else if (n < 0) {
            //whenever even just divide it by 2
            //this will also include Integer.MIN_VALUE
            //We're doing this because if I do -N and N=Integer.MIN_VALUE it'll become a value which is greater than the max value of Integer.MAX_VALUE
            if (n % 2 == 0) {
                n = n / 2;
                n = -n;
                x = (1 / x) * (1 / x);
            } else { //Odds don't need to be divided as their negative is in the positive limit
                n = -n;
                x = 1 / x;
            }
        }
        if (n % 2 == 0) { //even
            return myPow(x * x, n / 2);
        } else { //odd
            return x * myPow(x * x, n / 2);
        }
    }
}
