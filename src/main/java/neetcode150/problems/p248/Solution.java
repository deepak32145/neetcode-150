package neetcode150.problems.p248;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 248. Strobogrammatic Number III  [Hard, Premium]
 * https://leetcode.com/problems/strobogrammatic-number-iii/
 *
 * Problem:
 *   Count strobogrammatic numbers (look the same rotated 180 degrees) in the range [low, high],
 *   both given as strings.
 *
 * Example:
 *   Input:  low = "50", high = "100"   Output: 3   (69, 88, 96)
 *
 * Solution - generate every strobogrammatic number for each length and range-check:
 *   Build length-n numbers by wrapping length-(n-2) numbers with a rotation pair:
 *   (0,0) (1,1) (6,9) (8,8) (9,6). The base cases are "" (n = 0) and "0","1","8" (n = 1).
 *   A leading zero is only legal for a single digit, so the (0,0) pair is skipped at the outermost
 *   layer (n == total length) but allowed for inner layers.
 *   Only lengths between len(low) and len(high) matter. For strings of the same length lexicographic
 *   order equals numeric order, so compare against low/high only at the boundary lengths.
 *   Time: O(5^(len(high)/2))   Space: same
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.strobogrammaticInRange("50", "100")); // 3
        print(s.strobogrammaticInRange("0", "0")); // 1
        print(s.strobogrammaticInRange("0", "1000")); // 19
    }

    private static final char[][] PAIRS = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {
        int count = 0;
        for (int len = low.length(); len <= high.length(); len++) {
            for (String num : generate(len, len)) {
                if (len == low.length() && num.compareTo(low) < 0) continue;
                if (len == high.length() && num.compareTo(high) > 0) continue;
                count++;
            }
        }
        return count;
    }

    /** All strobogrammatic strings of length n; `total` is the final length (to forbid outer zeros). */
    private List<String> generate(int n, int total) {
        if (n == 0) return List.of("");
        if (n == 1) return List.of("0", "1", "8");

        List<String> res = new ArrayList<>();
        for (String inner : generate(n - 2, total)) {
            for (char[] p : PAIRS) {
                if (n == total && p[0] == '0') continue; // no leading zero on multi-digit numbers
                res.add(p[0] + inner + p[1]);
            }
        }
        return res;
    }
}
