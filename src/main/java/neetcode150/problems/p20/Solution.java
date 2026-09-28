package neetcode150.problems.p20;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 20. Valid Parentheses  [Easy]  -  Stack
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Problem:
 *   Given a string of '()[]{}' characters, decide whether every bracket is closed by the same
 *   type, in the correct order.
 *
 * Example:
 *   Input:  s = "()[]{}"   Output: true
 *   Input:  s = "(]"       Output: false
 *
 * Solution - stack:
 *   Odd length -> false. Push openers. For a closer, the top of the stack must be the
 *   matching opener - pop it; otherwise the string is invalid. At the end the stack must be
 *   empty.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isValid("()")); // true
        print(new Solution().isValid("()[]{}")); // true
        print(new Solution().isValid("(]")); // false
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (
                stack.isEmpty() &&
                (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']')
            ) return false; else {
                    if (
                        s.charAt(i) == ')' && stack.peek() == '('
                    ) stack.pop(); else if (
                        s.charAt(i) == '}' && stack.peek() == '{'
                    ) stack.pop(); else if (
                        s.charAt(i) == ']' && stack.peek() == '['
                    ) stack.pop(); else stack.add(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}

//Solution with HashMap Lookup table as described in the video
