package neetcode150.problems.p132;

import static neetcode150.common.Util.*;

/*
 * 132. Palindrome Partitioning II  [Hard]
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 * Problem:
 *   Minimum number of cuts so that every piece of s is a palindrome.
 *
 * Example:
 *   Input:  s = "aab"   Output: 1   ("aa" | "b")
 *
 * Solution - expand around every center, DP over prefixes:
 *   cut[i] = min cuts for the first i characters. Worst case is i-1 (cut between every letter).
 *   For each center (odd and even length), grow a palindrome s[l..r]. It means the prefix of length
 *   r+1 can end with that palindrome, costing cut[l] + 1 (or 0 more if it spans from index 0,
 *   which works because cut[0] = -1).
 *   Time: O(n^2)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.minCut("aab")); // 1
        print(s.minCut("a")); // 0
        print(s.minCut("ab")); // 1
    }

    public int minCut(String s) {
        int n = s.length();
        int[] cut = new int[n + 1];
        for (int i = 0; i <= n; i++) cut[i] = i - 1; // cut[0] = -1 so a whole-prefix palindrome costs 0

        for (int center = 0; center < n; center++) {
            // Odd-length palindromes centered at `center`.
            for (int l = center, r = center; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                cut[r + 1] = Math.min(cut[r + 1], cut[l] + 1);
            }
            // Even-length palindromes centered between center and center+1.
            for (int l = center, r = center + 1; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                cut[r + 1] = Math.min(cut[r + 1], cut[l] + 1);
            }
        }
        return cut[n];
    }
}
