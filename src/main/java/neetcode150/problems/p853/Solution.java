package neetcode150.problems.p853;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 853. Car Fleet  [Medium]  -  Stack
 * https://leetcode.com/problems/car-fleet/
 *
 * Problem:
 *   Cars drive toward target and can't pass each other; a faster car that catches a slower one
 *   joins it as a fleet. How many fleets arrive?
 *
 * Example:
 *   Input:  target = 12, position = [10, 8, 0, 5, 3], speed = [2, 4, 1, 1, 3]
 *   Output: 3
 *
 * Solution - sort by position, compare arrival times:
 *   Go from the car closest to target backwards and compute each car's arrival time. If a car
 *   would arrive no later than the fleet ahead (top of the stack), it merges into that fleet;
 *   otherwise it starts a new fleet (push).
 *   Time: O(n log n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().carFleet(12, new int[] {10, 8, 0, 5, 3}, new int[] {2, 4, 1, 1, 3})); // 3
        print(new Solution().carFleet(10, new int[] {3}, new int[] {3})); // 1
        print(new Solution().carFleet(100, new int[] {0, 2, 4}, new int[] {4, 2, 1})); // 1
    }

    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 1) return 1;
        Stack<Double> stack = new Stack<>();
        int[][] combine = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            combine[i][0] = position[i];
            combine[i][1] = speed[i];
        }

        Arrays.sort(combine, java.util.Comparator.comparingInt(o -> o[0]));
        for (int i = combine.length - 1; i >= 0; i--) {
            double currentTime = (double) (target - combine[i][0]) /
            combine[i][1];
            if (!stack.isEmpty() && currentTime <= stack.peek()) {
                continue;
            } else {
                stack.push(currentTime);
            }
        }
        return stack.size();
    }
}
