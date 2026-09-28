package neetcode150.problems.p22;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 22. Generate Parentheses  [Medium]  -  Backtracking / Stack
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Problem:
 *   Given n pairs of parentheses, generate every well-formed combination.
 *
 * Example:
 *   Input:  n = 3
 *   Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
 *
 * Solution - backtracking on open / close counts:
 *   - add '(' while openN < n
 *   - add ')' only while closedN < openN (so the prefix stays valid)
 *   - when openN == closedN == n, the stack holds a complete answer
 *   Time: O(4^n / sqrt(n)) (the n-th Catalan number of results)   Space: O(n) recursion
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().generateParenthesis(3)); // ["((()))", "(()())", "(())()", "()(())", "()()()"]
        print(new Solution().generateParenthesis(1)); // ["()"]
    }

    Stack<Character> stack = new Stack<>();
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, n);
        return res;
    }

    private void backtrack(int openN, int closedN, int n) {
        if (openN == closedN && closedN == n) {
            StringBuilder sb = new StringBuilder();
            for (Character c: stack) {
                sb.append(c);
            }
            res.add(sb.toString());
        }
        if (openN < n) {
            stack.push('(');
            backtrack(openN + 1, closedN, n);
            stack.pop();
        }
        if (closedN < openN) {
            stack.push(')');
            backtrack(openN, closedN + 1, n);
            stack.pop();
        }
    }
}
