package neetcode150.problems.p472;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 472. Concatenated Words  [Hard]
 * https://leetcode.com/problems/concatenated-words/
 *
 * Problem:
 *   Return all words in the list that are a concatenation of at least TWO shorter words from the
 *   same list.
 *
 * Example:
 *   Input:  ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *   Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Solution - word-break DP per word against a HashSet:
 *   For each word w, dp[i] = "prefix w[0..i) can be segmented into dictionary words". Try every
 *   previous cut j with dp[j] true and w[j..i) in the set. To force at least two parts, never
 *   allow the single segment that is the whole word (j == 0 && i == len).
 *   Time: O(N * L^2)   Space: O(N * L)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.findAllConcatenatedWordsInADict(new String[] {"cat", "cats", "catsdogcats", "dog", "dogcatsdog",
                "hippopotamuses", "rat", "ratcatdogcat"})); // [catsdogcats, dogcatsdog, ratcatdogcat]
        print(s.findAllConcatenatedWordsInADict(new String[] {"cat", "dog", "catdog"})); // [catdog]
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        List<String> res = new ArrayList<>();
        for (String w : words) {
            if (!w.isEmpty() && canForm(w, dict)) res.add(w);
        }
        return res;
    }

    private boolean canForm(String w, Set<String> dict) {
        int n = w.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // empty prefix is trivially segmentable
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i && !dp[i]; j++) {
                if (j == 0 && i == n) continue; // the word itself does not count as a part
                if (dp[j] && dict.contains(w.substring(j, i))) dp[i] = true;
            }
        }
        return dp[n];
    }
}
