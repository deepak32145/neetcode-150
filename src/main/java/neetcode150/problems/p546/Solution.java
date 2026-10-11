package neetcode150.problems.p546;

import static neetcode150.common.Util.*;

/*
 * 546. Remove Boxes  [Hard]
 * https://leetcode.com/problems/remove-boxes/
 *
 * Problem:
 *   Remove contiguous runs of same-colored boxes; removing k boxes at once scores k * k. Return the
 *   maximum total score for removing all boxes.
 *
 * Example:
 *   Input:  boxes = [1,3,2,2,2,3,4,3,1]   Output: 23
 *
 * Solution - interval DP with an extra "boxes stuck to the left" dimension:
 *   Plain interval DP fails because the best order may first remove boxes in between to make two
 *   same-colored boxes touch. So define
 *       f(l, r, k) = best score for boxes[l..r] when k boxes equal to boxes[l] are attached
 *                    (already adjacent) on its left.
 *   Choices for the leftmost box:
 *     1. Remove it now together with its k attached boxes:   (k+1)^2 + f(l+1, r, 0)
 *     2. Keep it for later: for any m in (l, r] with boxes[m] == boxes[l], first clear the middle
 *        l+1..m-1 (as an independent problem), which makes boxes[l] touch boxes[m]:
 *            f(l+1, m-1, 0) + f(m, r, k+1)
 *   Small optimization: swallow immediately adjacent equal boxes into k.
 *   Memoize on (l, r, k).
 *   Time: O(n^4) worst, O(n^3) states   Space: O(n^3)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.removeBoxes(new int[] {1, 3, 2, 2, 2, 3, 4, 3, 1})); // 23
        print(s.removeBoxes(new int[] {1, 1, 1})); // 9
        print(s.removeBoxes(new int[] {1})); // 1
    }

    private int[] boxes;
    private int[][][] memo;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        this.boxes = boxes;
        memo = new int[n][n][n];
        return dfs(0, n - 1, 0);
    }

    private int dfs(int l, int r, int k) {
        if (l > r) return 0;
        if (memo[l][r][k] != 0) return memo[l][r][k];

        // Absorb directly adjacent equal boxes: removing them together is never worse.
        int origL = l, origK = k;
        while (l < r && boxes[l + 1] == boxes[l]) {
            l++;
            k++;
        }

        // Option 1: remove boxes[l] and everything stuck to it right now.
        int best = (k + 1) * (k + 1) + dfs(l + 1, r, 0);

        // Option 2: first clear the gap up to a later box of the same color, so they merge.
        for (int m = l + 1; m <= r; m++) {
            if (boxes[m] == boxes[l]) {
                best = Math.max(best, dfs(l + 1, m - 1, 0) + dfs(m, r, k + 1));
            }
        }
        memo[origL][r][origK] = best;
        return best;
    }
}
