package neetcode150.problems.p354;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 354. Russian Doll Envelopes  [Hard]
 * https://leetcode.com/problems/russian-doll-envelopes/
 *
 * Problem:
 *   Envelope A fits in B if both width and height of A are strictly smaller. Return the maximum
 *   number of envelopes you can nest.
 *
 * Example:
 *   Input:  [[5,4],[6,4],[6,7],[2,3]]   Output: 3   ([2,3] => [5,4] => [6,7])
 *
 * Solution - sort, then longest increasing subsequence on heights:
 *   Sort by width ascending; for equal widths sort height DESCENDING. Then a strictly increasing
 *   subsequence of heights automatically has strictly increasing widths (equal-width envelopes are in
 *   decreasing height order, so at most one of them can be chosen). Run LIS with patience sorting:
 *   tails[k] = smallest ending height of an increasing subsequence of length k+1; binary search
 *   the position for each height.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.maxEnvelopes(new int[][] {{5, 4}, {6, 4}, {6, 7}, {2, 3}})); // 3
        print(s.maxEnvelopes(new int[][] {{1, 1}, {1, 1}, {1, 1}})); // 1
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(b[1], a[1]));

        int[] tails = new int[envelopes.length];
        int size = 0;
        for (int[] e : envelopes) {
            int h = e[1];
            // Find the first tail >= h (strict LIS: equal heights replace instead of extend).
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (tails[mid] < h) lo = mid + 1; else hi = mid;
            }
            tails[lo] = h;
            if (lo == size) size++;
        }
        return size;
    }
}
