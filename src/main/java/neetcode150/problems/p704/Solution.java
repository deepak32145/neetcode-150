package neetcode150.problems.p704;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 704. Binary Search  [Easy]  -  Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * Problem:
 *   Search target in a sorted array; return its index or -1, in O(log n).
 *
 * Example:
 *   Input:  nums = [-1, 0, 3, 5, 9, 12], target = 9
 *   Output: 4
 *
 * Solution - classic binary search:
 *   mid = i + (j - i) / 2 (avoids overflow). Found -> return; mid too small -> i = mid + 1;
 *   too big -> j = mid - 1.
 *   Time: O(log n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().search(new int[] {-1, 0, 3, 5, 9, 12}, 9)); // 4
        print(new Solution().search(new int[] {-1, 0, 3, 5, 9, 12}, 2)); // -1
    }

    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            // mid is calculated this way to prevent integer overflow.
            // See: https://blog.research.google/2006/06/extra-extra-read-all-about-it-nearly.html
            int mid = i + (j - i) / 2;

            if (nums[mid] == target) return mid; else if (
                nums[mid] < target
            ) i = mid + 1; else j = mid - 1;
        }

        return -1;
    }
}
