package neetcode150.problems.p84;

import java.util.*;
import neetcode150.common.Pair;
import static neetcode150.common.Util.*;

/*
 * 84. Largest Rectangle in Histogram  [Hard]  -  Stack
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Problem:
 *   Given bar heights of width 1, return the area of the largest rectangle in the histogram.
 *
 * Example:
 *   Input:  heights = [2, 1, 5, 6, 2, 3]
 *   Output: 10                (bars 5 and 6: 5 * 2)
 *
 * Solution - monotonic increasing stack of (startIndex, height):
 *   When a shorter bar arrives, pop taller bars: each popped bar's rectangle ends here, so
 *   area = h * (i - start). The new bar inherits the start index of the last popped bar
 *   (it can extend back that far). Bars left on the stack extend to the end: h * (n - start).
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3})); // 10
        print(new Solution().largestRectangleArea(new int[] {2, 4})); // 4
    }

    public int largestRectangleArea(int[] heights) {
      int area = 0, n = heights.length;
      int start;
      Stack<Pair<Integer,Integer>> stack = new Stack<>();
      for(int i=0;i<heights.length;i++){
         int curHt =heights[i];
         start = i;
        while(!stack.isEmpty() && stack.peek().getValue() > curHt){
          Pair<Integer,Integer> pair = stack.pop();
          int index = pair.getKey();
          int h = pair.getValue();
          area = Math.max(area, h * (i - index));
          start = index;
        }
        stack.push(new Pair(start,curHt));
      }
      
       while(!stack.isEmpty()){
          Pair<Integer,Integer> pair = stack.pop();
          int index = pair.getKey();
          int h = pair.getValue();
          area = Math.max(area, h * (n - index));
       }
        return area;
    }
  
}
