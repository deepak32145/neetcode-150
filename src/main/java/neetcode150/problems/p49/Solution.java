package neetcode150.problems.p49;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 49. Group Anagrams  [Medium]  -  Arrays & Hashing
 * https://leetcode.com/problems/group-anagrams/
 *
 * Problem:
 *   Group the strings that are anagrams of each other. Any order is accepted.
 *
 * Example:
 *   Input:  ["eat", "tea", "tan", "ate", "nat", "bat"]
 *   Output: [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
 *
 * Solution - letter-count key:
 *   For each word count its 26 letters and build a key like "#1#0#0...#1". Anagrams share the
 *   same key, so group words in a map key -> list.
 *   Time: O(n * k), k = max word length   Space: O(n * k)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"})); // [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]] (any order)
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];

            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(s);
        }

        return new ArrayList<>(res.values());
    }
}
