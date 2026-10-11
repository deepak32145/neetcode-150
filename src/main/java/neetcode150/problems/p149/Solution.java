package neetcode150.problems.p149;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 149. Max Points on a Line  [Hard]
 * https://leetcode.com/problems/max-points-on-a-line/
 *
 * Problem:
 *   Given 2D points, return the max number that lie on one straight line.
 *
 * Example:
 *   Input:  [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]   Output: 4
 *
 * Solution - fix an anchor point, count points by slope:
 *   For each anchor i, every other point j defines a direction (dx, dy). Two points are on the same
 *   line through the anchor iff their reduced directions are equal. Reduce by gcd and normalize the
 *   sign so (1,2) and (-1,-2) collapse. Using integer pairs avoids floating-point slope errors.
 *   The best line through anchor i has (max count of one direction) + 1 points.
 *   Time: O(n^2)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.maxPoints(new int[][] {{1, 1}, {2, 2}, {3, 3}})); // 3
        print(s.maxPoints(new int[][] {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}})); // 4
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int best = 0;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> byDirection = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int g = gcd(Math.abs(dx), Math.abs(dy));
                dx /= g;
                dy /= g;
                // Normalize sign so opposite directions on the same line share one key.
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                int count = byDirection.merge(dx + "/" + dy, 1, Integer::sum);
                best = Math.max(best, count + 1); // +1 for the anchor itself
            }
        }
        return best;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
