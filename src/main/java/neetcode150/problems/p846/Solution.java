package neetcode150.problems.p846;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 846. Hand of Straights  [Medium]  -  Greedy
 * https://leetcode.com/problems/hand-of-straights/
 *
 * Problem:
 *   Can the cards be split into groups of groupSize consecutive values?
 *
 * Example:
 *   Input:  hand = [1, 2, 3, 6, 2, 3, 4, 7, 8], groupSize = 3
 *   Output: true              ([1, 2, 3], [2, 3, 4], [6, 7, 8])
 *
 * Solution - counts + always start from the smallest card:
 *   Count every card and sort the hand. For each card (smallest first) that is still available,
 *   it must start a group, so consume card, card + 1, ..., card + groupSize - 1; a missing card
 *   means false.
 *   Time: O(n log n + n * groupSize)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().isNStraightHand(new int[] {1, 2, 3, 6, 2, 3, 4, 7, 8}, 3)); // true
        print(new Solution().isNStraightHand(new int[] {1, 2, 3, 4, 5}, 4)); // false
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int card : hand) hm.put(card, hm.getOrDefault(card, 0) + 1);

        Arrays.sort(hand);
        for (int card : hand) {
            if (hm.get(card) <= 0) continue;
            for (int i = 1; i < groupSize; i++) {
                int count = hm.getOrDefault(card + i, 0);
                if (count > 0) hm.put(card + i, count - 1); else return false;
            }
            hm.put(card, hm.get(card) - 1);
        }

        return true;
    }
}
