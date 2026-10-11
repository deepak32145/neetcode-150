package neetcode150.problems.p446;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 446. Arithmetic Slices II - Subsequence  [Hard]
 * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 *
 * Problem:
 *   Count arithmetic subsequences (length >= 3, constant difference) of nums.
 *
 * Example:
 *   Input:  nums = [2,4,6,8,10]   Output: 7
 *
 * Solution - DP keyed by difference:
 *   dp[i].get(d) = number of arithmetic subsequences of length >= 2 that END at index i with
 *   common difference d (a pair counts, even though it is not yet a valid answer).
 *   For every earlier j < i with d = nums[i] - nums[j]:
 *     - dp[j].get(d) sequences can be extended by nums[i]; each becomes length >= 3, so they are
 *       real answers: answer += dp[j].get(d)
 *     - dp[i][d] += dp[j][d] (the extended ones) + 1 (the new pair (j, i))
 *   Differences can exceed int range, so use long keys.
 *   Time: O(n^2)   Space: O(n^2)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.numberOfArithmeticSlices(new int[] {2, 4, 6, 8, 10})); // 7
        print(s.numberOfArithmeticSlices(new int[] {7, 7, 7, 7, 7})); // 16
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        @SuppressWarnings("unchecked")
        Map<Long, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++) dp[i] = new HashMap<>();

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - nums[j];
                int endingAtJ = dp[j].getOrDefault(diff, 0); // sequences (len >= 2) ready to be extended
                answer += endingAtJ;                         // each extension has length >= 3
                dp[i].merge(diff, endingAtJ + 1, Integer::sum); // extended ones + the new pair (j, i)
            }
        }
        return answer;
    }
}
