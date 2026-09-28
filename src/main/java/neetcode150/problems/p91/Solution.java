package neetcode150.problems.p91;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 91. Decode Ways  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/decode-ways/
 *
 * Problem:
 *   Letters map to numbers 'A' = 1 ... 'Z' = 26. Count the ways to decode a digit string.
 *
 * Example:
 *   Input:  s = "226"
 *   Output: 3                 ("BZ", "VF", "BBF")
 *
 * Solution - DP with two rolling values:
 *   ways(i) = ways(i - 1) if s[i - 1] != '0' (single digit)
 *           + ways(i - 2) if s[i - 2..i - 1] is between 10 and 26 (two digits)
 *   Keep only oneBack and twoBack.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().numDecodings("12")); // 2
        print(new Solution().numDecodings("226")); // 3
        print(new Solution().numDecodings("06")); // 0
    }

    public int numDecodings(String s) {
        int twoBack = 1; // empty string
        int oneBack = s.charAt(0) == '0' ? 0 : 1;
        int current = oneBack;
        for (int i = 2; i < s.length() + 1; i++) {
            current = 0;
            if (s.charAt(i - 1) != '0') {
                current += oneBack;
            }
            if (
                s.charAt(i - 2) == '1' ||
                (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')
            ) {
                current += twoBack;
            }
            twoBack = oneBack;
            oneBack = current;
        }
        return current;
    }
}

//bottom up
