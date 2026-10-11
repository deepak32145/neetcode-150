package neetcode150.problems.p218;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 218. The Skyline Problem  [Hard]
 * https://leetcode.com/problems/the-skyline-problem/
 *
 * Problem:
 *   Given buildings [left, right, height], return the key points (x, height) of the skyline.
 *
 * Example:
 *   Input:  [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 *   Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 *
 * Solution - sweep line with a multiset of active heights:
 *   Turn each building into two events: (left, -height) = start, (right, +height) = end. Sort by x;
 *   at equal x, the sign trick orders them so that taller starts come first, then shorter ends first
 *   (this avoids spurious dips between touching buildings).
 *   Sweep: a start adds the height to a TreeMap multiset, an end removes one copy. After each event
 *   the current skyline height is the max key. Emit a point whenever it changes.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.getSkyline(new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
        // [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]
        print(s.getSkyline(new int[][] {{0, 2, 3}, {2, 5, 3}})); // [[0, 3], [5, 0]]
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        for (int[] b : buildings) {
            events.add(new int[] {b[0], -b[2]}); // start: negative height
            events.add(new int[] {b[1], b[2]});  // end: positive height
        }
        events.sort((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        TreeMap<Integer, Integer> active = new TreeMap<>(); // height -> how many active buildings
        active.put(0, 1); // ground level so the map is never empty
        int prevMax = 0;
        List<List<Integer>> res = new ArrayList<>();

        for (int[] e : events) {
            if (e[1] < 0) {
                active.merge(-e[1], 1, Integer::sum);
            } else if (active.merge(e[1], -1, Integer::sum) == 0) {
                active.remove(e[1]);
            }
            int curMax = active.lastKey();
            if (curMax != prevMax) {
                res.add(List.of(e[0], curMax));
                prevMax = curMax;
            }
        }
        return res;
    }
}
