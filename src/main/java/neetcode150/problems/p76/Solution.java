package neetcode150.problems.p76;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 76. Minimum Window Substring  [Hard]  -  Sliding Window
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Problem:
 *   Return the shortest substring of s that contains every character of t (with multiplicity),
 *   or "" if none.
 *
 * Example:
 *   Input:  s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *
 * Solution - sliding window with a need-count map:
 *   map holds how many of each char of t are still needed. Grow the window to the right,
 *   decrementing counts; when a count hits 0 that char is satisfied (matched++). While all
 *   chars are satisfied, record the window and shrink it from the left.
 *   Time: O(|s| + |t|)   Space: O(|t|)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        print(new Solution().minWindow("a", "aa")); // ""
    }

    //sliding window
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char x : t.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int matched = 0;
        int start = 0;
        int minLen = s.length() + 1;
        int subStr = 0;
        for (int endWindow = 0; endWindow < s.length(); endWindow++) {
            char right = s.charAt(endWindow);
            if (map.containsKey(right)) {
                map.put(right, map.get(right) - 1);
                if (map.get(right) == 0) matched++;
            }

            while (matched == map.size()) {
                if (minLen > endWindow - start + 1) {
                    minLen = endWindow - start + 1;
                    subStr = start;
                }
                char deleted = s.charAt(start++);
                if (map.containsKey(deleted)) {
                    if (map.get(deleted) == 0) matched--;
                    map.put(deleted, map.get(deleted) + 1);
                }
            }
        }
        return minLen > s.length() ? "" : s.substring(subStr, subStr + minLen);
    }
}
