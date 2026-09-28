package neetcode150.problems.p1851;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1851. Minimum Interval to Include Each Query  [Hard]  -  Intervals
 * https://leetcode.com/problems/minimum-interval-to-include-each-query/
 *
 * Problem:
 *   For each query q, return the size (right - left + 1) of the smallest interval containing q,
 *   or -1.
 *
 * Example:
 *   Input:  intervals = [[1, 4], [2, 4], [3, 6], [4, 4]], queries = [2, 3, 4, 5]
 *   Output: [3, 3, 1, 4]
 *
 * Solution - sort both + min-heap by interval size (offline queries):
 *   Sort intervals by start and queries by value (keeping original indexes). For each query,
 *   push every interval that starts <= q, pop intervals that end before q, then the heap top is
 *   the smallest interval covering q.
 *   Time: O(n log n + q log q)   Space: O(n + q)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().minInterval(new int[][] {{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[] {2, 3, 4, 5})); // [3, 3, 1, 4]
        print(new Solution().minInterval(new int[][] {{2, 3}, {2, 5}, {1, 8}, {20, 25}}, new int[] {2, 19, 5, 22})); // [2, -1, 4, 6]
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        // book-keeping & sorting
        int numIntervals = intervals.length;
        int numQueries = queries.length;

        // Sort by start times
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));

        Query[] sortedQueries = new Query[numQueries];
        for (int i = 0; i < numQueries; i++) sortedQueries[i] =
            new Query(i, queries[i]);

        Arrays.sort(
            sortedQueries,
            (q1, q2) -> (q1.queryTimeStamp - q2.queryTimeStamp)
        );

        // algorithm

        Comparator<int[]> comparator = new IntervalComparator();
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        int idx = 0;

        for (Query query : sortedQueries) {
            // 1. Keep taking all those queries which have lower starting time than the query time and add them to priority queue
            while (
                (idx < numIntervals) &&
                (query.queryTimeStamp >= intervals[idx][0])
            ) {
                pq.add(intervals[idx]);
                idx++;
            }

            // 2. Keep removing the inconsistent intervals and get the min size interval from priority queue
            while (!pq.isEmpty() && (pq.peek()[1] < query.queryTimeStamp)) {
                pq.remove();
            }

            // Now, priority queue must have the consistent & smallest interval
            int ans = pq.isEmpty() ? -1 : IntervalComparator.getSize(pq.peek());
            query.setResult(ans);
        }

        // reconversion
        int[] results = new int[numQueries];
        for (Query query : sortedQueries) {
            results[query.index] = query.result;
        }

        return results;
    }
}

class IntervalComparator implements Comparator<int[]> {

    // smaller interval first; ties broken by earlier end
    @Override
    public int compare(int[] a, int[] b) {
        int diff = getSize(a) - getSize(b);
        return diff != 0 ? diff : a[1] - b[1];
    }

    static int getSize(int[] interval) {
        return interval[1] - interval[0] + 1;
    }
}

class Query {
    int index;
    int queryTimeStamp;
    int result;

    Query(int index, int queryTimeStamp) {
        this.index = index;
        this.queryTimeStamp = queryTimeStamp;
    }

    void setResult(int result) {
        this.result = result;
    }
}
