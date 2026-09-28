package neetcode150.problems.p136;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 136. Single Number  [Easy]  -  Bit Manipulation
 * https://leetcode.com/problems/single-number/
 *
 * Problem:
 *   Every element appears twice except one. Find it in O(n) time and O(1) space.
 *
 * Example:
 *   Input:  nums = [4, 1, 2, 1, 2]
 *   Output: 4
 *
 * Solution - XOR everything:
 *   a ^ a = 0 and a ^ 0 = a, so all pairs cancel and only the single number is left.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().singleNumber(new int[] {2, 2, 1})); // 1
        print(new Solution().singleNumber(new int[] {4, 1, 2, 1, 2})); // 4
    }

    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) ans ^= nums[i];
        return ans;
    }
}
