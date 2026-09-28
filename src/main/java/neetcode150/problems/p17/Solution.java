package neetcode150.problems.p17;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 17. Letter Combinations of a Phone Number  [Medium]  -  Backtracking
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Problem:
 *   Given a string of digits 2-9, return every letter combination they could represent on a
 *   phone keypad (2 = abc, 3 = def, ..., 9 = wxyz).
 *
 * Example:
 *   Input:  digits = "23"
 *   Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 *
 * Solution - backtracking:
 *   Build the string one digit at a time: for each letter mapped to digits[index], append it
 *   and recurse on index + 1. When the current string is as long as digits, record it.
 *   Time: O(4^n * n)   Space: O(n) recursion depth
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().letterCombinations("23")); // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        print(new Solution().letterCombinations("")); // []
    }

    private Map<Character, String> digitToChar = Map.of(
        '2',
        "abc",
        '3',
        "def",
        '4',
        "ghi",
        '5',
        "jkl",
        '6',
        "mno",
        '7',
        "pqrs",
        '8',
        "tuv",
        '9',
        "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList();
        }

        List<String> ans = new ArrayList();
        String cur = "";
        backtrack(digits, ans, cur, 0);
        return ans;
    }

    public void backtrack(
        String digits,
        List<String> ans,
        String cur,
        int index
    ) {
        if (cur.length() == digits.length()) {
            ans.add(cur);
            return;
        } else if (index >= digits.length()) {
            return;
        } else {
            String digit = digitToChar.get(digits.charAt(index));
            for (char c : digit.toCharArray()) {
                backtrack(digits, ans, cur + c, index + 1);
            }
        }
    }
}
