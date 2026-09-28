package neetcode150.problems.p3;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 3. Longest Substring Without Repeating Characters  [Medium]  -  Sliding Window
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Problem:
 *   Given a string s, return the length of the longest substring with no repeated characters.
 *
 * Example:
 *   Input:  s = "abcabcbb"
 *   Output: 3                 ("abc")
 *
 * Solution - sliding window kept in a list:
 *   The list holds the current window. When s[right] is already inside it, drop everything
 *   up to and including the earlier copy, then append s[right]. Track the max window size.
 *   Time: O(n * m) where m = alphabet size (list contains/indexOf)   Space: O(m)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().lengthOfLongestSubstring("abcabcbb")); // 3
        print(new Solution().lengthOfLongestSubstring("bbbbb")); // 1
        print(new Solution().lengthOfLongestSubstring("pwwkew")); // 3
    }

    public int lengthOfLongestSubstring(String s) {
            List<Character> substringL = new ArrayList<>();
            int largestlength = 0;
            for(int right = 0; right < s.length(); right++) {
                if(substringL.contains(s.charAt(right))) { 
                    // get the index of the char
                    int index = substringL.indexOf(s.charAt(right));
                    substringL.remove(index);
                    if(index > 0)
                        substringL.subList(0, index).clear();
                }
                substringL.add(s.charAt(right));
                largestlength = Math.max(largestlength, substringL.size());
            }
            return largestlength;
    }
}
