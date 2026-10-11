package neetcode150.problems.p301;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 301. Remove Invalid Parentheses  [Hard]
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * Problem:
 *   Remove the minimum number of parentheses to make s valid. Return all unique results.
 *
 * Example:
 *   Input:  s = "()())()"   Output: ["(())()", "()()()"]
 *
 * Solution - count the removals needed first, then DFS exactly that many:
 *   1. One scan finds how many '(' (leftRem) and ')' (rightRem) are unmatched; these are exactly
 *      the minimum removals.
 *   2. DFS over characters. For '(' or ')' we may delete it (if removals of that kind remain) or
 *      keep it. Keeping ')' is only allowed when an unmatched '(' is open. Track `open` so the
 *      final string is balanced.
 *   3. A string is recorded only when no removals remain and open == 0. A Set removes duplicates
 *      that arise from deleting different copies of the same character.
 *   Time: O(2^n) worst   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.removeInvalidParentheses("()())()")); // [(())(), ()()()]
        print(s.removeInvalidParentheses("(a)())()")); // [(a())(), (a)()()]
        print(s.removeInvalidParentheses(")(")); // [""]  (the empty string is the only valid result)
    }

    private String s;
    private Set<String> result;

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.result = new HashSet<>();

        int leftRem = 0, rightRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) leftRem--; // matched with an earlier '('
                else rightRem++;            // nothing to match: must be removed
            }
        }
        dfs(0, leftRem, rightRem, 0, new StringBuilder());
        return new ArrayList<>(result);
    }

    private void dfs(int i, int leftRem, int rightRem, int open, StringBuilder sb) {
        if (i == s.length()) {
            if (leftRem == 0 && rightRem == 0 && open == 0) result.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        int len = sb.length();

        if (c == '(') {
            if (leftRem > 0) dfs(i + 1, leftRem - 1, rightRem, open, sb); // option 1: delete it
            sb.append(c);
            dfs(i + 1, leftRem, rightRem, open + 1, sb);                   // option 2: keep it
        } else if (c == ')') {
            if (rightRem > 0) dfs(i + 1, leftRem, rightRem - 1, open, sb); // option 1: delete it
            if (open > 0) {                                                // option 2: keep it
                sb.append(c);
                dfs(i + 1, leftRem, rightRem, open - 1, sb);
            }
        } else {
            sb.append(c);
            dfs(i + 1, leftRem, rightRem, open, sb);
        }
        sb.setLength(len); // backtrack
    }
}
