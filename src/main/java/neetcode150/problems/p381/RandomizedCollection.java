package neetcode150.problems.p381;

import java.util.*;
import static neetcode150.common.Util.*;

/*
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed  [Hard]
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 *
 * Problem:
 *   Design RandomizedCollection (a multiset) with insert, remove (one occurrence) and getRandom
 *   (each stored element returned with probability proportional to its count), all average O(1).
 *
 * Example:
 *   insert(1) -> true, insert(1) -> false, insert(2) -> true, getRandom() -> 1 (2/3) or 2 (1/3),
 *   remove(1) -> true, getRandom() -> 1 or 2 with equal chance
 *
 * Solution - array for random access + value -> set of indices:
 *   `list` stores every element (duplicates included), so a uniform random index gives the right
 *   distribution. `positions` maps each value to the set of indices where it occurs.
 *   remove(val): take any index of val, overwrite it with the LAST element of the list and shrink
 *   the list (O(1) delete from an array), then fix the moved element's index set.
 *   insert returns true only if the value was not present before.
 */
class RandomizedCollection {
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        print(rc.insert(1)); // true
        print(rc.insert(1)); // false
        print(rc.insert(2)); // true
        print(rc.remove(1)); // true
        print(rc.remove(3)); // false
        print(rc.list.size()); // 2
    }

    private final List<Integer> list = new ArrayList<>();
    private final Map<Integer, Set<Integer>> positions = new HashMap<>();
    private final Random random = new Random();

    public RandomizedCollection() {}

    public boolean insert(int val) {
        Set<Integer> idxs = positions.computeIfAbsent(val, k -> new HashSet<>());
        idxs.add(list.size());
        list.add(val);
        return idxs.size() == 1; // first copy of this value
    }

    public boolean remove(int val) {
        Set<Integer> idxs = positions.get(val);
        if (idxs == null || idxs.isEmpty()) return false;

        int idx = idxs.iterator().next(); // any occurrence will do
        idxs.remove(idx);

        int lastIdx = list.size() - 1;
        int lastVal = list.get(lastIdx);
        if (idx != lastIdx) {
            // Move the last element into the hole and update its index set.
            list.set(idx, lastVal);
            Set<Integer> lastIdxs = positions.get(lastVal);
            lastIdxs.remove(lastIdx);
            lastIdxs.add(idx);
        }
        list.remove(lastIdx);
        if (idxs.isEmpty()) positions.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
