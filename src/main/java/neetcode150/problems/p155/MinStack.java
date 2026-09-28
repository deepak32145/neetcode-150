package neetcode150.problems.p155;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 155. Min Stack  [Medium]  -  Stack
 * https://leetcode.com/problems/min-stack/
 *
 * Problem:
 *   Design a stack that supports push, pop, top and getMin, all in O(1).
 *
 * Example:
 *   push(-2); push(0); push(-3); getMin() -> -3; pop(); top() -> 0; getMin() -> -2
 *
 * Solution - a second stack of running minimums:
 *   minStack stores, for every level of the main stack, the minimum up to that level. push adds
 *   min(val, current min); pop removes from both stacks together.
 *   Time: O(1) per operation   Space: O(n)
 */
class MinStack {
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        print(stack.getMin()); // -3
        stack.pop();
        print(stack.top()); // 0
        print(stack.getMin()); // -2
    }

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

        // The min stack may be empty, so we need to check it
        val = Math.min(val, minStack.isEmpty() ? val : minStack.peek());
        minStack.push(val);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
