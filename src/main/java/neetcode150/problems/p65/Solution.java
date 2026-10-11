package neetcode150.problems.p65;

import static neetcode150.common.Util.*;

/*
 * 65. Valid Number  [Hard]
 * https://leetcode.com/problems/valid-number/
 *
 * Problem:
 *   Return whether s is a valid decimal or integer, optionally with exponent (e/E).
 *
 * Example:
 *   "0" true, "e" false, "2e10" true, "-90E3" true, "99e2.5" false
 *
 * Solution - scan flags:
 *   Walk the string: at most one sign (start or after e), at most one dot (before e),
 *   at least one digit in the mantissa, and if e is present at least one digit after it.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isNumber("0")); // true
        print(new Solution().isNumber("e")); // false
        print(new Solution().isNumber("2e10")); // true
        print(new Solution().isNumber("99e2.5")); // false
    }

    public boolean isNumber(String s) {
        s = s.trim();
        boolean num = false, exp = false, dot = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') num = true;
            else if (c == '+' || c == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else if (c == '.') {
                if (dot || exp) return false;
                dot = true;
            } else if (c == 'e' || c == 'E') {
                if (exp || !num) return false;
                exp = true;
                num = false;
            } else return false;
        }
        return num;
    }
}
