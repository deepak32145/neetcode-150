package neetcode150.problems.p135;

import java.util.Arrays;
import static neetcode150.common.Util.*;

/*
 * 135. Candy  [Hard]
 * https://leetcode.com/problems/candy/
 *
 * Problem:
 *   Every child gets >= 1 candy; a child with a higher rating than a neighbor gets more candy
 *   than that neighbor. Return the minimum total candies.
 *
 * Example:
 *   Input:  ratings = [1,0,2]   Output: 5   (2,1,2)
 *
 * Solution - two greedy passes:
 *   Left to right: if rating[i] > rating[i-1], candy[i] = candy[i-1] + 1  (satisfies left neighbor)
 *   Right to left: if rating[i] > rating[i+1], candy[i] = max(candy[i], candy[i+1] + 1)
 *   Taking the max keeps the left-pass guarantee while also satisfying the right neighbor.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.candy(new int[] {1, 0, 2})); // 5
        print(s.candy(new int[] {1, 2, 2})); // 4
    }

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) candy[i] = candy[i - 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) candy[i] = Math.max(candy[i], candy[i + 1] + 1);
        }

        int total = 0;
        for (int c : candy) total += c;
        return total;
    }
}
