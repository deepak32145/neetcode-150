package neetcode150.problems.p52;

import static neetcode150.common.Util.*;

/*
 * 52. N-Queens II  [Hard]
 * https://leetcode.com/problems/n-queens-ii/
 *
 * Problem:
 *   Return how many distinct ways n queens can be placed on an n x n board with no two attacking.
 *
 * Example:
 *   Input:  n = 4
 *   Output: 2
 *
 * Solution - backtracking with bit / boolean arrays:
 *   Place one queen per row; track used columns and both diagonals.
 *   Time: O(n!)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().totalNQueens(4)); // 2
        print(new Solution().totalNQueens(1)); // 1
    }

    public int totalNQueens(int n) {
        return dfs(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
    }

    private int dfs(int row, int n, boolean[] col, boolean[] diag, boolean[] anti) {
        if (row == n) return 1;
        int ways = 0;
        for (int c = 0; c < n; c++) {
            int d = row - c + n, a = row + c;
            if (col[c] || diag[d] || anti[a]) continue;
            col[c] = diag[d] = anti[a] = true;
            ways += dfs(row + 1, n, col, diag, anti);
            col[c] = diag[d] = anti[a] = false;
        }
        return ways;
    }
}
