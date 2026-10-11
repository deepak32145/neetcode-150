package neetcode150.problems.p32;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 32. Longest Valid Parentheses  [Hard]
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Problem:
 *   Return the length of the longest valid (well-formed) parentheses substring.
 *
 * Example:
 *   Input:  s = ")()())"
 *   Output: 4                 ("()()")
 *
 * Solution - stack of indices:
 *   Push -1 as a base. For '(', push i. For ')', pop; if the stack is empty push i (new base),
 *   else the valid run ending at i has length i - peek.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().longestValidParentheses("(()")); // 2
        print(new Solution().longestValidParentheses(")()())")); // 4
    }

    public int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) st.push(i);
                else best = Math.max(best, i - st.peek());
            }
        }
        return best;
    }
}
