package neetcode150.problems.p202;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 202. Happy Number  [Easy]  -  Math & Geometry
 * https://leetcode.com/problems/happy-number/
 *
 * Problem:
 *   Replace n with the sum of the squares of its digits, repeatedly. n is happy if this reaches
 *   1; otherwise it loops forever.
 *
 * Example:
 *   Input:  n = 19
 *   Output: true              (1+81=82 -> 64+4=68 -> 36+64=100 -> 1)
 *
 * Solution - detect the loop with a set:
 *   Keep every value seen. Reaching 1 -> true. Seeing a value again -> we're in a cycle ->
 *   false. (Floyd's slow / fast pointers would use O(1) space.)
 *   Time: O(log n) per step, few steps   Space: O(number of steps)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isHappy(19)); // true
        print(new Solution().isHappy(2)); // false
    }

    public boolean isHappy(int n) {
        if (n == 1 || n == -1) {
            return true;
        }

        Set<Integer> visit = new HashSet<Integer>();

        // compute square until getting duplicate value
        while (!visit.contains(n)) {
            visit.add(n);
            // using helper function to compute the sum of squares
            n = sumOfSquare(n);

            if (n == 1) return true;
        }

        return false;
    }

    public int sumOfSquare(int n) {
        int output = 0;

        while (n != 0) {
            int digit = n % 10;
            digit = digit * digit;
            output += digit;
            n = n / 10;
        }

        return output;
    }
}
