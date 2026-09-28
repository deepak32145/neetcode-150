package neetcode150.problems.p253;

import java.util.*;
import neetcode150.common.Interval;
import static neetcode150.common.Util.*;

/*
 * 253. Meeting Rooms II  [Medium]  -  Intervals   (LeetCode premium / LintCode 919)
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 * Problem:
 *   Return the minimum number of conference rooms needed for all meetings.
 *
 * Example:
 *   Input:  [(0, 30), (5, 10), (15, 20)]
 *   Output: 2
 *
 * Solution - sort by start + min-heap of end times:
 *   Process meetings by start time. Free every room (heap entry) whose meeting ended by now,
 *   then take a room for the current meeting. The largest heap size seen is the answer.
 *   Time: O(n log n)   Space: O(n)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().minMeetingRooms(new ArrayList<>(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))))); // 2
        print(new Solution().minMeetingRooms(new ArrayList<>(List.of(new Interval(2, 7))))); // 1
    }

    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.isEmpty()) return 0;

        Collections.sort(
            intervals,
            (a, b) -> Integer.compare(a.start, b.start)
        );

        Queue<Interval> queue = new PriorityQueue<>((a, b) ->
            Integer.compare(a.end, b.end)
        );

        int count = 0;
        for (Interval interval : intervals) {
            while (
                !queue.isEmpty() && interval.start >= queue.peek().end
            ) queue.poll();

            queue.offer(interval);
            count = Math.max(count, queue.size());
        }
        return count;
    }
}

// Two pointer approach
