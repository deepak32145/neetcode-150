package neetcode150.problems.p548;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 548. Split Array with Equal Sum  [Hard, Premium]
 * https://leetcode.com/problems/split-array-with-equal-sum/
 *
 * Problem:
 *   Does there exist i, j, k with 0 < i, i+1 < j, j+1 < k, k < n-1 such that the four sums
 *   sum(0..i-1), sum(i+1..j-1), sum(j+1..k-1), sum(k+1..n-1) are all equal? (Elements at i, j, k
 *   are the cut points and are excluded.)
 *
 * Example:
 *   Input:  nums = [1,2,1,2,1,2,1]   Output: true   (i=1, j=3, k=5)
 *
 * Solution - fix the middle cut j, meet in the middle with a set:
 *   With prefix sums every range sum is O(1). For each j:
 *     left side:  for every i < j where sum(0..i-1) == sum(i+1..j-1), store that common sum in a set.
 *     right side: for every k > j where sum(j+1..k-1) == sum(k+1..n-1), the common sum must already
 *                 be in the set from the left side for the same j.
 *   One hit means all four parts are equal. Using the set per j turns the naive O(n^3) into O(n^2).
 *   Time: O(n^2)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.splitArray(new int[] {1, 2, 1, 2, 1, 2, 1})); // true
        print(s.splitArray(new int[] {1, 2, 1, 2, 1, 2, 1, 2})); // false
    }

    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7) return false; // need 4 non-empty parts plus 3 cut elements
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];

        for (int j = 3; j < n - 3; j++) { // j leaves room for i on its left and k on its right
            Set<Integer> leftSums = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                int first = pre[i];                   // sum(0..i-1)
                int second = pre[j] - pre[i + 1];     // sum(i+1..j-1)
                if (first == second) leftSums.add(first);
            }
            for (int k = j + 2; k < n - 1; k++) {
                int third = pre[k] - pre[j + 1];      // sum(j+1..k-1)
                int fourth = pre[n] - pre[k + 1];     // sum(k+1..n-1)
                if (third == fourth && leftSums.contains(third)) return true;
            }
        }
        return false;
    }
}
