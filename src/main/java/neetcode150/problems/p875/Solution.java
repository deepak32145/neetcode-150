package neetcode150.problems.p875;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 875. Koko Eating Bananas  [Medium]  -  Binary Search
 * https://leetcode.com/problems/koko-eating-bananas/
 *
 * Problem:
 *   Koko eats up to k bananas per hour from one pile. Find the minimum k so she finishes all
 *   piles within h hours.
 *
 * Example:
 *   Input:  piles = [3, 6, 7, 11], h = 8
 *   Output: 4
 *
 * Solution - binary search on the answer:
 *   k lies between 1 and the largest pile. For a middle k, hours = sum of ceil(pile / k). If it
 *   fits in h, try smaller (right = mid); otherwise larger (left = mid + 1).
 *   Time: O(n log max(pile))   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().minEatingSpeed(new int[] {3, 6, 7, 11}, 8)); // 4
        print(new Solution().minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 5)); // 30
        print(new Solution().minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 6)); // 23
    }

    public int minEatingSpeed(int[] piles, int h) {
        // Initalize the left and right boundaries
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middle = (left + right) / 2;
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middle);
            }

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return right;
    }
}
