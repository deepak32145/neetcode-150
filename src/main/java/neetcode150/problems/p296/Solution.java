package neetcode150.problems.p296;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 296. Best Meeting Point  [Hard, Premium]
 * https://leetcode.com/problems/best-meeting-point/
 *
 * Problem:
 *   Grid with 1s for friends' homes. Choose a meeting cell minimizing the total Manhattan distance
 *   from every home to it. Return that total distance.
 *
 * Example:
 *   Input:  [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]   Output: 6
 *
 * Solution - Manhattan distance separates into independent row and column problems:
 *   total = sum |r - R| + sum |c - C|. Each sum is minimized when the meeting coordinate is the
 *   MEDIAN of the coordinates. So gather all home rows and all home columns (sorted), take the
 *   medians, and add up the distances. Scanning row-major gives sorted rows for free; scanning
 *   column-major gives sorted columns.
 *   Time: O(m*n)   Space: O(homes)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.minTotalDistance(new int[][] {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}})); // 6
        print(s.minTotalDistance(new int[][] {{1, 1}})); // 1
    }

    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) if (grid[r][c] == 1) rows.add(r); // sorted by construction
        }
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < m; r++) if (grid[r][c] == 1) cols.add(c); // sorted by construction
        }
        return distanceToMedian(rows) + distanceToMedian(cols);
    }

    /** Sum of distances to the median of an already-sorted list: pair outermost elements inward. */
    private int distanceToMedian(List<Integer> sorted) {
        int sum = 0;
        for (int lo = 0, hi = sorted.size() - 1; lo < hi; lo++, hi--) {
            sum += sorted.get(hi) - sorted.get(lo); // each pair contributes its gap, wherever the median sits
        }
        return sum;
    }
}
