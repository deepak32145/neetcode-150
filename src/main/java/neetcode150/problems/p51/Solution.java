package neetcode150.problems.p51;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 51. N-Queens  [Hard]  -  Backtracking
 * https://leetcode.com/problems/n-queens/
 *
 * Problem:
 *   Place n queens on an n x n board so none attack each other. Return every distinct board
 *   ('Q' for a queen, '.' for empty).
 *
 * Example:
 *   Input:  n = 4
 *   Output: [[".Q..", "...Q", "Q...", "..Q."], ["..Q.", "Q...", "...Q", ".Q.."]]
 *
 * Solution - backtracking row by row:
 *   Place one queen per row. For each column in the current row, isSafe checks the column above
 *   and both upper diagonals. If safe, place it, recurse to the next row, then remove it.
 *   When all rows are filled, convert the board to strings.
 *   (Faster: track used columns / diagonals in sets for O(1) checks.)
 *   Time: O(n! * n)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().solveNQueens(4)); // [[".Q..", "...Q", "Q...", "..Q."], ["..Q.", "Q...", "...Q", ".Q.."]]
        print(new Solution().solveNQueens(1)); // [["Q"]]
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<List<String>>();
        boolean[][] board = new boolean[n][n];
        queens(board, 0, ans);
        return ans;
    }

    public void queens(boolean[][] board, int row, List<List<String>> ans2) {
        //base case
        if (row == board.length) {
            ArrayList<String> ans = new ArrayList<String>();
            createAnswer(board, ans);
            ans2.add(ans);
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                queens(board, row + 1, ans2);
                board[row][col] = false;
            }
        }
    }

    public void createAnswer(boolean[][] board, ArrayList<String> ans) {
        for (int i = 0; i < board.length; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]) {
                    str.append("Q");
                } else str.append(".");
            }
            ans.add(str.toString());
        }
    }

    public boolean isSafe(boolean[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[row - i][col - i]) {
                return false;
            }
        }
        int maxRight = Math.min(row, board.length - 1 - col);
        for (int i = 1; i <= maxRight; i++) {
            if (board[row - i][col + i]) return false;
        }
        return true;
    }
}



// Solution 2:
/*
 * This solution uses 3 hashsets to check whether the current queen has conflicts with previous
 * columns and two diagonals, avoiding the use of 2D boolean array and the isSafe checking function.
 * 
 */
