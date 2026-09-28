package neetcode150.problems.p56;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 56. Merge Intervals  [Medium]  -  Intervals
 * https://leetcode.com/problems/merge-intervals/
 *
 * Problem:
 *   Merge all overlapping intervals and return the non-overlapping result.
 *
 * Example:
 *   Input:  [[1, 3], [2, 6], [8, 10], [15, 18]]
 *   Output: [[1, 6], [8, 10], [15, 18]]
 *
 * Solution - sort by start, then merge:
 *   Sort by start. If the next interval starts before or at the end of the last merged one,
 *   extend that end (max of both ends); otherwise start a new merged interval.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}})); // [[1, 6], [8, 10], [15, 18]]
        print(new Solution().merge(new int[][] {{1, 4}, {4, 5}})); // [[1, 5]]
    }

    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            //comparing the values of prevEnd and curStart
            int curStart = intervals[i][0];
            if (curStart <= ans.get(ans.size() - 1)[1]) {
                //do the merging
                ans.get(ans.size() - 1)[1] =
                    Math.max(ans.get(ans.size() - 1)[1], intervals[i][1]);
            } else {
                ans.add(intervals[i]);
            }
        }
        int[][] res = new int[ans.size()][2];
        ans.toArray(res);
        return res;
    }
}
