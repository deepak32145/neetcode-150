package neetcode150.problems.p763;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 763. Partition Labels  [Medium]  -  Greedy
 * https://leetcode.com/problems/partition-labels/
 *
 * Problem:
 *   Split s into as many parts as possible so each letter appears in at most one part. Return
 *   the sizes of the parts.
 *
 * Example:
 *   Input:  s = "ababcbacadefegdehijhklij"
 *   Output: [9, 7, 8]
 *
 * Solution - last occurrence + greedy:
 *   Record the last index of each letter. Walk the string extending the current part's end to
 *   the last occurrence of every letter seen; when i passes that end, close the part.
 *   Time: O(n)   Space: O(26)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().partitionLabels("ababcbacadefegdehijhklij")); // [9, 7, 8]
        print(new Solution().partitionLabels("eccbbbbdec")); // [10]
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int j = 0;
        int i = 0;
        while (i < s.length()) {
            int count = 0;
            j = Math.max(j, map.get(s.charAt(i)));
            while (i <= j) {
                j = Math.max(j, map.get(s.charAt(i)));
                i++;
                count++;
            }
            list.add(count);
        }
        return list;
    }
}
