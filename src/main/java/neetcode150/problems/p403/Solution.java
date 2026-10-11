package neetcode150.problems.p403;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 403. Frog Jump  [Hard]
 * https://leetcode.com/problems/frog-jump/
 *
 * Problem:
 *   A frog starts on the first stone and its first jump must be 1 unit. If its last jump was k
 *   units, the next jump must be k-1, k or k+1 units. It can only land on stones. Can it reach the
 *   last stone?
 *
 * Example:
 *   Input:  stones = [0,1,3,5,6,8,12,17]   Output: true
 *
 * Solution - for every stone, the set of jump sizes that can land on it:
 *   jumps[pos] = all k such that the frog can arrive at pos with a last jump of size k.
 *   Process stones left to right. For each k arriving at pos, the frog may next jump k-1, k, k+1;
 *   if pos + step is a stone (and step > 0), record `step` in that stone's set.
 *   The last stone is reachable iff its set is non-empty. Seeding jumps[0] = {0} makes the first
 *   forced jump of size 1 fall out of the same rule (0 + {-1,0,1} -> only 1 is positive).
 *   Time: O(n^2)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.canCross(new int[] {0, 1, 3, 5, 6, 8, 12, 17})); // true
        print(s.canCross(new int[] {0, 1, 2, 3, 4, 8, 9, 11})); // false
    }

    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> jumps = new HashMap<>();
        for (int stone : stones) jumps.put(stone, new HashSet<>());
        jumps.get(stones[0]).add(0);

        for (int stone : stones) {
            for (int k : jumps.get(stone)) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step <= 0) continue;
                    Set<Integer> target = jumps.get(stone + step);
                    if (target != null) target.add(step); // can land on stone+step using a jump of `step`
                }
            }
        }
        return !jumps.get(stones[stones.length - 1]).isEmpty();
    }
}
