package neetcode150.problems.p130;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 130. Surrounded Regions  [Medium]  -  Graphs
 * https://leetcode.com/problems/surrounded-regions/
 *
 * Problem:
 *   In a board of 'X' and 'O', flip every region of 'O' that is fully surrounded by 'X' into
 *   'X'. Regions touching the border survive.
 *
 * Example:
 *   Input:  ["XXXX", "XOOX", "XXOX", "XOXX"]
 *   Output: ["XXXX", "XXXX", "XXXX", "XOXX"]
 *
 * Solution - reverse thinking, DFS from the border:
 *   1) DFS from every border 'O' and mark its region 'T' (safe).
 *   2) Every remaining 'O' is surrounded -> 'X'.
 *   3) Turn 'T' back into 'O'.
 *   Time: O(m * n)   Space: O(m * n) recursion worst case
 */
class Solution {
    public static void main(String[] args) {
        char[][] grid = board("XXXX", "XOOX", "XXOX", "XOXX");
        new Solution().solve(grid);
        print(grid); // [[X, X, X, X], [X, X, X, X], [X, X, X, X], [X, O, X, X]]
    }

    public void solve(char[][] board) {
        int nRows = board.length;
        int nCols = board[0].length;

        // 1a) Capture unsurrounded regions - top and bottom row (O -> T)
        for (int i = 0; i < nCols; i++) {
            if (board[0][i] == 'O') dfs(board, 0, i);
            if (board[nRows - 1][i] == 'O') dfs(board, nRows - 1, i);
        }

        // 1b) Capture unsurrounded regions - Left and right columns (O -> T)
        for (int i = 0; i < nRows; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][nCols - 1] == 'O') dfs(board, i, nCols - 1);
        }

        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X'; // 2) Capture surrounded regions (O -> X)
                if (board[r][c] == 'T') board[r][c] = 'O'; // 3) Uncapture unsurrounded regions (T- O)
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        int nRows = board.length;
        int nCols = board[0].length;
        if (
            r < 0 || c < 0 || r >= nRows || c >= nCols || board[r][c] != 'O'
        ) return;

        board[r][c] = 'T';
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}
