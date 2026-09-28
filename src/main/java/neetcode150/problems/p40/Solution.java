package neetcode150.problems.p40;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 40. Combination Sum II  [Medium]  -  Backtracking
 * https://leetcode.com/problems/combination-sum-ii/
 *
 * Problem:
 *   Like Combination Sum, but candidates may contain duplicates and each element can be used at
 *   most once. Return unique combinations that sum to target.
 *
 * Example:
 *   Input:  candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
 *   Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
 *
 * Solution - sort + backtracking that skips duplicates:
 *   Sort first. In each recursion level loop i from index; skip candidates[i] when it equals
 *   candidates[i - 1] at the same level (i > index), so equal values don't start duplicate
 *   branches. Recurse with i + 1 since each element is used once.
 *   Time: O(2^n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8)); // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> ls = new ArrayList<Integer>();
        comb(candidates, target, ans, ls, 0);
        return ans;
    }

    public void comb(
        int[] candidates,
        int target,
        List<List<Integer>> ans,
        List<Integer> ls,
        int index
    ) {
        if (target == 0) {
            ans.add(new ArrayList(ls));
        } else if (target < 0) return; else {
            for (int i = index; i < candidates.length; i++) {
                if (i > index && candidates[i] == candidates[i - 1]) continue;
                ls.add(candidates[i]);
                comb(candidates, target - candidates[i], ans, ls, i + 1);
                ls.remove(ls.get(ls.size() - 1));
            }
        }
    }
}
