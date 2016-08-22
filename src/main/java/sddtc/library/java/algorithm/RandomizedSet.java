package sddtc.library.java.algorithm;

import java.util.*;

/**
 * no.380 https://leetcode.com/problems/insert-delete-getrandom-o1/
 * Author sddtc
 * Date 16/8/22
 */
public class RandomizedSet {
    List<Integer> list;
    Map<Integer, Integer> valueAndLocation;
    java.util.Random rand = new java.util.Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        list = new ArrayList<>();
        valueAndLocation = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valueAndLocation.containsKey(val)) {
            return false;
        }
        valueAndLocation.put(val, list.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!valueAndLocation.containsKey(val)) {
            return false;
        }
        int location = valueAndLocation.get(val);
        if (location < list.size() - 1) {
            int lastPositionValue = list.get(list.size() - 1);
            list.set(location, lastPositionValue);
            valueAndLocation.put(lastPositionValue, location);
        }
        valueAndLocation.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(valueAndLocation.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
