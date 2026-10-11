package neetcode150.problems.p466;

import static neetcode150.common.Util.*;

/*
 * 466. Count The Repetitions  [Hard]
 * https://leetcode.com/problems/count-the-repetitions/
 *
 * Problem:
 *   str1 = s1 repeated n1 times, str2 = s2 repeated n2 times. Return the largest M such that
 *   str2 repeated M times can be obtained from str1 by deleting characters (subsequence).
 *
 * Example:
 *   Input:  s1 = "acb", n1 = 4, s2 = "ab", n2 = 2   Output: 2
 *
 * Solution - simulate one s1 at a time and detect a cycle:
 *   After consuming k copies of s1, the only state that matters for the future is j = our position
 *   inside s2. Record for each k: repeatCount[k] = number of complete s2 matched so far, and
 *   nextIndex[k] = j. As soon as nextIndex[k] equals an earlier nextIndex[start], the process is
 *   periodic: from start to k, each block of (k - start) copies adds the same number of s2's.
 *   Use the cycle to jump straight to n1 copies:
 *       prefix  = repeatCount[start]
 *       cycles  = (n1 - start) / (k - start)   blocks, each worth repeatCount[k] - repeatCount[start]
 *       tail    = leftover copies, read off the recorded table
 *   Total complete s2 / n2 = M.
 *   Time: O(n1 * |s1|)   Space: O(n1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.getMaxRepetitions("acb", 4, "ab", 2)); // 2
        print(s.getMaxRepetitions("acb", 1, "acb", 1)); // 1
        print(s.getMaxRepetitions("abc", 1000000, "abc", 1)); // 1000000
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();

        int[] repeatCount = new int[n1 + 1]; // complete s2 matched after k copies of s1
        int[] nextIndex = new int[n1 + 1];   // position inside s2 after k copies of s1
        int j = 0, count = 0;

        for (int k = 1; k <= n1; k++) {
            for (char c : a) {
                if (c == b[j] && ++j == b.length) { // matched the whole s2 once more
                    j = 0;
                    count++;
                }
            }
            repeatCount[k] = count;
            nextIndex[k] = j;

            // Seen this position in s2 after an earlier copy? Then the pattern repeats from there.
            for (int start = 0; start < k; start++) {
                if (nextIndex[start] == j) {
                    int blockLen = k - start;
                    int prefix = repeatCount[start];
                    int pattern = (n1 - start) / blockLen * (repeatCount[k] - repeatCount[start]);
                    int tail = repeatCount[start + (n1 - start) % blockLen] - repeatCount[start];
                    return (prefix + pattern + tail) / n2;
                }
            }
        }
        return repeatCount[n1] / n2; // no cycle found within n1 copies
    }
}
