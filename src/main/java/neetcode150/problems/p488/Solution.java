package neetcode150.problems.p488;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 488. Zuma Game  [Hard]
 * https://leetcode.com/problems/zuma-game/
 *
 * Problem:
 *   A row of colored balls (board) and balls in hand. Each move inserts one ball from hand into
 *   the row; any group of 3+ same-colored consecutive balls is removed (chain reactions repeat).
 *   Return the minimum insertions to clear the board, or -1 if impossible.
 *
 * Example:
 *   Input:  board = "WWRRBBWW", hand = "WRBRW"   Output: 2
 *
 * Solution - DFS with memoization and pruning:
 *   State = (board, hand). Try every insertion position i and every distinct hand color c, apply
 *   the removal cascade, recurse with one fewer ball.
 *   Pruning keeps the search small:
 *     - sort the hand so identical colors are adjacent, and skip repeats of the same color;
 *     - only insert c at i when it can help: next to a ball of the same color, OR between two
 *       equal balls of a DIFFERENT color (to break them apart and set up a later match).
 *   clean() removes runs of 3+ and repeats until stable. Memoize on board + "#" + hand.
 *   Time: exponential, tiny in practice (board <= 16, hand <= 5)   Space: memo size
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findMinStep("WRRBBW", "RB")); // -1
        print(s.findMinStep("WWRRBBWW", "WRBRW")); // 2
        print(s.findMinStep("G", "GGGGG")); // 2
        print(s.findMinStep("RBYYBBRRB", "YRBGB")); // 3
    }

    private static final int INF = 1_000_000;
    private Map<String, Integer> memo;

    public int findMinStep(String board, String hand) {
        memo = new HashMap<>();
        char[] h = hand.toCharArray();
        Arrays.sort(h); // identical balls become adjacent, enabling the "skip duplicates" prune
        int res = dfs(board, new String(h));
        return res >= INF ? -1 : res;
    }

    private int dfs(String board, String hand) {
        if (board.isEmpty()) return 0;
        if (hand.isEmpty()) return INF;

        String key = board + "#" + hand;
        Integer cached = memo.get(key);
        if (cached != null) return cached;

        int best = INF;
        for (int j = 0; j < hand.length(); j++) {
            if (j > 0 && hand.charAt(j) == hand.charAt(j - 1)) continue; // same color already tried
            char c = hand.charAt(j);
            String rest = hand.substring(0, j) + hand.substring(j + 1);

            for (int i = 0; i <= board.length(); i++) {
                boolean sameAsLeft = i > 0 && board.charAt(i - 1) == c;
                boolean splitsPair = i > 0 && i < board.length()
                        && board.charAt(i - 1) == board.charAt(i) && board.charAt(i) != c;
                if (!sameAsLeft && !splitsPair) continue; // inserting here cannot help

                String next = clean(board.substring(0, i) + c + board.substring(i));
                best = Math.min(best, 1 + dfs(next, rest));
            }
        }
        memo.put(key, best);
        return best;
    }

    /** Repeatedly remove any run of 3+ equal characters until none remain. */
    private String clean(String s) {
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) j++;
            if (j - i >= 3) return clean(s.substring(0, i) + s.substring(j)); // removal may cascade
            i = j;
        }
        return s;
    }
}
