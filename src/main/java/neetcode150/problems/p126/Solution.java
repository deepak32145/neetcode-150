package neetcode150.problems.p126;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 126. Word Ladder II  [Hard]
 * https://leetcode.com/problems/word-ladder-ii/
 *
 * Problem:
 *   Return ALL shortest transformation sequences from beginWord to endWord, changing one letter
 *   at a time, where every intermediate word must be in wordList.
 *
 * Example:
 *   Input:  beginWord = "hit", endWord = "cog", wordList = [hot,dot,dog,lot,log,cog]
 *   Output: [[hit,hot,dot,dog,cog],[hit,hot,lot,log,cog]]
 *
 * Solution - level-order BFS building a parents map, then DFS back from endWord:
 *   1. BFS one level at a time. For every word reached, remember which words on the previous
 *      level reached it (parents). Words used on a level are removed from the dictionary only
 *      after the whole level is processed, so several same-level parents are all recorded.
 *   2. Stop at the first level that reaches endWord (that is the shortest length).
 *   3. DFS from endWord through parents back to beginWord; every path found is a shortest path.
 *   Time: O(N * L * 26) for BFS plus output size   Space: O(N * L)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
        // [[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
        print(new Solution().findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log"))); // []
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        dict.remove(beginWord);

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> level = new HashSet<>();
        level.add(beginWord);
        boolean found = false;

        while (!level.isEmpty() && !found) {
            Set<String> next = new HashSet<>();
            for (String word : level) {
                char[] cs = word.toCharArray();
                for (int i = 0; i < cs.length; i++) {
                    char orig = cs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == orig) continue;
                        cs[i] = c;
                        String nb = new String(cs);
                        if (dict.contains(nb)) {
                            next.add(nb);
                            parents.computeIfAbsent(nb, k -> new ArrayList<>()).add(word);
                            if (nb.equals(endWord)) found = true;
                        }
                    }
                    cs[i] = orig;
                }
            }
            dict.removeAll(next); // a word reached on this level cannot lie on a shortest path later
            level = next;
        }

        if (found) {
            LinkedList<String> path = new LinkedList<>();
            path.add(endWord);
            build(endWord, beginWord, parents, path, res);
        }
        return res;
    }

    /** Walks parents from word back to beginWord, emitting each complete path (front-to-back order). */
    private void build(String word, String beginWord, Map<String, List<String>> parents,
                       LinkedList<String> path, List<List<String>> res) {
        if (word.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String p : parents.getOrDefault(word, List.of())) {
            path.addFirst(p);
            build(p, beginWord, parents, path, res);
            path.removeFirst(); // backtrack
        }
    }
}
