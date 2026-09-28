package neetcode150.problems.p90;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 90. Subsets II  [Medium]  -  Backtracking
 * https://leetcode.com/problems/subsets-ii/
 *
 * Problem:
 *   nums may contain duplicates. Return all subsets without duplicate subsets.
 *
 * Example:
 *   Input:  nums = [1, 2, 2]
 *   Output: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
 *
 * Solution - sort + skip duplicates at the same level:
 *   Sort. Every call adds the current list as a subset, then tries each next element; when
 *   nums[i] == nums[i - 1] for i > idx it is skipped so the same value doesn't start two
 *   identical branches.
 *   Time: O(n * 2^n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().subsetsWithDup(new int[] {1, 2, 2})); // [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]] (any order)
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        subSet(nums, 0, ans, list);
        return ans;
    }

    public void subSet(
        int[] nums,
        int idx,
        List<List<Integer>> ans,
        List<Integer> list
    ) {
        ans.add(new ArrayList<>(list));

        for (int i = idx; i < nums.length; i++) {
            //skip the duplicate elements
            if (i > idx && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            subSet(nums, i + 1, ans, list);
            list.remove(list.size() - 1);
        }
    }
}
