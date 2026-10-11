package neetcode150.problems.p68;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 68. Text Justification  [Hard]
 * https://leetcode.com/problems/text-justification/
 *
 * Problem:
 *   Pack words into lines of exactly maxWidth. Fully justify every line except the last
 *   (and lines with a single word), which are left-justified.
 *
 * Example:
 *   words = ["This","is","an","example","of","text","justification."], maxWidth = 16
 *
 * Solution - greedy pack then distribute spaces:
 *   Take as many words as fit. If last line or one word, left pad with spaces. Else split
 *   extra spaces as evenly as possible, leftover going to the leftmost gaps.
 *   Time: O(n)   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        print(new Solution().fullJustify(
            new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int j = i, len = 0;
            while (j < words.length && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            boolean last = j == words.length;
            StringBuilder sb = new StringBuilder();
            if (last || gaps == 0) {
                for (int k = i; k < j; k++) {
                    if (k > i) sb.append(' ');
                    sb.append(words[k]);
                }
                while (sb.length() < maxWidth) sb.append(' ');
            } else {
                int spaces = maxWidth - len;
                int even = spaces / gaps, extra = spaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        int put = even + (k - i < extra ? 1 : 0);
                        sb.append(" ".repeat(put));
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }
}
