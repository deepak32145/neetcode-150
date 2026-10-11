package neetcode150.problems.p425;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 425. Word Squares  [Hard, Premium]
 * https://leetcode.com/problems/word-squares/
 *
 * Problem:
 *   Given unique words of equal length n, return all word squares: n words where the k-th row and
 *   the k-th column read the same string.
 *
 * Example:
 *   Input:  ["area","lead","wall","lady","ball"]
 *   Output: [["wall","area","lead","lady"],["ball","area","lead","lady"]]
 *
 * Solution - backtracking with a prefix index:
 *   If rows 0..k-1 are placed, row k must read the same as column k, so its first k letters are
 *   forced: prefix = rows[0][k] rows[1][k] ... rows[k-1][k]. Instead of trying every word we fetch
 *   only the words with that prefix from a precomputed map (prefix -> words). Recurse until n rows
 *   are placed.
 *   Time: exponential worst case, heavily pruned in practice   Space: O(words * n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.wordSquares(new String[] {"area", "lead", "wall", "lady", "ball"}));
        // [[ball, area, lead, lady], [wall, area, lead, lady]] (any order)
        print(s.wordSquares(new String[] {"abat", "baba", "atan", "atal"}));
        // [[baba, abat, baba, atal], [baba, abat, baba, atan]]
    }

    private int n;
    private Map<String, List<String>> byPrefix;
    private List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {
        n = words[0].length();
        byPrefix = new HashMap<>();
        result = new ArrayList<>();

        // Index every word under each of its prefixes (including the empty prefix).
        for (String w : words) {
            for (int len = 0; len <= n; len++) {
                byPrefix.computeIfAbsent(w.substring(0, len), k -> new ArrayList<>()).add(w);
            }
        }
        // Any word can be the first row (its prefix is the empty string).
        for (String w : words) {
            List<String> square = new ArrayList<>();
            square.add(w);
            backtrack(square);
        }
        return result;
    }

    private void backtrack(List<String> square) {
        int row = square.size();
        if (row == n) {
            result.add(new ArrayList<>(square));
            return;
        }
        // Row `row` must start with column `row` of the rows placed so far.
        StringBuilder prefix = new StringBuilder();
        for (String placed : square) prefix.append(placed.charAt(row));

        for (String candidate : byPrefix.getOrDefault(prefix.toString(), List.of())) {
            square.add(candidate);
            backtrack(square);
            square.remove(square.size() - 1);
        }
    }
}
