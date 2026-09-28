package neetcode150.problems.p55;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 55. Jump Game  [Medium]  -  Greedy
 * https://leetcode.com/problems/jump-game/
 *
 * Problem:
 *   nums[i] is the max jump length from index i. Starting at index 0, can you reach the last
 *   index?
 *
 * Example:
 *   Input:  nums = [2, 3, 1, 1, 4]   Output: true
 *   Input:  nums = [3, 2, 1, 0, 4]   Output: false
 *
 * Solution - move the goal backwards:
 *   goal starts at the last index. Walking from right to left, if i + nums[i] >= goal then i can
 *   reach the goal, so i becomes the new goal. Answer: goal == 0.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().canJump(new int[] {2, 3, 1, 1, 4})); // true
        print(new Solution().canJump(new int[] {3, 2, 1, 0, 4})); // false
    }

    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
}
