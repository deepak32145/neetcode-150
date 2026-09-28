package neetcode150.problems.p621;

import java.util.*;
import neetcode150.common.Pair;
import static neetcode150.common.Util.*;

/*
 * 621. Task Scheduler  [Medium]  -  Heap / Priority Queue
 * https://leetcode.com/problems/task-scheduler/
 *
 * Problem:
 *   Tasks are letters; the same task needs at least n time units between two runs. Each unit
 *   runs one task or idles. Return the minimum total time.
 *
 * Example:
 *   Input:  tasks = [A, A, A, B, B, B], n = 2
 *   Output: 8                 (A B idle A B idle A B)
 *
 * Solution - max-heap of counts + cooldown queue (simulation):
 *   Each time unit, run the task with the most remaining count (max-heap). If it still has runs
 *   left, park it in a queue with the time it becomes available (time + n). Move it back to the
 *   heap at that time.
 *   (Math shortcut: max(len, (maxCount - 1) * (n + 1) + numberOfTasksWithMaxCount).)
 *   Time: O(total time * log 26)   Space: O(26)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().leastInterval("AAABBB".toCharArray(), 2)); // 8
        print(new Solution().leastInterval("ACABDB".toCharArray(), 1)); // 6
        print(new Solution().leastInterval("AAABBB".toCharArray(), 3)); // 10
    }

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        int[] arr = new int[26];
        for (char c : tasks) arr[c - 'A']++;
        for (int val : arr) if (val > 0) pq.add(val);
        int time = 0;

        while ((!pq.isEmpty() || !q.isEmpty())) {
            time++;
            if (!pq.isEmpty()) {
                int val = pq.poll();
                val--;
                if (val > 0) q.add(new Pair(val, time + n));
            }

            if (!q.isEmpty() && q.peek().getValue() == time) pq.add(
                q.poll().getKey()
            );
        }
        return time;
    }
}
