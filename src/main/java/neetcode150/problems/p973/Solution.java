package neetcode150.problems.p973;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 973. K Closest Points to Origin  [Medium]  -  Heap / Priority Queue
 * https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * Problem:
 *   Return the k points closest to (0, 0) (any order).
 *
 * Example:
 *   Input:  points = [[3, 3], [5, -1], [-2, 4]], k = 2
 *   Output: [[3, 3], [-2, 4]]
 *
 * Solution - min-heap on squared distance:
 *   Push all points into a min-heap ordered by x^2 + y^2 (no sqrt needed) and poll k times.
 *   (A max-heap capped at size k gives O(n log k).)
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().kClosest(new int[][] {{1, 3}, {-2, 2}}, 1)); // [[-2, 2]]
        print(new Solution().kClosest(new int[][] {{3, 3}, {5, -1}, {-2, 4}}, 2)); // [[3, 3], [-2, 4]] (any order)
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) ->
            Integer.compare(
                (a[0] * a[0] + a[1] * a[1]),
                (b[0] * b[0] + b[1] * b[1])
            )
        );
        for (int[] point : points) {
            q.add(point);
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] cur = q.poll();
            ans[i][0] = cur[0];
            ans[i][1] = cur[1];
        }
        return ans;
    }
}

//This approach is a sightly optimized approach here we can use a max heap and maintain its size as k.
//So when we do the removal the time complexity will reduce from logn to logk
//Max heap because we will remove the top elements (the one which are greater)
//Overall Time complexity O(NlogK)
