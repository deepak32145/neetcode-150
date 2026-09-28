package neetcode150.problems.p190;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 190. Reverse Bits  [Easy]  -  Bit Manipulation
 * https://leetcode.com/problems/reverse-bits/
 *
 * Problem:
 *   Reverse the 32 bits of an unsigned integer.
 *
 * Example:
 *   Input:  00000010100101000001111010011100  (43261596)
 *   Output: 00111001011110000010100101000000  (964176192)
 *
 * Solution - shift bit by bit:
 *   32 times: shift ans left, copy n's lowest bit into it (ans |= n & 1), shift n right.
 *   Time: O(32) = O(1)   Space: O(1)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().reverseBits(43261596)); // 964176192
    }

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= (n & 1);
            n >>= 1;
        }
        return ans;
    }
}
