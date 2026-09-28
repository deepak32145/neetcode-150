package neetcode150.problems.p2013;

import java.util.*;
import static neetcode150.common.Util.*;

// https://leetcode.com/submissions/detail/761120641/

/*
 * 2013. Detect Squares  [Medium]  -  Math & Geometry
 * https://leetcode.com/problems/detect-squares/
 *
 * Problem:
 *   Add points (duplicates allowed). count(point) returns how many ways to pick three stored
 *   points that form an axis-aligned square of positive area with the query point.
 *
 * Example:
 *   add([3, 10]); add([11, 2]); add([3, 2]); count([11, 10]) -> 1; count([14, 8]) -> 0;
 *   add([11, 2]); count([11, 10]) -> 2
 *
 * Solution - count grid + walk the four diagonals:
 *   matrix[x][y] = how many times (x, y) was added (coordinates 0..1000). For a query, walk
 *   each of the four diagonal directions; for each diagonal corner that exists, if the other
 *   two corners exist too, add the product of the three counts.
 *   Time: O(1000) per count, O(1) per add   Space: O(1001 * 1001)
 */
class DetectSquares {
    public static void main(String[] args) {
        DetectSquares squares = new DetectSquares();
        squares.add(new int[] {3, 10});
        squares.add(new int[] {11, 2});
        squares.add(new int[] {3, 2});
        print(squares.count(new int[] {11, 10})); // 1
        print(squares.count(new int[] {14, 8})); // 0
        squares.add(new int[] {11, 2}); // duplicate points are allowed
        print(squares.count(new int[] {11, 10})); // 2
    }

    private Integer[][] matrix;

    public DetectSquares() {
        matrix = new Integer[1001][1001];
    }

    public void add(int[] point) {
        if (matrix[point[0]][point[1]] == null) {
            matrix[point[0]][point[1]] = 1;
        } else {
            matrix[point[0]][point[1]] = matrix[point[0]][point[1]] + 1;
        }
    }

    public int count(int[] point) {
        int currentSquareCount = 0;
        int currentPointCount = 1;
        int startRow = point[0];
        int startCol = point[1];
        int curRow = point[0];
        int curCol = point[1];

        while (curRow != 0 && curCol != 0) {
            curRow--;
            curCol--;
            if (
                matrix[curRow][curCol] != null &&
                matrix[startRow][curCol] != null &&
                matrix[curRow][startCol] != null
            ) {
                currentSquareCount =
                    currentSquareCount +
                    (
                        currentPointCount *
                        matrix[curRow][curCol] *
                        matrix[startRow][curCol] *
                        matrix[curRow][startCol]
                    );
            }
        }

        curRow = point[0];
        curCol = point[1];
        while (curRow != 1000 && curCol != 1000) {
            curRow++;
            curCol++;
            if (
                matrix[curRow][curCol] != null &&
                matrix[startRow][curCol] != null &&
                matrix[curRow][startCol] != null
            ) {
                currentSquareCount =
                    currentSquareCount +
                    (
                        currentPointCount *
                        matrix[curRow][curCol] *
                        matrix[startRow][curCol] *
                        matrix[curRow][startCol]
                    );
            }
        }

        curRow = point[0];
        curCol = point[1];
        while (curRow != 0 && curCol != 1000) {
            curRow--;
            curCol++;
            if (
                matrix[curRow][curCol] != null &&
                matrix[startRow][curCol] != null &&
                matrix[curRow][startCol] != null
            ) {
                currentSquareCount =
                    currentSquareCount +
                    (
                        currentPointCount *
                        matrix[curRow][curCol] *
                        matrix[startRow][curCol] *
                        matrix[curRow][startCol]
                    );
            }
        }

        curRow = point[0];
        curCol = point[1];
        while (curRow != 1000 && curCol != 0) {
            curRow++;
            curCol--;
            if (
                matrix[curRow][curCol] != null &&
                matrix[startRow][curCol] != null &&
                matrix[curRow][startCol] != null
            ) {
                currentSquareCount =
                    currentSquareCount +
                    (
                        currentPointCount *
                        matrix[curRow][curCol] *
                        matrix[startRow][curCol] *
                        matrix[curRow][startCol]
                    );
            }
        }

        return currentSquareCount;
    }
}
