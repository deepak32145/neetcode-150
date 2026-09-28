package neetcode150.problems.p424;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 424. Longest Repeating Character Replacement  [Medium]  -  Sliding Window
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * Problem:
 *   You may replace at most k characters of s (uppercase letters). Return the length of the
 *   longest substring of one repeated letter you can get.
 *
 * Example:
 *   Input:  s = "AABABBA", k = 1
 *   Output: 4
 *
 * Solution - sliding window with the most frequent count:
 *   A window is valid if (window length - count of its most frequent letter) <= k. Grow the
 *   window to the right; when it becomes invalid, slide the left edge by one. max never needs to
 *   decrease because only a larger max can produce a longer answer.
 *   Time: O(n)   Space: O(26)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().characterReplacement("ABAB", 2)); // 4
        print(new Solution().characterReplacement("AABABBA", 1)); // 4
    }

    public int characterReplacement(String s, int k) {
        int[] arr = new int[26];
        int ans = 0;
        int max = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            arr[s.charAt(j) - 'A']++;
            max = Math.max(max, arr[s.charAt(j) - 'A']);
            if (j - i + 1 - max > k) {
                arr[s.charAt(i) - 'A']--;
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
