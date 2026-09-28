package neetcode150.problems.p39;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 39. Combination Sum  [Medium]  -  Backtracking
 * https://leetcode.com/problems/combination-sum/
 *
 * Problem:
 *   Given distinct candidates and a target, return all unique combinations that sum to target.
 *   Each number may be used any number of times.
 *
 * Example:
 *   Input:  candidates = [2, 3, 6, 7], target = 7
 *   Output: [[2, 2, 3], [7]]
 *
 * Solution - include / skip backtracking:
 *   At candidates[index] either take it again (stay on index, target shrinks) or skip to
 *   index + 1. Record the list when target hits 0; stop when target < 0 or out of candidates.
 *   Time: O(2^t) roughly, t = target / min(candidate)   Space: O(t) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().combinationSum(new int[] {2, 3, 6, 7}, 7)); // [[2, 2, 3], [7]]
        print(new Solution().combinationSum(new int[] {2, 3, 5}, 8)); // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList();
        backtrack(candidates, target, ans, cur, 0);
        return ans;
    }

    public void backtrack(
        int[] candidates,
        int target,
        List<List<Integer>> ans,
        List<Integer> cur,
        int index
    ) {
        if (target == 0) {
            ans.add(new ArrayList(cur));
        } else if (target < 0 || index >= candidates.length) {
            return;
        } else {
            cur.add(candidates[index]);
            backtrack(candidates, target - candidates[index], ans, cur, index);

            cur.remove(cur.get(cur.size() - 1));
            backtrack(candidates, target, ans, cur, index + 1);
        }
    }
}
