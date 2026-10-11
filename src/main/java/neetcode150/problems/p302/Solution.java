package neetcode150.problems.p302;

import static neetcode150.common.Util.*;

/*
 * 302. Smallest Rectangle Enclosing Black Pixels  [Hard, Premium]
 * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
 *
 * Problem:
 *   A binary image has exactly one connected region of black pixels ('1'). Given one black pixel
 *   (x, y), return the area of the smallest axis-aligned rectangle enclosing all black pixels,
 *   in better than O(m*n).
 *
 * Example:
 *   Input:  image = ["0010","0110","0100"], x = 0, y = 2   Output: 6
 *
 * Solution - binary search for the four borders:
 *   Because the black region is connected and contains (x, y), the rows containing a black pixel
 *   form one contiguous interval around x (same for columns around y). That makes "does row r have
 *   a black pixel?" monotone on each side of x, so each border is a binary search:
 *     top    = first row in [0, x]   that has black
 *     bottom = first row in [x+1, m] that has no black   (exclusive bound)
 *     left / right likewise over columns.
 *   Each check scans one row/column. Time: O(m log n + n log m)   Space: O(1)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] image = board("0010", "0110", "0100");
        print(s.minArea(image, 0, 2)); // 6
        print(s.minArea(board("1"), 0, 0)); // 1
    }

    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int top = searchRows(image, 0, x, true);
        int bottom = searchRows(image, x + 1, m, false);
        int left = searchCols(image, 0, y, true);
        int right = searchCols(image, y + 1, n, false);
        return (bottom - top) * (right - left);
    }

    /**
     * Binary search over rows in [lo, hi). If findFirstBlack, returns the first row with a black
     * pixel; otherwise returns the first row without one (hi if every row has black).
     */
    private int searchRows(char[][] image, int lo, int hi, boolean findFirstBlack) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean hasBlack = false;
            for (char c : image[mid]) {
                if (c == '1') {
                    hasBlack = true;
                    break;
                }
            }
            if (hasBlack == findFirstBlack) hi = mid; else lo = mid + 1;
        }
        return lo;
    }

    private int searchCols(char[][] image, int lo, int hi, boolean findFirstBlack) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean hasBlack = false;
            for (char[] row : image) {
                if (row[mid] == '1') {
                    hasBlack = true;
                    break;
                }
            }
            if (hasBlack == findFirstBlack) hi = mid; else lo = mid + 1;
        }
        return lo;
    }
}
