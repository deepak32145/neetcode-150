package neetcode150.problems.p214;

import static neetcode150.common.Util.*;

/*
 * 214. Shortest Palindrome  [Hard]
 * https://leetcode.com/problems/shortest-palindrome/
 *
 * Problem:
 *   You may only add characters in FRONT of s. Return the shortest palindrome you can make.
 *
 * Example:
 *   Input:  s = "aacecaaa"   Output: "aaacecaaa"
 *
 * Solution - longest palindromic prefix via the KMP failure function:
 *   Adding to the front means we keep the longest prefix of s that is already a palindrome and
 *   mirror the remaining suffix onto the front. A prefix of s is a palindrome iff it equals a suffix
 *   of reverse(s). Build t = s + "#" + reverse(s); the KMP failure value at the last position is the
 *   length of the longest prefix of s that is also a suffix of reverse(s), i.e. that palindrome.
 *   The "#" stops matches from running across the boundary.
 *   Answer = reverse(s's remaining suffix) + s.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.shortestPalindrome("aacecaaa")); // aaacecaaa
        print(s.shortestPalindrome("abcd")); // dcbabcd
        print(s.shortestPalindrome("")); //
    }

    public String shortestPalindrome(String s) {
        if (s.isEmpty()) return s;
        String rev = new StringBuilder(s).reverse().toString();
        String t = s + "#" + rev;

        // Standard KMP prefix function: fail[i] = longest proper prefix of t[0..i] that is also a suffix.
        int[] fail = new int[t.length()];
        for (int i = 1; i < t.length(); i++) {
            int j = fail[i - 1];
            while (j > 0 && t.charAt(i) != t.charAt(j)) j = fail[j - 1];
            if (t.charAt(i) == t.charAt(j)) j++;
            fail[i] = j;
        }

        int palPrefixLen = fail[t.length() - 1];
        // The part of s after the palindromic prefix, reversed, goes in front.
        return rev.substring(0, s.length() - palPrefixLen) + s;
    }
}
