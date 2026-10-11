package neetcode150.problems.p420;

import static neetcode150.common.Util.*;

/*
 * 420. Strong Password Checker  [Hard]
 * https://leetcode.com/problems/strong-password-checker/
 *
 * Problem:
 *   A strong password has 6..20 chars, at least one lowercase, one uppercase and one digit, and no
 *   three identical characters in a row. One step = insert, delete or replace one character.
 *   Return the minimum steps to make the password strong.
 *
 * Example:
 *   Input:  password = "aaa111"   Output: 2
 *
 * Solution - case analysis on the length:
 *   Let `missing` = number of absent character types (0..3) and, for every run of >= 3 equal
 *   chars of length L, `replace += L / 3` (replacements needed to break it).
 *   - length < 6:  need insertions; one insertion can also add a missing type or split a run, so
 *                  answer = max(6 - n, missing).
 *   - 6 <= n <= 20: only replacements matter (a replacement can fix a run AND supply a missing
 *                  type), answer = max(missing, replace).
 *   - n > 20:      must delete n - 20 characters. Deletions are free to also shorten runs, which
 *                  reduces the replacements needed. Spend deletions where they save the most:
 *                    runs with L % 3 == 0 : 1 deletion saves 1 replacement
 *                    runs with L % 3 == 1 : 2 deletions save 1 replacement
 *                    other runs           : 3 deletions save 1 replacement
 *                  answer = deletions + max(missing, remainingReplacements).
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.strongPasswordChecker("a")); // 5
        print(s.strongPasswordChecker("aA1")); // 3
        print(s.strongPasswordChecker("1337C0d3")); // 0
        print(s.strongPasswordChecker("aaa111")); // 2
        print(s.strongPasswordChecker("aaaaaaaaaaaaaaaaaaaaa")); // 7
    }

    public int strongPasswordChecker(String password) {
        int n = password.length();

        boolean hasLower = false, hasUpper = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }
        int missing = (hasLower ? 0 : 1) + (hasUpper ? 0 : 1) + (hasDigit ? 0 : 1);

        // Scan runs of equal characters.
        int replace = 0;      // replacements needed to break all runs of >= 3
        int mod0 = 0, mod1 = 0; // runs of length >= 3 with L % 3 == 0 and L % 3 == 1
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && password.charAt(j) == password.charAt(i)) j++;
            int len = j - i;
            if (len >= 3) {
                replace += len / 3;
                if (len % 3 == 0) mod0++;
                else if (len % 3 == 1) mod1++;
            }
            i = j;
        }

        if (n < 6) return Math.max(missing, 6 - n);
        if (n <= 20) return Math.max(missing, replace);

        int delete = n - 20;
        // Use deletions in order of efficiency: 1 per saved replacement, then 2, then 3.
        replace -= Math.min(delete, mod0);
        replace -= Math.min(Math.max(delete - mod0, 0), mod1 * 2) / 2;
        replace -= Math.max(delete - mod0 - 2 * mod1, 0) / 3;
        return delete + Math.max(missing, replace);
    }
}
