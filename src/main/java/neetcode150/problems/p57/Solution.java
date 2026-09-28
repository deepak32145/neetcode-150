package neetcode150.problems.p57;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 57. Insert Interval  [Medium]  -  Intervals
 * https://leetcode.com/problems/insert-interval/
 *
 * Problem:
 *   Insert newInterval into a sorted list of non-overlapping intervals, merging where needed.
 *
 * Example:
 *   Input:  intervals = [[1, 3], [6, 9]], newInterval = [2, 5]
 *   Output: [[1, 5], [6, 9]]
 *
 * Solution - single linear pass:
 *   For each interval: if it ends before newInterval starts, keep it. If it starts after
 *   newInterval ends, add newInterval, then this one (and mark newInterval as placed).
 *   Otherwise they overlap - widen newInterval to cover both. Add newInterval at the end if it
 *   was never placed.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5})); // [[1, 5], [6, 9]]
        print(new Solution().insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8})); // [[1, 2], [3, 10], [12, 16]]
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (newInterval == null || interval[1] < newInterval[0]) res.add(
                interval
            ); else if (interval[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        if (newInterval != null) res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }
}
