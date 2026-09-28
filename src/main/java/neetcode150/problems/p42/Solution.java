package neetcode150.problems.p42;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 42. Trapping Rain Water  [Hard]  -  Two Pointers
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Problem:
 *   Given bar heights (width 1 each), compute how much rain water is trapped between them.
 *
 * Example:
 *   Input:  height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 *   Output: 6
 *
 * Solution - prefix max from both sides:
 *   left[i] = tallest bar from the start through i, right[i] = tallest bar from i to the end.
 *   Water above bar i = min(left[i], right[i]) - height[i]. Sum it up.
 *   (The two-pointer version gets the same result in O(1) space.)
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
        print(new Solution().trap(new int[] {4, 2, 0, 3, 2, 5})); // 9
    }

    public int trap(int[] heights) {
        int left[] = new int[heights.length], right[] = new int[heights.length], max =
            heights[0], c = 0;

        for (int i = 0; i < heights.length; i++) {
            left[i] = Math.max(heights[i], max);
            max = left[i];
        }

        max = heights[heights.length - 1];
        for (int i = heights.length - 1; i >= 0; i--) {
            right[i] = Math.max(heights[i], max);
            max = right[i];
        }

        for (int i = 0; i < heights.length; i++) {
            c = c + Math.min(left[i], right[i]) - heights[i];
        }
        return c;
    }
}

//O(1) space
