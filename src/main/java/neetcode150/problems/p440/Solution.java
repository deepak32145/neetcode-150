package neetcode150.problems.p440;

import static neetcode150.common.Util.*;

/*
 * 440. K-th Smallest in Lexicographical Order  [Hard]
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
 *
 * Problem:
 *   Among the integers 1..n sorted lexicographically ("1","10","100",...,"2",...), return the
 *   k-th one.
 *
 * Example:
 *   Input:  n = 13, k = 2   Output: 10   (order: 1, 10, 11, 12, 13, 2, 3, ...)
 *
 * Solution - walk a 10-ary prefix trie without building it:
 *   Think of numbers as nodes of a trie where children of p are 10p .. 10p+9. Lexicographic order
 *   is a preorder walk. Start at cur = 1 and use k-1 more steps. For the current prefix, count
 *   how many numbers (<= n) live in its subtree:
 *     - if that count <= remaining k: the answer is not in this subtree, skip it -> move to the
 *       next sibling (cur + 1) and subtract the count
 *     - otherwise the answer is inside: step down to the first child (cur * 10), using 1 step.
 *   countSubtree adds the sizes level by level: [first, last] range at each depth, clipped to n.
 *   Time: O(log^2 n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findKthNumber(13, 2)); // 10
        print(s.findKthNumber(1, 1)); // 1
        print(s.findKthNumber(100, 10)); // 17
    }

    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--; // cur = 1 is the first number; k more steps remain
        while (k > 0) {
            long count = countSubtree(cur, n);
            if (count <= k) {
                cur++;       // skip the whole subtree under cur, move to the next sibling
                k -= count;
            } else {
                cur *= 10;   // answer is inside this subtree: go to the first child
                k--;
            }
        }
        return (int) cur;
    }

    /** Number of integers in [1, n] that have `prefix` as a decimal prefix (including itself). */
    private long countSubtree(long prefix, long n) {
        long count = 0;
        long first = prefix, last = prefix;
        while (first <= n) {
            count += Math.min(n, last) - first + 1; // numbers of this length with the prefix, clipped to n
            first *= 10;
            last = last * 10 + 9;
        }
        return count;
    }
}
