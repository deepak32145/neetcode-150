package neetcode150.problems.p30;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 30. Substring with Concatenation of All Words  [Hard]
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * Problem:
 *   Find every starting index in s of a substring that is a concatenation of every word in
 *   words exactly once (words are the same length, may contain duplicates).
 *
 * Example:
 *   Input:  s = "barfoothefoobarman", words = ["foo","bar"]
 *   Output: [0, 9]
 *
 * Solution - word-length sliding windows:
 *   For each offset 0..wordLen-1, slide a window of whole words. Track counts; shrink from
 *   the left when a word is overused; record left when the window holds exactly all words.
 *   Time: O(n * wordLen)   Space: O(number of unique words)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().findSubstring("barfoothefoobarman", new String[] {"foo", "bar"})); // [0, 9]
        print(new Solution().findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"})); // []
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty() || words.length == 0) return res;
        int w = words[0].length(), needCount = words.length;
        Map<String, Integer> need = new HashMap<>();
        for (String word : words) need.merge(word, 1, Integer::sum);
        for (int offset = 0; offset < w; offset++) {
            int left = offset, used = 0;
            Map<String, Integer> have = new HashMap<>();
            for (int right = offset; right + w <= s.length(); right += w) {
                String word = s.substring(right, right + w);
                if (!need.containsKey(word)) {
                    have.clear();
                    used = 0;
                    left = right + w;
                    continue;
                }
                have.merge(word, 1, Integer::sum);
                used++;
                while (have.get(word) > need.get(word)) {
                    String drop = s.substring(left, left + w);
                    have.put(drop, have.get(drop) - 1);
                    left += w;
                    used--;
                }
                if (used == needCount) res.add(left);
            }
        }
        return res;
    }
}
