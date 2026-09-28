package neetcode150.problems.p567;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 567. Permutation in String  [Medium]  -  Sliding Window
 * https://leetcode.com/problems/permutation-in-string/
 *
 * Problem:
 *   Does s2 contain a permutation of s1 as a substring?
 *
 * Example:
 *   Input:  s1 = "ab", s2 = "eidbaooo"
 *   Output: true              ("ba")
 *
 * Solution - fixed-size window of letter counts:
 *   Count s1's letters. Slide a window of length |s1| over s2, adding the new letter and removing
 *   the one that left; if the two count arrays are equal, a permutation was found.
 *   Time: O(26 * |s2|)   Space: O(26)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().checkInclusion("ab", "eidbaooo")); // true
        print(new Solution().checkInclusion("ab", "eidboaoo")); // false
    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int[] freq = new int[26];
        int m = s2.length();
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 'a']++;
        }
        int[] freq2 = new int[26];
        for (int i = 0; i < m; i++) {
            freq2[s2.charAt(i) - 'a']++;
            if (i >= n) {
                freq2[s2.charAt(i - n) - 'a']--;
            }
            if (Arrays.equals(freq, freq2))
                return true;
        }
        return false;
    }
}
