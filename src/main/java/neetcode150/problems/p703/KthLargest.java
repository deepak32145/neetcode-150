package neetcode150.problems.p703;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 703. Kth Largest Element in a Stream  [Easy]  -  Heap / Priority Queue
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * Problem:
 *   Design a class that returns the k-th largest element after each new number is added.
 *
 * Example:
 *   KthLargest(3, [4, 5, 8, 2]); add(3) -> 4; add(5) -> 5; add(10) -> 5; add(9) -> 8; add(4) -> 8
 *
 * Solution - min-heap of size k:
 *   Keep only the k largest values. While the heap has fewer than k, just add; otherwise replace
 *   the top only if the new value is bigger. The top is the k-th largest.
 *   Time: O(log k) per add   Space: O(k)
 */
class KthLargest {
    public static void main(String[] args) {
        KthLargest kth = new KthLargest(3, new int[] {4, 5, 8, 2});
        print(kth.add(3)); // 4
        print(kth.add(5)); // 5
        print(kth.add(10)); // 5
        print(kth.add(9)); // 8
        print(kth.add(4)); // 8
    }

    final PriorityQueue<Integer> heap = new PriorityQueue<>();
    final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int n : nums) add(n);
    }

    public int add(int val) {
        if (heap.size() < k) heap.offer(val); //for adding the values of the array
        else if (val > heap.peek()) {
            heap.poll(); //remove the top element
            heap.add(val); //add the new element
        }
        return heap.peek();
    }
}
