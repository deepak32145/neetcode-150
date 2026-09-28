package neetcode150.problems.p33;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 33. Search in Rotated Sorted Array  [Medium]  -  Binary Search
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Problem:
 *   A sorted array of distinct ints was rotated at an unknown pivot. Return the index of target,
 *   or -1 if absent, in O(log n).
 *
 * Example:
 *   Input:  nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 *   Output: 4
 *
 * Solution - binary search on the sorted half:
 *   At each step one half around mid is sorted. If nums[l] <= nums[mid] the left half is sorted:
 *   go left only if target lies within [nums[l], nums[mid]). Otherwise the right half is
 *   sorted: go right only if target lies within (nums[mid], nums[r]].
 *   Time: O(log n)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        print(new Solution().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3)); // -1
    }

    public int search(int[] nums, int target) {

        int l = 0;
        int r = nums.length - 1;

        while(l<=r){

            int mid = (l+r)/2;

            if(nums[mid] == target){
                return mid;
            }
            //left sorted
            if(nums[l]<=nums[mid]){
                if(target > nums[mid] || target < nums[l]){
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }else{//right sorted
                if(target < nums[mid] || target > nums [r]){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }

        }

        return -1;
    }
}
