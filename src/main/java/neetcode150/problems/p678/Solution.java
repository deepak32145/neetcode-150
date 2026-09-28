package neetcode150.problems.p678;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 678. Valid Parenthesis String  [Medium]  -  Greedy
 * https://leetcode.com/problems/valid-parenthesis-string/
 *
 * Problem:
 *   s contains '(', ')' and '*', where '*' can be '(', ')' or empty. Is s valid?
 *
 * Example:
 *   Input:  s = "(*))"
 *   Output: true
 *
 * Solution - two stacks of indices:
 *   Push indexes of '(' and '*'. A ')' pops a '(' if possible, else a '*', else it's invalid.
 *   At the end, each leftover '(' must be matched by a '*' that comes AFTER it (compare indices).
 *   (Greedy O(1)-space version: track the min and max possible number of open brackets.)
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().checkValidString("()")); // true
        print(new Solution().checkValidString("(*)")); // true
        print(new Solution().checkValidString("(*))")); // true
        print(new Solution().checkValidString("((*")); // false
    }

    public boolean checkValidString(String s) {
        int count = 0;
        Stack<Integer> op = new Stack<>();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') op.push(i); // index of opening
            else if (s.charAt(i) == ')') {
                if (op.size() > 0) op.pop(); // if we have brackets
                else if (st.size() > 0) st.pop(); // if not brackets do we have stars
                else return false; // a closing bracket without opening and star
            } else st.push(i); // index of star
        }
        // if we left with some opening bracket that over stars con cover up
        while (op.size() > 0 && st.size() > 0) {
            if (op.peek() > st.peek()) return false;
            op.pop();
            st.pop();
        }

        return op.size() == 0;
    }
}

// Time complexity: O(n)
// Space complexity: O(1)
