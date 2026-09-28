package neetcode150.problems.p10;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 10. Regular Expression Matching  [Hard]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Problem:
 *   Implement matching of string s against pattern p where '.' matches any single char and
 *   'x*' matches zero or more of the preceding char. The match must cover all of s.
 *
 * Example:
 *   Input:  s = "aa", p = "a*"
 *   Output: true              ('*' repeats 'a' twice)
 *
 * Solution - top-down recursion with a cache on (i, j):
 *   dfs(i, j) = does s[i..] match p[j..]?
 *   - first-char match = i < len(s) and (s[i] == p[j] or p[j] == '.')
 *   - if p[j + 1] == '*': either skip "x*" (dfs(i, j + 2)) or, if first chars match,
 *     consume one char of s and stay on the same pattern (dfs(i + 1, j))
 *   - otherwise: first chars must match and continue with dfs(i + 1, j + 1)
 *   Note: the boolean cache only short-circuits "true" results; a Boolean[][] would also
 *   remember "false" and guarantee O(m * n).
 *   Time: O(m * n) with a full memo   Space: O(m * n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isMatch("aa", "a")); // false
        print(new Solution().isMatch("aa", "a*")); // true
        print(new Solution().isMatch("ab", ".*")); // true
    }

    public boolean isMatch(String s, String p) {
        boolean[][] cache = new boolean[s.length() + 1][p.length() + 1];

        return dfs(cache, s, p, 0, 0);
    }

    private boolean dfs(boolean[][] cache, String s, String p, int i, int j) {
        if (cache[i][j] != false) return cache[i][j];

        if (i >= s.length() && j >= p.length()) return true;

        if (j >= p.length()) return false;

        boolean match =
            i < s.length() &&
            (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            cache[i][j] =
                dfs(cache, s, p, i, j + 2) ||
                (match && dfs(cache, s, p, i + 1, j));
        } else {
            cache[i][j] = match && dfs(cache, s, p, i + 1, j + 1);
        }

        return cache[i][j];
    }
}
