package neetcode150.problems.p271;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 271. Encode and Decode Strings  [Medium]  -  Arrays & Hashing   (LeetCode premium / LintCode 659)
 * https://leetcode.com/problems/encode-and-decode-strings/
 *
 * Problem:
 *   Design encode(list of strings) -> one string and decode(string) -> the original list. The
 *   strings may contain any characters.
 *
 * Example:
 *   Input:  ["lint", "code", "love", "you"]
 *   Encode: "4#lint4#code4#love3#you"   ->   decode gives the list back
 *
 * Solution - length prefix:
 *   Write each string as length + '#' + string. To decode, read digits up to '#', which gives
 *   the length, then take exactly that many characters - so '#' inside a string is harmless.
 *   Time: O(total length)   Space: O(total length)
 */
public class Solution {
    public static void main(String[] args) {
        Solution codec = new Solution();
        String encoded = codec.encode(List.of("lint", "code", "love", "you"));
        print(encoded);
        print(codec.decode(encoded)); // ["lint", "code", "love", "you"]
    }

    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for (String str : strs) {
            encodedString.append(str.length()).append("#").append(str);
        }
        return encodedString.toString();
    }

    public List<String> decode(String str) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') j++;

            int length = Integer.valueOf(str.substring(i, j));
            i = j + 1 + length;
            list.add(str.substring(j + 1, i));
        }
        return list;
    }
}
