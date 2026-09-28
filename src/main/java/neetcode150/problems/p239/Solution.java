package neetcode150.problems.p239;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 239. Sliding Window Maximum  [Hard]  -  Sliding Window
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Problem:
 *   Return the maximum of every window of size k as it slides from left to right.
 *
 * Example:
 *   Input:  nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 *   Output: [3, 3, 5, 5, 6, 7]
 *
 * Solution - monotonic decreasing deque of indices:
 *   Drop the front index when it leaves the window. Before adding i, pop smaller values from
 *   the back - they can never be a maximum again. The front of the deque is always the max of
 *   the current window.
 *   Time: O(n)   Space: O(k)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3)); // [3, 3, 5, 5, 6, 7]
        print(new Solution().maxSlidingWindow(new int[] {1}, 1)); // [1]
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int j = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!q.isEmpty() && q.peekFirst() < i - k + 1) q.pollFirst();
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) q.pollLast();
            q.offer(i);
            if (i >= k - 1) ans[j++] = nums[q.peekFirst()];
        }
        return ans;
    }
}
