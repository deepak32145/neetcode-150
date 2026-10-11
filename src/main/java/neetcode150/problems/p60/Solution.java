package neetcode150.problems.p60;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 60. Permutation Sequence  [Hard]
 * https://leetcode.com/problems/permutation-sequence/
 *
 * Problem:
 *   Return the k-th permutation (1-indexed) of the numbers 1..n, in lexicographic order.
 *
 * Example:
 *   Input:  n = 3, k = 3
 *   Output: "213"
 *
 * Solution - factorial number system:
 *   There are (n-1)! permutations for each choice of the first digit. Pick the digit at
 *   index (k-1) / (n-1)!, shrink k, repeat.
 *   Time: O(n^2)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().getPermutation(3, 3)); // "213"
        print(new Solution().getPermutation(4, 9)); // "2314"
    }

    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fact *= i;
        }
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            fact /= i;
            sb.append(nums.remove(k / fact));
            k %= fact;
        }
        return sb.toString();
    }
}
