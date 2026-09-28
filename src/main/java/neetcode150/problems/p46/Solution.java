package neetcode150.problems.p46;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 46. Permutations  [Medium]  -  Backtracking
 * https://leetcode.com/problems/permutations/
 *
 * Problem:
 *   Given an array of distinct ints, return all possible orderings.
 *
 * Example:
 *   Input:  nums = [1, 2, 3]
 *   Output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
 *
 * Solution - backtracking by swapping in place:
 *   For position `start`, swap each index i >= start into it, recurse on start + 1, then swap
 *   back. When start == length, copy the array into the answer.
 *   Time: O(n * n!)   Space: O(n) recursion
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().permute(new int[] {1, 2, 3})); // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        function(ans, nums, 0);
        return ans;
    }

    public void function(List<List<Integer>> ans, int[] arr, int start) {
        if (start == arr.length) {
            List<Integer> list = new ArrayList();
            for (int i = 0; i < arr.length; i++) list.add(arr[i]);
            ans.add(list);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            function(ans, arr, start + 1);
            swap(arr, start, i);
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
