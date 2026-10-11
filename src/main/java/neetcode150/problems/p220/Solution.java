package neetcode150.problems.p220;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 220. Contains Duplicate III  [Hard]
 * https://leetcode.com/problems/contains-duplicate-iii/
 *
 * Problem:
 *   Is there a pair i != j with |i - j| <= indexDiff and |nums[i] - nums[j]| <= valueDiff?
 *
 * Example:
 *   Input:  nums = [1,2,3,1], indexDiff = 3, valueDiff = 0   Output: true
 *
 * Solution - bucket sort over a sliding window:
 *   Put each value in a bucket of width (valueDiff + 1). Two values in the SAME bucket always differ
 *   by <= valueDiff; only the two neighbouring buckets can hold other qualifying values, and there
 *   we check the actual difference. Keep only the last indexDiff elements in the map (evict the
 *   element leaving the window), so each bucket holds at most one value (a second would have
 *   already returned true).
 *   floorDiv keeps negative numbers in the right bucket.
 *   Time: O(n)   Space: O(indexDiff)
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        print(s.containsNearbyAlmostDuplicate(new int[] {1, 2, 3, 1}, 3, 0)); // true
        print(s.containsNearbyAlmostDuplicate(new int[] {1, 0, 1, 1}, 1, 2)); // true
        print(s.containsNearbyAlmostDuplicate(new int[] {1, 5, 9, 1, 5, 9}, 2, 3)); // false
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0 || indexDiff <= 0) return false;
        long width = (long) valueDiff + 1;
        Map<Long, Long> buckets = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            long id = Math.floorDiv(x, width);

            if (buckets.containsKey(id)) return true; // same bucket -> within valueDiff
            Long left = buckets.get(id - 1);
            if (left != null && x - left <= valueDiff) return true;
            Long right = buckets.get(id + 1);
            if (right != null && right - x <= valueDiff) return true;

            buckets.put(id, x);
            if (i >= indexDiff) { // slide the window: drop the element that is now too far away
                buckets.remove(Math.floorDiv((long) nums[i - indexDiff], width));
            }
        }
        return false;
    }
}
