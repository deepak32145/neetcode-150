package neetcode150.problems.p140;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 140. Word Break II  [Hard]
 * https://leetcode.com/problems/word-break-ii/
 *
 * Problem:
 *   Return every way to break s into a sequence of dictionary words.
 *
 * Example:
 *   Input:  s = "catsanddog", wordDict = [cat,cats,and,sand,dog]
 *   Output: ["cats and dog", "cat sand dog"]
 *
 * Solution - DFS with memoization on the start index:
 *   sentences(start) = all ways to break s[start..]. For each dictionary word that matches at
 *   `start`, prepend it to every sentence of the remainder. Cache per start so the same suffix is
 *   never re-expanded (this also prunes dead suffixes cheaply: they cache as an empty list).
 *   Time: O(n * 2^n) worst case output-bound   Space: O(n * 2^n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
        // [cat sand dog, cats and dog]
        print(new Solution().wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"))); // []
    }

    private Map<Integer, List<String>> memo;
    private Set<String> words;
    private String str;

    public List<String> wordBreak(String s, List<String> wordDict) {
        memo = new HashMap<>();
        words = new HashSet<>(wordDict);
        str = s;
        return dfs(0);
    }

    private List<String> dfs(int start) {
        if (memo.containsKey(start)) return memo.get(start);
        List<String> res = new ArrayList<>();
        if (start == str.length()) {
            res.add(""); // one way to break the empty suffix: no words
            return res;
        }
        for (int end = start + 1; end <= str.length(); end++) {
            String word = str.substring(start, end);
            if (!words.contains(word)) continue;
            for (String rest : dfs(end)) {
                res.add(rest.isEmpty() ? word : word + " " + rest);
            }
        }
        memo.put(start, res);
        return res;
    }
}
