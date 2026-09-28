package neetcode150.problems.p78;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 78. Subsets  [Medium]  -  Backtracking
 * https://leetcode.com/problems/subsets/
 *
 * Problem:
 *   Given distinct ints, return all possible subsets (the power set).
 *
 * Example:
 *   Input:  nums = [1, 2, 3]
 *   Output: [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
 *
 * Solution - include / exclude backtracking:
 *   For each index, first include nums[start] and recurse, then remove it and recurse without
 *   it. When start passes the end, copy the current list into the answer.
 *   Time: O(n * 2^n)   Space: O(n) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().subsets(new int[] {1, 2, 3})); // [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(ans, 0, nums, list);
        return ans;
    }

    public void helper(
        List<List<Integer>> ans,
        int start,
        int[] nums,
        List<Integer> list
    ) {
        if (start >= nums.length) {
            ans.add(new ArrayList<>(list));
        } else {
            // add the element and start the  recursive call
            list.add(nums[start]);
            helper(ans, start + 1, nums, list);
            // remove the element and do the backtracking call.
            list.remove(list.size() - 1);
            helper(ans, start + 1, nums, list);
        }
    }
}
