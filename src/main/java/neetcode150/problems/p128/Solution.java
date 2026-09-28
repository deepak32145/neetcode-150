package neetcode150.problems.p128;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 128. Longest Consecutive Sequence  [Medium]  -  Arrays & Hashing
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Problem:
 *   Return the length of the longest run of consecutive integers in an unsorted array, in O(n).
 *
 * Example:
 *   Input:  nums = [100, 4, 200, 1, 3, 2]
 *   Output: 4                 ([1, 2, 3, 4])
 *
 * Solution - hash set, count only from sequence starts:
 *   Put all numbers in a set. A number starts a sequence only if num - 1 is not in the set;
 *   from there count num + 1, num + 2, ... Each number is visited a constant number of times.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().longestConsecutive(new int[] {100, 4, 200, 1, 3, 2})); // 4
        print(new Solution().longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // 9
    }

    public int longestConsecutive(int[] nums) {
       if (nums.length == 0) return 0;
       HashSet<Integer> hs = new HashSet<>();
       for(int num:nums) hs.add(num);
       int longest =1;
       for(int num: nums ){
           //check if the num is the start of a sequence by checking if left exists
           if(!hs.contains(num-1)){ // start of a sequence
                int count =1;
                while(hs.contains(num + 1)){ // check if hs contains next no.
                    num++;
                    count++;
                }
                longest = Math.max(longest, count);
                
           }
           if(longest > nums.length/2) break;

       }
       return longest;
    }
}
