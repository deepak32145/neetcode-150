package neetcode150.problems.p215;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 215. Kth Largest Element in an Array  [Medium]  -  Heap / Priority Queue
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Problem:
 *   Return the k-th largest element of an unsorted array.
 *
 * Example:
 *   Input:  nums = [3, 2, 1, 5, 6, 4], k = 2
 *   Output: 5
 *
 * Solution - min-heap of size k:
 *   Push each number; when the heap grows beyond k, pop the smallest. The heap keeps the k
 *   largest values and its top is the k-th largest. (Quickselect: O(n) on average.)
 *   Time: O(n log k)   Space: O(k)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2)); // 5
        print(new Solution().findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)); // 4
    }

    public int findKthLargest(int[] nums, int k) {
        //create a min heap
        PriorityQueue<Integer> heap = new PriorityQueue();

        //iterate over the array
        for (int n : nums) {
            //first add the integer to heap
            heap.add(n);
            //if size of the heap is greater than k
            if (heap.size() > k) {
                //remove the root element (lowest of all)
                heap.poll();
            }
        }
        //finally heap has k largest elements left with root as the kth largest element
        return heap.peek();
    }
}
