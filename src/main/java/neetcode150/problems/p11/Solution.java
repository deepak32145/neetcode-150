package neetcode150.problems.p11;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 11. Container With Most Water  [Medium]  -  Two Pointers
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Problem:
 *   height[i] is the height of a vertical line at x = i. Choose two lines that, with the
 *   x-axis, hold the most water. Return that maximum area.
 *
 * Example:
 *   Input:  height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
 *   Output: 49                (lines at index 1 and 8: min(8, 7) * 7)
 *
 * Solution - two pointers from both ends:
 *   area = (right - left) * min(height[left], height[right]). Record the max, then move the
 *   pointer with the shorter line inward - moving the taller one can never give more water.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7})); // 49
        print(new Solution().maxArea(new int[] {1, 1})); // 1
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            int containerLength = right - left;
            int area = containerLength * Math.min(height[left], height[right]);
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
