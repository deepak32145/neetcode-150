package neetcode150.problems.p37;

import static neetcode150.common.Util.*;

/*
 * 37. Sudoku Solver  [Hard]
 * https://leetcode.com/problems/sudoku-solver/
 *
 * Problem:
 *   Fill a 9x9 Sudoku board in-place. Empty cells are '.'.
 *
 * Example:
 *   A valid board with some '.' cells is completed so every row, column, and 3x3 box
 *   contains 1-9 exactly once.
 *
 * Solution - backtracking:
 *   Find the next '.', try digits 1-9 that do not clash in row/col/box, recurse; undo on fail.
 *   Time: O(9^empty)   Space: O(1) extra besides recursion
 */
class Solution {
    public static void main(String[] args) {
        char[][] board = board(
            "53..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79"
        );
        new Solution().solveSudoku(board);
        print(board[0]); // [5, 3, 4, 6, 7, 8, 9, 1, 2]
    }

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') continue;
                for (char d = '1'; d <= '9'; d++) {
                    if (!valid(board, r, c, d)) continue;
                    board[r][c] = d;
                    if (solve(board)) return true;
                    board[r][c] = '.';
                }
                return false;
            }
        }
        return true;
    }

    private boolean valid(char[][] board, int r, int c, char d) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == d || board[i][c] == d) return false;
            int br = (r / 3) * 3 + i / 3, bc = (c / 3) * 3 + i % 3;
            if (board[br][bc] == d) return false;
        }
        return true;
    }
}
