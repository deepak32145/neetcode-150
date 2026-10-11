package neetcode150.problems.p85;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 85. Maximal Rectangle  [Hard]
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * Problem:
 *   In a binary matrix, return the area of the largest rectangle containing only 1's.
 *
 * Example:
 *   matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *   Output: 6
 *
 * Solution - histogram per row:
 *   height[c] is consecutive 1's ending at the current row. Then largest rectangle in
 *   histogram (monotonic stack) on each row; take the max area.
 *   Time: O(m*n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        char[][] m = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        print(new Solution().maximalRectangle(m)); // 6
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length, best = 0;
        int[] h = new int[n];
        for (char[] row : matrix) {
            for (int c = 0; c < n; c++) h[c] = row[c] == '1' ? h[c] + 1 : 0;
            best = Math.max(best, largest(h));
        }
        return best;
    }

    private int largest(int[] h) {
        Deque<Integer> st = new ArrayDeque<>();
        int best = 0, n = h.length;
        for (int i = 0; i <= n; i++) {
            int cur = i == n ? 0 : h[i];
            while (!st.isEmpty() && cur < h[st.peek()]) {
                int height = h[st.pop()];
                int left = st.isEmpty() ? -1 : st.peek();
                best = Math.max(best, height * (i - left - 1));
            }
            st.push(i);
        }
        return best;
    }
}
