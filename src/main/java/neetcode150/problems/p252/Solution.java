package neetcode150.problems.p252;

import java.util.*;
import neetcode150.common.Interval;
import static neetcode150.common.Util.*;

/*
 * 252. Meeting Rooms  [Easy]  -  Intervals   (LeetCode premium / LintCode 920)
 * https://leetcode.com/problems/meeting-rooms/
 *
 * Problem:
 *   Given meeting time intervals, can one person attend all of them (no overlaps)?
 *
 * Example:
 *   Input:  [(0, 30), (5, 10), (15, 20)]   Output: false
 *   Input:  [(5, 8), (9, 15)]              Output: true
 *
 * Solution - sort starts and ends separately:
 *   After sorting both arrays, meetings overlap iff some end[j] > start[j + 1]
 *   (the j-th meeting to finish ends after the next one starts).
 *   Time: O(n log n)   Space: O(n)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().canAttendMeetings(new ArrayList<>(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))))); // false
        print(new Solution().canAttendMeetings(new ArrayList<>(List.of(new Interval(5, 8), new Interval(9, 15))))); // true
    }

    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        int length = intervals.size();
        if (intervals.size() == 0 || length == 1) return true;
        // Write your code here

        int[] start = new int[length];
        int[] end = new int[length];
        for (int i = 0; i < length; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int j = 0;
        while (j + 1 < length) {
            if (end[j] > start[j + 1]) return false;
            j++;
        }

        return true;
    }
}
