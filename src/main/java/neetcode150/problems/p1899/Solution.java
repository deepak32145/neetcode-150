package neetcode150.problems.p1899;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1899. Merge Triplets to Form Target Triplet  [Medium]  -  Greedy
 * https://leetcode.com/problems/merge-triplets-to-form-target-triplet/
 *
 * Problem:
 *   Merging two triplets takes the max of each position. Can some merges produce exactly
 *   target?
 *
 * Example:
 *   Input:  triplets = [[2, 5, 3], [1, 8, 4], [1, 7, 5]], target = [2, 7, 5]
 *   Output: true              (merge [2, 5, 3] and [1, 7, 5])
 *
 * Solution - greedy filter:
 *   Ignore any triplet with a value larger than target in some position (it would overshoot).
 *   From the rest, check that each of the three target values is matched by at least one
 *   triplet.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().mergeTriplets(new int[][] {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}}, new int[] {2, 7, 5})); // true
        print(new Solution().mergeTriplets(new int[][] {{3, 4, 5}, {4, 5, 6}}, new int[] {3, 2, 5})); // false
        print(new Solution().mergeTriplets(new int[][] {{2, 5, 3}, {2, 3, 4}, {1, 2, 5}, {5, 2, 3}}, new int[] {5, 5, 5})); // true
    }

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] greedy = new boolean[3];
        loop:for (int[] triplet : triplets) {
            for (int i = 0; i < 3; i++) if (
                triplet[i] > target[i]
            ) continue loop;

            for (int i = 0; i < 3; i++) if (triplet[i] == target[i]) greedy[i] =
                true;
        }

        return greedy[0] && greedy[1] && greedy[2];
    }
}
