package neetcode150.problems.p527;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 527. Word Abbreviation  [Hard, Premium]
 * https://leetcode.com/problems/word-abbreviation/
 *
 * Problem:
 *   Give each of the distinct words the SHORTEST possible abbreviation of the form
 *   prefix + (count of omitted letters) + lastLetter such that all abbreviations are unique. If
 *   the abbreviation is not shorter than the word, keep the word. Start with a 1-letter prefix and
 *   lengthen prefixes only as needed.
 *
 * Example:
 *   Input:  ["like","god","internal","me","internet","interval","intension","face","intrusion"]
 *   Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 *
 * Solution - iteratively lengthen prefixes of colliding words:
 *   1. Abbreviate every word with prefix length 1.
 *   2. Group words by their current abbreviation. Any group of size > 1 collides: increase the
 *      prefix length of every word in that group by 1 and recompute.
 *   3. Repeat until no group has more than one word. Words are distinct, so with a long enough
 *      prefix the abbreviation becomes the word itself, guaranteeing termination.
 *   Only words that share length, last letter and prefix can collide, and they all grow together,
 *   so each word ends up with the minimal prefix that separates it from the others.
 *   Time: O(n * L) per round, at most L rounds   Space: O(n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.wordsAbbreviation(List.of("like", "god", "internal", "me", "internet", "interval", "intension",
                "face", "intrusion")));
        // [l2e, god, internal, me, i6t, interval, inte4n, f2e, intr4n]
        print(s.wordsAbbreviation(List.of("aa", "aaa"))); // [aa, aaa]
    }

    public List<String> wordsAbbreviation(List<String> words) {
        int n = words.size();
        int[] prefix = new int[n];
        Arrays.fill(prefix, 1);
        String[] abbr = new String[n];
        for (int i = 0; i < n; i++) abbr[i] = abbreviate(words.get(i), 1);

        boolean collided = true;
        while (collided) {
            collided = false;
            Map<String, List<Integer>> groups = new HashMap<>();
            for (int i = 0; i < n; i++) groups.computeIfAbsent(abbr[i], k -> new ArrayList<>()).add(i);

            for (List<Integer> group : groups.values()) {
                if (group.size() < 2) continue;
                collided = true;
                for (int idx : group) { // every word sharing this abbreviation needs a longer prefix
                    prefix[idx]++;
                    abbr[idx] = abbreviate(words.get(idx), prefix[idx]);
                }
            }
        }
        return Arrays.asList(abbr);
    }

    /** prefix chars + number of omitted letters + last char; the word itself if that is not shorter. */
    private String abbreviate(String word, int prefixLen) {
        int omitted = word.length() - prefixLen - 1;
        if (omitted <= 1) return word; // 0 or 1 omitted letters: no saving
        return word.substring(0, prefixLen) + omitted + word.charAt(word.length() - 1);
    }
}
