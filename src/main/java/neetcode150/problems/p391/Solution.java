package neetcode150.problems.p391;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 391. Perfect Rectangle  [Hard]
 * https://leetcode.com/problems/perfect-rectangle/
 *
 * Problem:
 *   Given axis-aligned rectangles [x1, y1, x2, y2], do they tile one larger rectangle exactly,
 *   with no gaps and no overlaps?
 *
 * Example:
 *   Input:  [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]   Output: true
 *
 * Solution - two conditions that together are necessary and sufficient:
 *   1. Area: the sum of the small areas equals the area of the bounding box (rules out gaps, and
 *      overlaps that are not compensated by gaps).
 *   2. Corners: in a perfect tiling every interior point is a corner of an EVEN number of tiles
 *      (2 or 4), while exactly the four corners of the bounding box are corners of exactly one tile.
 *      Toggle each rectangle's 4 corners in a set (add if absent, remove if present). At the end the
 *      set must be exactly the four bounding-box corners.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.isRectangleCover(new int[][] {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}})); // true
        print(s.isRectangleCover(new int[][] {{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}})); // false (gap)
        print(s.isRectangleCover(new int[][] {{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 2, 4, 4}})); // false (overlap)
    }

    public boolean isRectangleCover(int[][] rectangles) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        long area = 0;
        Set<String> corners = new HashSet<>();

        for (int[] r : rectangles) {
            minX = Math.min(minX, r[0]);
            minY = Math.min(minY, r[1]);
            maxX = Math.max(maxX, r[2]);
            maxY = Math.max(maxY, r[3]);
            area += (long) (r[2] - r[0]) * (r[3] - r[1]);

            // Toggle the four corners: a point that appears an even number of times cancels out.
            for (String p : new String[] {r[0] + "," + r[1], r[0] + "," + r[3], r[2] + "," + r[1], r[2] + "," + r[3]}) {
                if (!corners.add(p)) corners.remove(p);
            }
        }

        if (area != (long) (maxX - minX) * (maxY - minY)) return false;
        return corners.size() == 4
                && corners.contains(minX + "," + minY) && corners.contains(minX + "," + maxY)
                && corners.contains(maxX + "," + minY) && corners.contains(maxX + "," + maxY);
    }
}
