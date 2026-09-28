package neetcode150.problems.p79;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 79. Word Search  [Medium]  -  Backtracking
 * https://leetcode.com/problems/word-search/
 *
 * Problem:
 *   Does word exist in the grid as a path of horizontally / vertically adjacent cells, using
 *   each cell at most once?
 *
 * Example:
 *   Input:  board = ["ABCE", "SFCS", "ADEE"], word = "ABCCED"
 *   Output: true
 *
 * Solution - DFS backtracking from every cell:
 *   check() matches word[cur] at (i, j), marks the cell as used by adding 100 to its char,
 *   tries the four neighbours for cur + 1, then restores the cell.
 *   Time: O(m * n * 3^L), L = word length   Space: O(L) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().exist(board("ABCE", "SFCS", "ADEE"), "ABCCED")); // true
        print(new Solution().exist(board("ABCE", "SFCS", "ADEE"), "SEE")); // true
        print(new Solution().exist(board("ABCE", "SFCS", "ADEE"), "ABCB")); // false
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check(board, word, i, j, m, n, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(
        char[][] board,
        String word,
        int i,
        int j,
        int m,
        int n,
        int cur
    ) {
        if (cur >= word.length()) return true;
        if (
            i < 0 ||
            j < 0 ||
            i >= m ||
            j >= n ||
            board[i][j] != word.charAt(cur)
        ) return false;
        boolean exist = false;
        if (board[i][j] == word.charAt(cur)) {
            board[i][j] += 100;
            exist =
                check(board, word, i + 1, j, m, n, cur + 1) ||
                check(board, word, i, j + 1, m, n, cur + 1) ||
                check(board, word, i - 1, j, m, n, cur + 1) ||
                check(board, word, i, j - 1, m, n, cur + 1);
            board[i][j] -= 100;
        }
        return exist;
    }
}
