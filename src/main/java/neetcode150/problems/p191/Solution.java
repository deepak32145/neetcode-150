package neetcode150.problems.p191;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 191. Number of 1 Bits  [Easy]  -  Bit Manipulation
 * https://leetcode.com/problems/number-of-1-bits/
 *
 * Problem:
 *   Return how many bits are set to 1 in an integer (its Hamming weight).
 *
 * Example:
 *   Input:  n = 11 (binary 1011)
 *   Output: 3
 *
 * Solution - check the lowest bit 32 times:
 *   If (n & 1) != 0 count it, then shift n right. (n & (n - 1) clears the lowest set bit and
 *   would loop only once per 1 bit.)
 *   Time: O(32) = O(1)   Space: O(1)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().hammingWeight(11)); // 3
        print(new Solution().hammingWeight(128)); // 1
        print(new Solution().hammingWeight(-3)); // 31 (0b111...101)
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }
}

//smart way
