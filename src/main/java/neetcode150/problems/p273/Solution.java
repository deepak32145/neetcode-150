package neetcode150.problems.p273;

import static neetcode150.common.Util.*;

/*
 * 273. Integer to English Words  [Hard]
 * https://leetcode.com/problems/integer-to-english-words/
 *
 * Problem:
 *   Convert a non-negative integer to its English words representation.
 *
 * Example:
 *   Input:  num = 1234567
 *   Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Solution - process three digits at a time:
 *   English groups numbers in thousands: billions, millions, thousands, units. Split num into
 *   groups of three digits, convert each group (0..999) with a small helper, and append the group's
 *   scale word. The helper handles hundreds, then 1-19 by lookup, then tens + ones.
 *   Time: O(1) (at most 4 groups)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.numberToWords(123)); // One Hundred Twenty Three
        print(s.numberToWords(12345)); // Twelve Thousand Three Hundred Forty Five
        print(s.numberToWords(1234567)); // One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven
        print(s.numberToWords(1000000)); // One Million
        print(s.numberToWords(0)); // Zero
    }

    private static final String[] BELOW_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety"};
    private static final String[] SCALES = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String result = "";
        for (int scale = 0; num > 0; scale++, num /= 1000) {
            int group = num % 1000; // current three digits, least significant group first
            if (group != 0) {
                String words = threeDigits(group);
                if (!SCALES[scale].isEmpty()) words += " " + SCALES[scale];
                result = result.isEmpty() ? words : words + " " + result; // prepend: higher scales go left
            }
        }
        return result;
    }

    /** Words for 1..999. */
    private String threeDigits(int n) {
        StringBuilder sb = new StringBuilder();
        if (n >= 100) {
            sb.append(BELOW_20[n / 100]).append(" Hundred");
            n %= 100;
        }
        if (n >= 20) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(TENS[n / 10]);
            n %= 10;
        }
        if (n > 0) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(BELOW_20[n]); // covers 1..19 and the ones digit after a tens word
        }
        return sb.toString();
    }
}
