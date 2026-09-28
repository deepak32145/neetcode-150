package neetcode150.problems.p127;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 127. Word Ladder  [Hard]  -  Graphs
 * https://leetcode.com/problems/word-ladder/
 *
 * Problem:
 *   Transform beginWord into endWord by changing one letter at a time; every intermediate word
 *   must be in wordList. Return the number of words in the shortest sequence, or 0.
 *
 * Example:
 *   Input:  beginWord = "hit", endWord = "cog",
 *           wordList = ["hot", "dot", "dog", "lot", "log", "cog"]
 *   Output: 5                 (hit -> hot -> dot -> dog -> cog)
 *
 * Solution - wildcard buckets + BFS:
 *   Bucket every word under each of its patterns ("hot" -> "*ot", "h*t", "ho*"). Words in the
 *   same bucket are one letter apart. BFS from beginWord level by level through the buckets;
 *   the level at which endWord appears is the answer.
 *   Time: O(n * L^2), L = word length   Space: O(n * L^2)
 */
class Solution {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));
        print(new Solution().ladderLength("hit", "cog", words)); // 5
        List<String> noCog = new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log"));
        print(new Solution().ladderLength("hit", "cog", noCog)); // 0
    }

    public int ladderLength(
        String beginWord,
        String endWord,
        List<String> wordList
    ) {
        Map<String, List<String>> adjlist = new HashMap<>();
        wordList.add(beginWord);
        for (String word : wordList) {
            StringBuilder string = null;
            for (int i = 0; i < word.length(); i++) {
                string = new StringBuilder(word);
                string.setCharAt(i, '*');
                List<String> wordlist = adjlist.getOrDefault(
                    string.toString(),
                    new ArrayList<String>()
                );
                wordlist.add(word);
                adjlist.put(string.toString(), wordlist);
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int step = 1;
        StringBuilder string = null;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String str = queue.poll();

                for (int i = 0; i < str.length(); i++) {
                    string = new StringBuilder(str);
                    string.setCharAt(i, '*');
                    for (String pat : adjlist.get(string.toString())) {
                        if (pat.equals(endWord)) {
                            return step;
                        }
                        if (visited.contains(pat)) {
                            continue;
                        }
                        queue.offer(pat);
                        visited.add(pat);
                    }
                }
            }
            // step++;
        }
        return 0;
    }
}
