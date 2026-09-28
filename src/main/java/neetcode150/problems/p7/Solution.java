package neetcode150.problems.p7;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 7. Reverse Integer  [Medium]  -  Bit Manipulation / Math
 * https://leetcode.com/problems/reverse-integer/
 *
 * Problem:
 *   Reverse the digits of a signed 32-bit integer x. If the result overflows the 32-bit range,
 *   return 0.
 *
 * Example:
 *   Input:  x = -123
 *   Output: -321
 *
 * Solution - pop and push digits with an overflow check:
 *   Work on |x|. Repeatedly take the last digit (x % 10) and append it to num
 *   (num = num * 10 + digit). Before appending, if num > Integer.MAX_VALUE / 10 the next
 *   step would overflow, so return 0. Restore the sign at the end.
 *   Time: O(log x) (number of digits)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().reverse(123)); // 321
        print(new Solution().reverse(-123)); // -321
        print(new Solution().reverse(120)); // 21
    }

    public int reverse(int x) {
        boolean isNegative = x < 0;

        x = Math.abs(x);

        int num = 0;

        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < num) return 0;

            num = 10 * num + x % 10;
            x /= 10;
        }

        return isNegative ? -num : num;
    }
}
