package neetcode150.problems.p139;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 139. Word Break  [Medium]  -  1-D Dynamic Programming
 * https://leetcode.com/problems/word-break/
 *
 * Problem:
 *   Can s be split into a sequence of words from wordDict (words may be reused)?
 *
 * Example:
 *   Input:  s = "leetcode", wordDict = ["leet", "code"]
 *   Output: true
 *
 * Solution - DP from the end:
 *   dp[i] = can s[i..] be segmented. dp[n] = true. For each i (right to left) and each word w,
 *   if s starts with w at i then dp[i] = dp[i + len(w)]. Answer: dp[0].
 *   Time: O(n * w * L), w = words, L = word length   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().wordBreak("leetcode", List.of("leet", "code"))); // true
        print(new Solution().wordBreak("applepenapple", List.of("apple", "pen"))); // true
        print(new Solution().wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"))); // false
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for(int i = s.length()-1; i >= 0; i--){
            for(String w: wordDict){
                if((i + w.length()) <= s.length() && s.startsWith(w, i)){
                    dp[i] = dp[i + w.length()];
                }
                if(dp[i]){
                    break;
                }
            }
        }
        return dp[0];
    }
}
