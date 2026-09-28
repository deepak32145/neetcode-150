package neetcode150.problems.p435;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 435. Non-overlapping Intervals  [Medium]  -  Intervals
 * https://leetcode.com/problems/non-overlapping-intervals/
 *
 * Problem:
 *   Return the minimum number of intervals to remove so the rest don't overlap
 *   (touching at an endpoint is fine).
 *
 * Example:
 *   Input:  [[1, 2], [2, 3], [3, 4], [1, 3]]
 *   Output: 1                 (remove [1, 3])
 *
 * Solution - greedy, sort by start:
 *   Keep the last kept interval. If the next one starts before it ends, they overlap: remove one
 *   (count++) and keep whichever ends earlier, since it leaves more room. Otherwise keep the new
 *   interval.
 *   Time: O(n log n)   Space: O(1) extra
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().eraseOverlapIntervals(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}})); // 1
        print(new Solution().eraseOverlapIntervals(new int[][] {{1, 2}, {1, 2}, {1, 2}})); // 2
        print(new Solution().eraseOverlapIntervals(new int[][] {{1, 2}, {2, 3}})); // 0
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int intervalsRemoved = 0;

        Arrays.sort(
            intervals,
            (arr1, arr2) -> Integer.compare(arr1[0], arr2[0])
        );

        int[] intervalFirst = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (firstIntervalwithinSecond(intervalFirst, intervals[i])) {
                //mark first interval to be removed
                intervalsRemoved++;
                // determine which interval to remove
                //remove the interval that ends last
                if (intervalFirst[1] > intervals[i][1]) {
                    intervalFirst = intervals[i];
                }
            } else {
                intervalFirst = intervals[i];
            }
        }
        return intervalsRemoved;
    }

    public boolean firstIntervalwithinSecond(
        int[] intervalFirst,
        int[] intervalSecond
    ) {
        return intervalSecond[0] < intervalFirst[1];
    }
}
