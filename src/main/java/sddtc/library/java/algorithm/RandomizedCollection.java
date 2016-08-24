package sddtc.library.java.algorithm;

import java.util.*;

/**
 * no.381 https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * Author sddtc
 * Date 16/8/24
 */
public class RandomizedCollection {
    List<Integer> list;
    Map<Integer, Set<Integer>> valueWithLocations;
    Random random = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        list = new ArrayList<>();
        valueWithLocations = new HashMap<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!valueWithLocations.containsKey(val)) {
            valueWithLocations.put(val, new HashSet<Integer>());
        }

        valueWithLocations.get(val).add(list.size());
        list.add(val);
        return !valueWithLocations.containsKey(val);
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (!valueWithLocations.containsKey(val)) {
            return false;
        }

        int location = valueWithLocations.get(val).iterator().next();
        valueWithLocations.get(val).remove(location);
        if (location < list.size() - 1) {
            int lastValue = list.get(list.size() - 1);
            list.set(location, lastValue);
            valueWithLocations.get(lastValue).remove(list.size() - 1);
            valueWithLocations.get(lastValue).add(location);
        }

        list.remove(list.size() - 1);
        if (valueWithLocations.get(val).isEmpty()) {
            valueWithLocations.remove(val);
        }
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
