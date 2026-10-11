package neetcode150.problems.p483;

import static neetcode150.common.Util.*;

/*
 * 483. Smallest Good Base  [Hard]
 * https://leetcode.com/problems/smallest-good-base/
 *
 * Problem:
 *   k >= 2 is a good base of n if n written in base k has all digits equal to 1. Given n as a
 *   string, return the smallest good base.
 *
 * Example:
 *   Input:  n = "13"   Output: "3"   (13 = 111 in base 3)
 *
 * Solution - fix the number of digits, binary search the base:
 *   n = 1 + k + k^2 + ... + k^m for some m >= 1. For a FIXED m the sum is strictly increasing in k,
 *   so at most one k works and it can be binary searched. Smaller bases need MORE digits, so try
 *   m from the largest possible (about log2 n) downward; the first hit is the smallest base.
 *   m = 1 always works with k = n - 1 (n = 1 + (n-1)), which is the fallback.
 *   The sum is built with an overflow guard: if the next power exceeds n, the sum is "too big".
 *   Time: O(log^2 n * log n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.smallestGoodBase("13")); // 3
        print(s.smallestGoodBase("4681")); // 8
        print(s.smallestGoodBase("1000000000000000000")); // 999999999999999999
    }

    public String smallestGoodBase(String nStr) {
        long n = Long.parseLong(nStr);

        for (int m = 62; m >= 2; m--) { // m = number of powers beyond k^0, i.e. m + 1 digits
            long lo = 2;
            long hi = (long) Math.pow(n, 1.0 / m) + 1; // k^m <= n, so k <= n^(1/m); +1 for float error
            while (lo <= hi) {
                long mid = lo + (hi - lo) / 2;
                int cmp = compareSum(mid, m, n);
                if (cmp == 0) return String.valueOf(mid);
                if (cmp < 0) lo = mid + 1; else hi = mid - 1;
            }
        }
        return String.valueOf(n - 1); // two digits "11" in base n-1
    }

    /** Compares 1 + k + ... + k^m with n without overflowing: returns <0, 0, >0. */
    private int compareSum(long k, int m, long n) {
        long sum = 1, power = 1;
        for (int i = 0; i < m; i++) {
            if (power > n / k) return 1; // next power alone exceeds n: sum is larger than n
            power *= k;
            sum += power;
            if (sum > n) return 1;
        }
        return Long.compare(sum, n);
    }
}
