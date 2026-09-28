package neetcode150.problems.p217;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 217. Contains Duplicate  [Easy]  -  Arrays & Hashing
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Problem:
 *   Return true if any value appears at least twice in the array.
 *
 * Example:
 *   Input:  nums = [1, 2, 3, 1]
 *   Output: true
 *
 * Solution - hash set:
 *   Add numbers one by one; if a number is already in the set we found a duplicate.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().containsDuplicate(new int[] {1, 2, 3, 1})); // true
        print(new Solution().containsDuplicate(new int[] {1, 2, 3, 4})); // false
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> uniques = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (uniques.contains(nums[i])) {
                return true;
            }
            uniques.add(nums[i]);
        }
        return false;
    }
}
