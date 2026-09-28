package neetcode150.problems.p15;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 15. 3Sum  [Medium]  -  Two Pointers
 * https://leetcode.com/problems/3sum/
 *
 * Problem:
 *   Return all unique triplets [a, b, c] from nums with a + b + c == 0.
 *
 * Example:
 *   Input:  nums = [-1, 0, 1, 2, -1, -4]
 *   Output: [[-1, -1, 2], [-1, 0, 1]]
 *
 * Solution - sort + two pointers:
 *   Sort nums. Fix nums[i] (skip it if equal to the previous one to avoid duplicates) and
 *   look for a pair summing to -nums[i] in the rest with left/right pointers. On a hit, save
 *   it and skip over duplicate left/right values. Too small -> left++, too big -> right--.
 *   Time: O(n^2)   Space: O(1) extra (ignoring output and sort)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().threeSum(new int[] {-1, 0, 1, 2, -1, -4})); // [[-1, -1, 2], [-1, 0, 1]]
        print(new Solution().threeSum(new int[] {0, 0, 0})); // [[0, 0, 0]]
    }

    //2 pointers
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> sol = new LinkedList<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            //Only consider non-duplicate elements for i
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int target = 0 - nums[i];
                int left = i + 1;
                int right = nums.length - 1;

                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        sol.add(List.of(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }

        return sol;
    }
}
