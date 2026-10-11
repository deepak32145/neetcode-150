package neetcode150.problems.p224;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 224. Basic Calculator  [Hard]
 * https://leetcode.com/problems/basic-calculator/
 *
 * Problem:
 *   Evaluate an expression with non-negative integers, '+', '-', parentheses and spaces.
 *
 * Example:
 *   Input:  s = "(1+(4+5+2)-3)+(6+8)"   Output: 23
 *
 * Solution - running result with a stack for parentheses:
 *   Scan left to right keeping: result (sum so far in this nesting level), sign of the next number,
 *   and the number being built.
 *     digit    -> extend the number
 *     + or -   -> fold sign*number into result, remember the new sign
 *     (        -> push result and the sign in front of the parenthesis, start a fresh level
 *     )        -> fold the pending number, multiply the inner result by the pushed sign,
 *                 then add the pushed outer result
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.calculate("1 + 1")); // 2
        print(s.calculate(" 2-1 + 2 ")); // 3
        print(s.calculate("(1+(4+5+2)-3)+(6+8)")); // 23
        print(s.calculate("-(2+3)")); // -5
    }

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0, num = 0, sign = 1;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                result += sign * num; // finish the previous number
                num = 0;
                sign = (c == '+') ? 1 : -1;
            } else if (c == '(') {
                // Save the outer context; the sign applies to the whole parenthesized value.
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * num;
                num = 0;
                result *= stack.pop(); // sign in front of the parenthesis
                result += stack.pop(); // result accumulated before the parenthesis
            }
            // spaces are ignored
        }
        return result + sign * num;
    }
}
