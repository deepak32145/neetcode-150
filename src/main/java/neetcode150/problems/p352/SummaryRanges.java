package neetcode150.problems.p352;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 352. Data Stream as Disjoint Intervals  [Hard]
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 *
 * Problem:
 *   Design SummaryRanges: addNum(value) receives integers one by one and getIntervals() returns the
 *   numbers seen so far as a sorted list of disjoint [start, end] intervals.
 *
 * Example:
 *   addNum(1), addNum(3), addNum(7), addNum(2), addNum(6) -> getIntervals() = [[1,3],[6,7]]
 *
 * Solution - TreeMap from interval start to interval end:
 *   For a new value v, find the interval starting at or before v (lo) and the next one after (hi).
 *     - v already covered by lo           -> nothing to do
 *     - lo ends at v-1 AND hi starts at v+1 -> v bridges them: merge into one interval
 *     - only lo ends at v-1               -> extend lo to v
 *     - only hi starts at v+1             -> new interval starting at v with hi's end; drop hi
 *     - otherwise                         -> new single-point interval [v, v]
 *   addNum O(log n), getIntervals O(n).
 */
class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();
        sr.addNum(1);
        print(sr.getIntervals()); // [[1, 1]]
        sr.addNum(3);
        print(sr.getIntervals()); // [[1, 1], [3, 3]]
        sr.addNum(7);
        print(sr.getIntervals()); // [[1, 1], [3, 3], [7, 7]]
        sr.addNum(2);
        print(sr.getIntervals()); // [[1, 3], [7, 7]]
        sr.addNum(6);
        print(sr.getIntervals()); // [[1, 3], [6, 7]]
    }

    private final TreeMap<Integer, Integer> intervals = new TreeMap<>(); // start -> end

    public SummaryRanges() {}

    public void addNum(int value) {
        Integer lo = intervals.floorKey(value);    // interval starting at or before value
        Integer hi = intervals.higherKey(value);   // first interval starting after value

        if (lo != null && intervals.get(lo) >= value) return; // already inside an interval

        boolean joinLeft = lo != null && intervals.get(lo) + 1 == value;
        boolean joinRight = hi != null && hi == value + 1;

        if (joinLeft && joinRight) {
            intervals.put(lo, intervals.remove(hi)); // value bridges two intervals into one
        } else if (joinLeft) {
            intervals.put(lo, value);                // extend the left interval by one
        } else if (joinRight) {
            intervals.put(value, intervals.remove(hi)); // grow the right interval to the left
        } else {
            intervals.put(value, value);             // isolated number
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[intervals.size()][];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : intervals.entrySet()) {
            res[i++] = new int[] {e.getKey(), e.getValue()};
        }
        return res;
    }
}
