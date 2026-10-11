package neetcode150.problems.p458;

import static neetcode150.common.Util.*;

/*
 * 458. Poor Pigs  [Hard]
 * https://leetcode.com/problems/poor-pigs/
 *
 * Problem:
 *   `buckets` buckets, exactly one poisonous. A pig dies minutesToDie minutes after drinking it.
 *   You have minutesToTest minutes total. Minimum pigs needed to find the poison bucket?
 *
 * Example:
 *   Input:  buckets = 4, minutesToDie = 15, minutesToTest = 15   Output: 2
 *
 * Solution - information theory, each pig is a digit in base `states`:
 *   rounds = minutesToTest / minutesToDie. A pig can end in rounds + 1 distinguishable states:
 *   it dies in round 1, 2, ..., rounds, or survives. With p pigs the outcomes form a p-digit number
 *   in base (rounds + 1), which can identify (rounds + 1)^p buckets. Arrange buckets as points of
 *   that grid: pig i drinks every bucket whose digit i equals the current round.
 *   Smallest p with (rounds + 1)^p >= buckets.
 *   Time: O(log buckets)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.poorPigs(4, 15, 15)); // 2
        print(s.poorPigs(4, 15, 30)); // 2
        print(s.poorPigs(1000, 15, 60)); // 5
        print(s.poorPigs(1, 1, 1)); // 0
    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1; // distinguishable outcomes per pig
        int pigs = 0;
        long capacity = 1; // buckets distinguishable with `pigs` pigs
        while (capacity < buckets) {
            capacity *= states;
            pigs++;
        }
        return pigs;
    }
}
