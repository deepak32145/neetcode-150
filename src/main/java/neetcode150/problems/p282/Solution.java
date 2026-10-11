package neetcode150.problems.p282;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 282. Expression Add Operators  [Hard]
 * https://leetcode.com/problems/expression-add-operators/
 *
 * Problem:
 *   Insert '+', '-' or '*' between (or not between) the digits of num so the expression evaluates to
 *   target. Return all such expressions.
 *
 * Example:
 *   Input:  num = "123", target = 6   Output: ["1+2+3", "1*2*3"]
 *
 * Solution - backtracking that carries the running value and the last term:
 *   Pick the next operand as num[pos..i]. Track `value` (evaluation so far) and `last` (the most
 *   recent signed term, which a following '*' must bind to):
 *     +n : value + n,                       last = n
 *     -n : value - n,                       last = -n
 *     *n : value - last + last * n,         last = last * n   (undo the last term, redo it multiplied)
 *   This handles precedence without parsing. Skip operands with a leading zero ("05"), and use long
 *   to avoid overflow.
 *   Time: O(4^n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.addOperators("123", 6)); // [1+2+3, 1*2*3]
        print(s.addOperators("232", 8)); // [2+3*2, 2*3+2]
        print(s.addOperators("105", 5)); // [1*0+5, 10-5]
        print(s.addOperators("3456237490", 9191)); // []
    }

    private List<String> res;
    private String num;
    private long target;

    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        this.num = num;
        this.target = target;
        dfs(0, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(int pos, StringBuilder expr, long value, long last) {
        if (pos == num.length()) {
            if (value == target) res.add(expr.toString());
            return;
        }
        int len = expr.length();
        long cur = 0;
        for (int i = pos; i < num.length(); i++) {
            if (i > pos && num.charAt(pos) == '0') break; // no multi-digit operand with a leading zero
            cur = cur * 10 + (num.charAt(i) - '0');
            String operand = num.substring(pos, i + 1);

            if (pos == 0) {
                // First operand: no operator in front of it.
                expr.append(operand);
                dfs(i + 1, expr, cur, cur);
                expr.setLength(len);
            } else {
                expr.append('+').append(operand);
                dfs(i + 1, expr, value + cur, cur);
                expr.setLength(len);

                expr.append('-').append(operand);
                dfs(i + 1, expr, value - cur, -cur);
                expr.setLength(len);

                expr.append('*').append(operand);
                dfs(i + 1, expr, value - last + last * cur, last * cur);
                expr.setLength(len);
            }
        }
    }
}
