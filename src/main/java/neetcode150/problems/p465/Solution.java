package neetcode150.problems.p465;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 465. Optimal Account Balancing  [Hard, Premium]
 * https://leetcode.com/problems/optimal-account-balancing/
 *
 * Problem:
 *   transactions[i] = [from, to, amount]. Return the minimum number of transactions needed to
 *   settle all debts.
 *
 * Example:
 *   Input:  [[0,1,10],[2,0,5]]   Output: 2
 *   Input:  [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]   Output: 1
 *
 * Solution - net balances + backtracking:
 *   Only each person's NET balance matters (sum of what they are owed minus what they owe). Drop
 *   everyone at zero. Then settle greedily with backtracking: take the first person with a
 *   non-zero balance and try to cancel it against every later person with the opposite sign. After
 *   that transfer the first person is settled (balance 0), so recurse on the rest and count 1
 *   transaction. Take the minimum over choices.
 *   Pruning: if a transfer zeroes BOTH people exactly, that is always optimal, so stop trying
 *   other partners.
 *   Time: O((n-1)!) worst case, n <= 12 non-zero balances   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.minTransfers(new int[][] {{0, 1, 10}, {2, 0, 5}})); // 2
        print(s.minTransfers(new int[][] {{0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}})); // 1
    }

    private int[] debts;

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> net = new HashMap<>();
        for (int[] t : transactions) {
            net.merge(t[0], -t[2], Integer::sum); // giver's balance goes down
            net.merge(t[1], t[2], Integer::sum);  // receiver's balance goes up
        }
        debts = net.values().stream().filter(v -> v != 0).mapToInt(Integer::intValue).toArray();
        return settle(0);
    }

    /** Minimum transactions to settle debts[start..], assuming debts before start are settled. */
    private int settle(int start) {
        while (start < debts.length && debts[start] == 0) start++;
        if (start == debts.length) return 0;

        int best = Integer.MAX_VALUE;
        for (int i = start + 1; i < debts.length; i++) {
            if ((long) debts[i] * debts[start] >= 0) continue; // same sign (or zero): cannot cancel

            int original = debts[i];
            debts[i] += debts[start];                    // start transfers everything to/from i
            best = Math.min(best, 1 + settle(start + 1));
            debts[i] = original;                         // backtrack

            if (original + debts[start] == 0) break;     // perfect cancel: no better partner exists
        }
        return best;
    }
}
