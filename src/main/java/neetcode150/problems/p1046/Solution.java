package neetcode150.problems.p1046;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1046. Last Stone Weight  [Easy]  -  Heap / Priority Queue
 * https://leetcode.com/problems/last-stone-weight/
 *
 * Problem:
 *   Repeatedly smash the two heaviest stones: equal weights both vanish, otherwise the
 *   difference remains. Return the last stone's weight (0 if none).
 *
 * Example:
 *   Input:  stones = [2, 7, 4, 1, 8, 1]
 *   Output: 1
 *
 * Solution - max-heap (a min-heap of negated values):
 *   Poll the two largest; if they differ push back the difference. Repeat until at most one
 *   stone remains.
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().lastStoneWeight(new int[] {2, 7, 4, 1, 8, 1})); // 1
        print(new Solution().lastStoneWeight(new int[] {1})); // 1
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue();
        for (int stone : stones) maxHeap.add(-stone);
        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.remove();
            int stone2 = maxHeap.remove();
            if (stone1 != stone2) maxHeap.add(stone1 - stone2);
        }
        return maxHeap.size() != 0 ? (-maxHeap.remove()) : 0;
    }
}
