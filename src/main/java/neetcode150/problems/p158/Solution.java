package neetcode150.problems.p158;

import static neetcode150.common.Util.*;

/*
 * 158. Read N Characters Given read4 II - Call Multiple Times  [Hard, Premium]
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 *
 * Problem:
 *   Implement read(buf, n) on top of read4(buf4), which reads up to 4 chars from a file per call.
 *   read may be called many times, so leftover characters from an earlier call must not be lost.
 *
 * Example:
 *   file = "abcde"; read(buf, 1) -> 1 ("a"); read(buf, 2) -> 2 ("bc"); read(buf, 1) -> 1 ("d")
 *
 * Solution - keep a persistent internal 4-char buffer between calls:
 *   buf4 holds the chunk last returned by read4, bufCnt is how many valid chars it holds and bufPtr
 *   is the next unread index. read() drains buf4 first; only when it is empty does it call read4
 *   again. If read4 returns 0 the file is exhausted, so stop and return what was copied.
 *   Time: O(n) per call   Space: O(1)
 *
 * The Reader4 class below is a local stub standing in for LeetCode's provided API.
 * Delete it when pasting into LeetCode.
 */
class Reader4 {
    static String source = "";
    private int pos = 0;

    int read4(char[] buf4) {
        int count = 0;
        while (count < 4 && pos < source.length()) buf4[count++] = source.charAt(pos++);
        return count;
    }
}

class Solution extends Reader4 {
    public static void main(String[] args) {
        Reader4.source = "abcde";
        Solution s = new Solution();
        char[] buf = new char[10];
        int n = s.read(buf, 1);
        print(n + " " + new String(buf, 0, n)); // 1 a
        n = s.read(buf, 2);
        print(n + " " + new String(buf, 0, n)); // 2 bc
        n = s.read(buf, 10);
        print(n + " " + new String(buf, 0, n)); // 2 de  (file ends before 10)
        n = s.read(buf, 1);
        print(n); // 0
    }

    private final char[] buf4 = new char[4]; // chunk from the last read4 call
    private int bufPtr = 0;                  // next unread position in buf4
    private int bufCnt = 0;                  // number of valid chars in buf4

    public int read(char[] buf, int n) {
        int copied = 0;
        while (copied < n) {
            if (bufPtr == bufCnt) {
                // Internal buffer drained: fetch the next chunk of up to 4 chars.
                bufCnt = read4(buf4);
                bufPtr = 0;
                if (bufCnt == 0) break; // end of file
            }
            buf[copied++] = buf4[bufPtr++];
        }
        return copied;
    }
}
