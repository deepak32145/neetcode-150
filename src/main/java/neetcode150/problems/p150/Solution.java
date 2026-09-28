package neetcode150.problems.p150;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 150. Evaluate Reverse Polish Notation  [Medium]  -  Stack
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Problem:
 *   Evaluate an expression written in postfix (RPN) with + - * / (integer division toward 0).
 *
 * Example:
 *   Input:  tokens = ["2", "1", "+", "3", "*"]
 *   Output: 9                 ((2 + 1) * 3)
 *
 * Solution - stack of operands:
 *   Push numbers. For an operator pop b (top) and a, push a op b. Order matters for - and /.
 *   The final stack value is the answer.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().evalRPN(new String[] {"2", "1", "+", "3", "*"})); // 9
        print(new Solution().evalRPN(new String[] {"4", "13", "5", "/", "+"})); // 6
        print(new Solution().evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"})); // 22
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.add(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.add(b - a);
            } else if (token.equals("*")) {
                stack.add(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.add(b / a);
            } else {
                stack.add(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
