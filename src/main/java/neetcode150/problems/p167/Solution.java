package neetcode150.problems.p167;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 167. Two Sum II - Input Array Is Sorted  [Medium]  -  Two Pointers
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Problem:
 *   numbers is sorted ascending. Return the 1-based indices of the two numbers adding up to
 *   target, using O(1) extra space.
 *
 * Example:
 *   Input:  numbers = [2, 7, 11, 15], target = 9
 *   Output: [1, 2]
 *
 * Solution - two pointers:
 *   Start at both ends. Sum too small -> move left up; too big -> move right down; equal ->
 *   done.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().twoSum(new int[] {2, 7, 11, 15}, 9)); // [1, 2]
        print(new Solution().twoSum(new int[] {2, 3, 4}, 6)); // [1, 3]
    }

    public int[] twoSum(int[] numbers, int target) {
        int a_pointer = 0;
        int b_pointer = numbers.length - 1;
        int num_a, num_b;

        while (a_pointer < b_pointer) {
            num_a = numbers[a_pointer];
            num_b = numbers[b_pointer];

            if (num_a + num_b == target) break;

            if (num_a + num_b < target) {
                a_pointer++;
                continue;
            }

            b_pointer--;
        }

        return new int[] { a_pointer + 1, b_pointer + 1 };
    }
}
