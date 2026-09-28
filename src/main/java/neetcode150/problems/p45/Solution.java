package neetcode150.problems.p45;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 45. Jump Game II  [Medium]  -  Greedy
 * https://leetcode.com/problems/jump-game-ii/
 *
 * Problem:
 *   nums[i] is the max jump length from index i. Return the minimum number of jumps to reach
 *   the last index (always reachable).
 *
 * Example:
 *   Input:  nums = [2, 3, 1, 1, 4]
 *   Output: 2                 (0 -> 1 -> 4)
 *
 * Solution - greedy BFS by levels:
 *   [l, r] is the range reachable with `res` jumps. Scan it to find the farthest index
 *   reachable with one more jump; that becomes the next range [r + 1, farthest]. Count levels
 *   until r reaches the end.
 *   Time: O(n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().jump(new int[] {2, 3, 1, 1, 4})); // 2
        print(new Solution().jump(new int[] {2, 3, 0, 1, 4})); // 2
    }

    public int jump(int[] nums) {
        int res = 0, r = 0, l = 0, fur = 0;

        while (r < nums.length - 1) {
            fur = 0;
            for (int i = l; i <= r; i++) fur = Math.max(fur, i + nums[i]);
            l = r + 1;
            r = fur;
            res++;
        }
        return res;
    }
}
