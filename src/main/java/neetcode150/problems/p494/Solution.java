package neetcode150.problems.p494;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 494. Target Sum  [Medium]  -  2-D Dynamic Programming
 * https://leetcode.com/problems/target-sum/
 *
 * Problem:
 *   Put + or - in front of every number. Count the assignments whose total equals target.
 *
 * Example:
 *   Input:  nums = [1, 1, 1, 1, 1], target = 3
 *   Output: 5
 *
 * Solution - DFS with memo on (index, running sum):
 *   calculate(i, sum) = calculate(i + 1, sum + nums[i]) + calculate(i + 1, sum - nums[i]);
 *   at the end count 1 if sum == target. Results are cached under "i,sum".
 *   (Alternatively: count subsets with sum (total + target) / 2.)
 *   Time: O(n * total)   Space: O(n * total)
 */
public class Solution {
    public static void main(String[] args) {
        print(new Solution().findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3)); // 5
        print(new Solution().findTargetSumWays(new int[] {1}, 1)); // 1
    }

    HashMap<String, Integer> dp;
   
    public int findTargetSumWays(int[] nums, int S) {
        dp = new HashMap<>();
        return calculate(nums, 0, 0, S);
    }
    
    public int calculate(int[] nums, int i, int sum, int S) {
        String s = i + "," + sum;
        
        if (i == nums.length) {
           return (sum == S)? 1 : 0; 
        }
        if(dp.containsKey(s)){
            return dp.get(s); 
        }

        int res = calculate(nums, i + 1, sum + nums[i], S) + calculate(nums, i + 1, sum - nums[i], S);
        dp.put(s, res);
        return res;
    }
}

/*                                 Alternative Better Complexity Solution
-------------------------------------------------------------------------------------------------------
//Brute-force solution (accepted)
// Subset Sum DP solution (Recursive DP solution for java exceeds time limit)

 * Calculate for the sum of all the potential positive numbers (targetSum)
 *
 * Formula: targetSum = (∑nums + target) / 2 
 * (must be even otherwise there's no valid answer so return 0)
 *
 * Return all the possible ways to get the targetSum
 * since the remaining numbers would be negative we just need to account 
 * for the sum of the positive numbers (targetSum)
 *
 * The formula for the targetSum was derived as follows:
 * P = potential positive numbers
 * N = potential negative numbers
 * ∑P - ∑N = target
 * ∑P - ∑N + ∑nums = target + ∑nums
 * ∑P - ∑N + (∑P + ∑N) = target + ∑nums       
 * ∑P + ∑P = target + ∑nums
 * 2 * ∑P = target + ∑nums
 * (2 * ∑P) / 2 = (target + ∑nums) / 2
 * ∑P = (target + ∑nums) / 2
 -------------------------------------------------------------------------------
 */
