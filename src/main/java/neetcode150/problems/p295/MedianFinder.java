package neetcode150.problems.p295;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 295. Find Median from Data Stream  [Hard]  -  Heap / Priority Queue
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * Problem:
 *   Support addNum(num) and findMedian() over a growing stream of numbers.
 *
 * Example:
 *   addNum(1); addNum(2); findMedian() -> 1.5; addNum(3); findMedian() -> 2.0
 *
 * Solution - two heaps:
 *   smallHeap (a max-heap) holds the lower half, largeHeap (a min-heap) holds the upper half.
 *   After each insert, rebalance so every small value <= every large value and the sizes differ
 *   by at most 1. The median is the top of the bigger heap, or the average of both tops.
 *   Time: addNum O(log n), findMedian O(1)   Space: O(n)
 */
class MedianFinder {
    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        print(finder.findMedian()); // 1.5
        finder.addNum(3);
        print(finder.findMedian()); // 2.0
    }

    private Queue<Integer> smallHeap; //small elements - maxHeap
    private Queue<Integer> largeHeap; //large elements - minHeap

    public MedianFinder() {
        smallHeap = new PriorityQueue<>((a, b) -> b - a);
        largeHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        smallHeap.add(num);
        if (
            smallHeap.size() - largeHeap.size() > 1 ||
            !largeHeap.isEmpty() &&
            smallHeap.peek() > largeHeap.peek()
        ) {
            largeHeap.add(smallHeap.poll());
        }
        if (largeHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(largeHeap.poll());
        }
    }

    public double findMedian() {
        if (smallHeap.size() == largeHeap.size()) {
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        } else if (smallHeap.size() > largeHeap.size()) {
            return (double) smallHeap.peek();
        } else {
            return (double) largeHeap.peek();
        }
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
