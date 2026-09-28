package neetcode150.problems.p287;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 287. Find the Duplicate Number  [Medium]  -  Linked List
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Problem:
 *   nums has n + 1 numbers in the range [1, n], with exactly one value repeated. Find it without
 *   modifying nums and with O(1) extra space.
 *
 * Example:
 *   Input:  nums = [1, 3, 4, 2, 2]
 *   Output: 2
 *
 * Solution - Floyd's cycle detection on i -> nums[i]:
 *   Treat each value as a "next pointer". The duplicate is where the cycle begins.
 *   Phase 1: slow / fast pointers meet inside the cycle.
 *   Phase 2: start a second pointer at 0; moving both one step at a time, they meet at the
 *   cycle entrance = the duplicate.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findDuplicate(new int[] {1, 3, 4, 2, 2})); // 2
        print(new Solution().findDuplicate(new int[] {3, 1, 3, 4, 2})); // 3
    }

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        while (slow != fast);

        int slow2 = 0;

        do {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        while (slow != slow2);

        return slow2;
    }
}
