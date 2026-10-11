package neetcode150.problems.p330;

import static neetcode150.common.Util.*;

/*
 * 330. Patching Array  [Hard]
 * https://leetcode.com/problems/patching-array/
 *
 * Problem:
 *   Given a sorted array nums, add the minimum number of elements so that every integer in [1, n]
 *   can be formed as a sum of some elements.
 *
 * Example:
 *   Input:  nums = [1,5,10], n = 20   Output: 2   (patch 2 and 4)
 *
 * Solution - greedy on the smallest unreachable sum:
 *   Let `miss` be the smallest value in [1, n] we cannot yet form; everything below miss is
 *   reachable. If the next array element x <= miss, taking it extends reach to miss + x.
 *   Otherwise miss is stuck, so patch with miss itself (the best possible number, since it doubles
 *   the reach to miss + miss). Repeat until miss > n.
 *   Time: O(len(nums) + log n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.minPatches(new int[] {1, 3}, 6)); // 1
        print(s.minPatches(new int[] {1, 5, 10}, 20)); // 2
        print(s.minPatches(new int[] {1, 2, 2}, 5)); // 0
    }

    public int minPatches(int[] nums, int n) {
        long miss = 1; // smallest sum not yet reachable (long: doubling can pass int range)
        int patches = 0, i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++]; // use an existing number: all of [1, miss + nums[i]) now reachable
            } else {
                miss += miss;      // patch with `miss`: doubles the reachable range
                patches++;
            }
        }
        return patches;
    }
}
