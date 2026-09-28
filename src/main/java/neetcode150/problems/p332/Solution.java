package neetcode150.problems.p332;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 332. Reconstruct Itinerary  [Hard]  -  Advanced Graphs
 * https://leetcode.com/problems/reconstruct-itinerary/
 *
 * Problem:
 *   Use every ticket [from, to] exactly once, starting at "JFK". If several itineraries work,
 *   return the lexicographically smallest.
 *
 * Example:
 *   Input:  [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *   Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Solution - Hierholzer's algorithm (Eulerian path), iterative:
 *   For each airport keep its destinations in a min-heap (alphabetical order). Walk with a
 *   stack: while the top airport still has tickets, fly to its smallest destination (push it).
 *   When it has none left, pop it onto the FRONT of the itinerary.
 *   Time: O(E log E)   Space: O(E)
 */
class Solution {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>(List.of(
            List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO")));
        print(new Solution().findItinerary(tickets)); // ["JFK", "MUC", "LHR", "SFO", "SJC"]
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> itinerary = new LinkedList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        Stack<String> stack = new Stack<>();

        for (List<String> ticket : tickets) {
            graph
                .computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>())
                .add(ticket.get(1));
        }

        stack.push("JFK");
        while (!stack.isEmpty()) {
            String nextDestination = stack.peek();

            if (
                !graph
                    .getOrDefault(nextDestination, new PriorityQueue<>())
                    .isEmpty()
            ) {
                stack.push(graph.get(nextDestination).poll());
            } else {
                itinerary.addFirst(stack.pop());
            }
        }
        return itinerary;
    }
}
