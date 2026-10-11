package neetcode150.problems.p305;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 305. Number of Islands II  [Hard, Premium]
 * https://leetcode.com/problems/number-of-islands-ii/
 *
 * Problem:
 *   Start with an m x n grid of water. Each operation turns one cell into land. After each
 *   operation return the number of islands (4-directionally connected land).
 *
 * Example:
 *   Input:  m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]   Output: [1,1,2,3]
 *
 * Solution - union-find that grows as land appears:
 *   Flatten (r, c) to r*n + c. Adding land at a cell makes count++ (a new island), then for each
 *   land neighbor we union the two sets; every successful union (different roots) merges two
 *   islands, so count--. Duplicate positions are ignored (just repeat the current count).
 *   Path compression + union by size keep operations near O(1).
 *   Time: O(k * alpha(m*n))   Space: O(m*n)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.numIslands2(3, 3, new int[][] {{0, 0}, {0, 1}, {1, 2}, {2, 1}})); // [1, 1, 2, 3]
        print(s.numIslands2(1, 1, new int[][] {{0, 0}, {0, 0}})); // [1, 1]
    }

    private int[] parent;
    private int[] size;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        parent = new int[m * n];
        size = new int[m * n];
        Arrays.fill(parent, -1); // -1 means water
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        List<Integer> res = new ArrayList<>();
        int count = 0;
        for (int[] p : positions) {
            int id = p[0] * n + p[1];
            if (parent[id] != -1) { // already land: nothing changes
                res.add(count);
                continue;
            }
            parent[id] = id;
            size[id] = 1;
            count++; // starts as its own island

            for (int[] d : dirs) {
                int r = p[0] + d[0], c = p[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n) continue;
                int nid = r * n + c;
                if (parent[nid] == -1) continue; // water neighbor
                if (union(id, nid)) count--;     // joined two different islands into one
            }
            res.add(count);
        }
        return res;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // path halving
            x = parent[x];
        }
        return x;
    }

    /** Returns true if a and b were in different sets (and now are merged). */
    private boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (size[ra] < size[rb]) {
            int t = ra;
            ra = rb;
            rb = t;
        }
        parent[rb] = ra;
        size[ra] += size[rb];
        return true;
    }
}
