package neetcode150.problems.p411;

import static neetcode150.common.Util.*;

/*
 * 411. Minimum Unique Word Abbreviation  [Hard, Premium]
 * https://leetcode.com/problems/minimum-unique-word-abbreviation/
 *
 * Problem:
 *   An abbreviation replaces any non-adjacent substrings with their lengths ("apple" -> "a2e",
 *   "1p3"). The abbreviation's length counts each letter and each number as 1. Given a target and
 *   a dictionary, return the SHORTEST abbreviation of target that is not an abbreviation of any
 *   dictionary word.
 *
 * Example:
 *   Input:  target = "apple", dictionary = ["blade"]   Output: "a4"
 *
 * Solution - bitmask over which letters are kept:
 *   An abbreviation of target is fully described by a mask of kept positions (bit i set = letter
 *   i is written, clear = absorbed into a number). Only same-length dictionary words can share an
 *   abbreviation, so for each such word compute diff = mask of positions where it differs from
 *   target. The abbreviation is unique w.r.t. that word iff it keeps at least one differing
 *   position: (keptMask & diff) != 0.
 *   Enumerate all 2^m masks (constraints guarantee 2^m * dictionarySize is small), keep those valid
 *   for every word, and choose the one with the fewest tokens (kept letters + runs of omitted).
 *   Time: O(2^m * d)   Space: O(d)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.minAbbreviation("apple", new String[] {"blade"})); // a4
        print(s.minAbbreviation("apple", new String[] {"plain", "amber", "blade"})); // 1p3
        print(s.minAbbreviation("apple", new String[] {})); // 5
    }

    public String minAbbreviation(String target, String[] dictionary) {
        int m = target.length();

        // For each same-length word: positions where it differs from target.
        int[] diffs = new int[dictionary.length];
        int count = 0;
        for (String word : dictionary) {
            if (word.length() != m) continue;
            int mask = 0;
            for (int i = 0; i < m; i++) {
                if (word.charAt(i) != target.charAt(i)) mask |= 1 << i;
            }
            diffs[count++] = mask;
        }

        int bestMask = 0, bestLen = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << m); mask++) {
            boolean unique = true;
            for (int i = 0; i < count && unique; i++) {
                if ((mask & diffs[i]) == 0) unique = false; // would also abbreviate this word
            }
            if (!unique) continue;
            int len = abbreviationLength(mask, m);
            if (len < bestLen) {
                bestLen = len;
                bestMask = mask;
            }
        }
        return build(target, bestMask);
    }

    /** Number of tokens: each kept letter is one token; each maximal run of omitted letters is one. */
    private int abbreviationLength(int mask, int m) {
        int len = 0;
        for (int i = 0; i < m; ) {
            if ((mask >> i & 1) == 1) {
                len++;
                i++;
            } else {
                len++;
                while (i < m && (mask >> i & 1) == 0) i++; // swallow the whole omitted run
            }
        }
        return len;
    }

    private String build(String target, int mask) {
        int m = target.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; ) {
            if ((mask >> i & 1) == 1) {
                sb.append(target.charAt(i++));
            } else {
                int run = 0;
                while (i < m && (mask >> i & 1) == 0) {
                    run++;
                    i++;
                }
                sb.append(run);
            }
        }
        return sb.toString();
    }
}
