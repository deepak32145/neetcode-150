package neetcode150.problems.p1;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 1. Two Sum  [Easy]  -  Arrays & Hashing
 * https://leetcode.com/problems/two-sum/
 *
 * Problem:
 *   Given an int array nums and an int target, return the indices of the two numbers that
 *   add up to target. Exactly one answer exists and the same element can't be used twice.
 *
 * Example:
 *   Input:  nums = [2, 7, 11, 15], target = 9
 *   Output: [0, 1]            (nums[0] + nums[1] = 2 + 7 = 9)
 *
 * Solution - one-pass hash map:
 *   Walk the array. For each num, the partner we need is diff = target - num.
 *   If diff was already seen (stored in map value -> index), return both indices;
 *   otherwise remember num -> i and continue.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().twoSum(new int[] {2, 7, 11, 15}, 9)); // [0, 1]
        print(new Solution().twoSum(new int[] {3, 2, 4}, 6)); // [1, 2]
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> prevMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;

            if (prevMap.containsKey(diff)) {
                return new int[] { prevMap.get(diff), i };
            }

            prevMap.put(num, i);
        }

        return new int[] {};
    }
}
