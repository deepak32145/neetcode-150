package neetcode150.problems.p479;

import static neetcode150.common.Util.*;

/*
 * 479. Largest Palindrome Product  [Hard]
 * https://leetcode.com/problems/largest-palindrome-product/
 *
 * Problem:
 *   Return the largest palindrome that is a product of two n-digit integers, modulo 1337.
 *
 * Example:
 *   Input:  n = 2   Output: 987   (99 * 91 = 9009, 9009 % 1337 = 987)
 *
 * Solution - generate palindromes from the top, test for an n-digit factor:
 *   The product of two n-digit numbers has 2n digits (for the maximum case), so the target is a
 *   2n-digit palindrome. Such a palindrome is fully determined by its first half h (n digits):
 *   mirror h to get p. Iterate h from 10^n - 1 downwards so the FIRST palindrome that has an
 *   n-digit divisor is the largest. For that test, try divisors x from 10^n - 1 downwards while
 *   x * x >= p (the smaller factor is at most sqrt(p)); stop at the first x dividing p.
 *   n = 1 is special: the answer is 9 (single-digit products, no 2-digit palindrome fits).
 *   Time: ~O(10^n * 10^(n/2))   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.largestPalindrome(1)); // 9
        print(s.largestPalindrome(2)); // 987
        print(s.largestPalindrome(3)); // 123
    }

    public int largestPalindrome(int n) {
        if (n == 1) return 9;

        long max = (long) Math.pow(10, n) - 1; // largest n-digit number
        long min = max / 10;                   // exclusive lower bound for n-digit halves

        for (long half = max; half > min; half--) {
            // Mirror the half to build the 2n-digit palindrome: 98 -> 9889.
            long p = half;
            for (long t = half; t > 0; t /= 10) p = p * 10 + t % 10;

            // Look for an n-digit divisor whose cofactor is also n-digit (x*x >= p keeps x >= cofactor).
            for (long x = max; x * x >= p; x--) {
                if (p % x == 0) return (int) (p % 1337);
            }
        }
        return 9;
    }
}
