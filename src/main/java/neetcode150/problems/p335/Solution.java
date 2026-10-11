package neetcode150.problems.p335;

import static neetcode150.common.Util.*;

/*
 * 335. Self Crossing  [Hard]
 * https://leetcode.com/problems/self-crossing/
 *
 * Problem:
 *   Start at (0,0), move distance[0] north, distance[1] west, distance[2] south, distance[3] east,
 *   and so on, turning counter-clockwise each time. Return true if the path crosses itself.
 *
 * Example:
 *   Input:  distance = [2,1,1,2]   Output: true
 *
 * Solution - a new segment can only cross one of three earlier segments:
 *   Let d[i] be the current segment. Because every turn is 90 degrees counter-clockwise, the only
 *   segments it can touch are i-3, i-4 and i-5 (anything older is walled off by the spiral).
 *     1. Crosses i-3:   d[i] >= d[i-2]  and  d[i-1] <= d[i-3]       (spiral turning inward)
 *     2. Touches i-4:   d[i-1] == d[i-3] and d[i] + d[i-4] >= d[i-2] (exact overlap on a corner)
 *     3. Crosses i-5:   d[i-2] >= d[i-4], d[i] + d[i-4] >= d[i-2],
 *                       d[i-1] <= d[i-3], d[i-1] + d[i-5] >= d[i-3]
 *   If none ever fires, the path is a non-crossing spiral (outward then possibly inward).
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.isSelfCrossing(new int[] {2, 1, 1, 2})); // true
        print(s.isSelfCrossing(new int[] {1, 2, 3, 4})); // false
        print(s.isSelfCrossing(new int[] {1, 1, 1, 2, 1})); // true
        print(s.isSelfCrossing(new int[] {1, 1, 2, 1, 1})); // true
    }

    public boolean isSelfCrossing(int[] d) {
        for (int i = 3; i < d.length; i++) {
            // Case 1: the fourth segment crosses the first (current line crosses the one 3 back).
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            // Case 2: the fifth segment touches the first (lines i and i-4 overlap).
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) return true;
            // Case 3: the sixth segment crosses the first (spiral closing in over a longer arc).
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i] + d[i - 4] >= d[i - 2]
                    && d[i - 1] <= d[i - 3] && d[i - 1] + d[i - 5] >= d[i - 3]) return true;
        }
        return false;
    }
}
