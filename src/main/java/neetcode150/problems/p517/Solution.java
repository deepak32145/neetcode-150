package neetcode150.problems.p517;

import static neetcode150.common.Util.*;

/*
 * 517. Super Washing Machines  [Hard]
 * https://leetcode.com/problems/super-washing-machines/
 *
 * Problem:
 *   Machines in a row hold dresses. In one move, EACH machine may pass one dress to an adjacent
 *   machine (all machines act simultaneously). Return the minimum moves to make all machines hold
 *   the same number of dresses, or -1 if impossible.
 *
 * Example:
 *   Input:  machines = [1,0,5]   Output: 3
 *
 * Solution - bottleneck on net flow across each position:
 *   If total % n != 0 the target is not an integer: -1. Otherwise avg = total / n.
 *   For machine i let diff = machines[i] - avg and let `flow` be the running sum of diffs up to i
 *   (net dresses that must cross the boundary between i and i+1; negative = must come from the right).
 *   Two lower bounds apply at every i:
 *     - |flow|: dresses crossing a single boundary move one per step.
 *     - diff:   a machine with a surplus can push out only one dress per step in total
 *               (so it needs at least `diff` moves).
 *   The answer is the maximum of those bounds over all i, and it is achievable.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findMinMoves(new int[] {1, 0, 5})); // 3
        print(s.findMinMoves(new int[] {0, 3, 0})); // 2
        print(s.findMinMoves(new int[] {0, 2, 0})); // -1
    }

    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int total = 0;
        for (int m : machines) total += m;
        if (total % n != 0) return -1;

        int avg = total / n;
        int moves = 0, flow = 0;
        for (int m : machines) {
            int diff = m - avg;  // surplus (+) or deficit (-) at this machine
            flow += diff;        // net dresses that must cross to the right of this machine
            moves = Math.max(moves, Math.max(Math.abs(flow), diff));
        }
        return moves;
    }
}
