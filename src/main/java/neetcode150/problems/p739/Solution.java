package neetcode150.problems.p739;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 739. Daily Temperatures  [Medium]  -  Stack
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Problem:
 *   For each day, return how many days you wait for a warmer temperature (0 if never).
 *
 * Example:
 *   Input:  [73, 74, 75, 71, 69, 72, 76, 73]
 *   Output: [1, 1, 4, 2, 1, 1, 0, 0]
 *
 * Solution - monotonic decreasing stack of day indexes:
 *   For each day, pop every earlier day that is colder - today is their answer
 *   (currDay - prevDay). Then push today.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73})); // [1, 1, 4, 2, 1, 1, 0, 0]
        print(new Solution().dailyTemperatures(new int[] {30, 40, 50, 60})); // [1, 1, 1, 0]
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int currDay = 0; currDay < temperatures.length; currDay++) {
            while (
                !stack.isEmpty() &&
                temperatures[currDay] > temperatures[stack.peek()]
            ) {
                int prevDay = stack.pop();
                ans[prevDay] = currDay - prevDay;
            }
            stack.add(currDay);
        }
        return ans;
    }
}
