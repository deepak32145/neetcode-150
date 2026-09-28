package neetcode150.problems.p66;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 66. Plus One  [Easy]  -  Math & Geometry
 * https://leetcode.com/problems/plus-one/
 *
 * Problem:
 *   A large integer is given as an array of digits (most significant first). Add one and
 *   return the resulting digits.
 *
 * Example:
 *   Input:  [1, 2, 3]   Output: [1, 2, 4]
 *   Input:  [9]         Output: [1, 0]
 *
 * Solution - carry from the right:
 *   Add the carry to the last digit and move left while the digit overflows past 9. If the
 *   carry is still 1 after the first digit, the answer needs one more digit: [1, 0, 0, ...].
 *   Time: O(n)   Space: O(n) only for the all-9s case
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().plusOne(new int[] {1, 2, 3})); // [1, 2, 4]
        print(new Solution().plusOne(new int[] {9})); // [1, 0]
    }

    public int[] plusOne(int[] digits) {
        final int len = digits.length;
        int[] newDigits = new int[len + 1];
        int carry = 1;
        int currSum = 0;
        for (int i = len - 1; i >= 0; i--) {
            currSum = digits[i] + carry;
            if (currSum > 9) {
                digits[i] = currSum % 10;
                newDigits[i + 1] = digits[i];
                carry = 1;
            } else {
                digits[i] = currSum;
                newDigits[i + 1] = digits[i];
                carry = 0;
                break;
            }
        }

        if (carry == 1) {
            newDigits[0] = 1;
            return newDigits;
        }

        return digits;
    }
}
