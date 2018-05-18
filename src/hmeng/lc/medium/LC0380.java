package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
380. Insert Delete GetRandom O(1)

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/
public class LC0380 {

    private List<Integer> nums = new ArrayList<>();
    private Map<Integer, Integer> indexMap = new HashMap<>();
    
    int size = 0;
    /** Initialize your data structure here. */
    public LC0380() {
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indexMap.containsKey(val)) return false;
        nums.add(size, val);
        size++;
        indexMap.put(val, size-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) return false;
        int i = indexMap.get(val);
        if (i != size-1) {
            nums.set(i, nums.get(size-1));
            indexMap.put(nums.get(size-1), i);
        }
        size--;
        indexMap.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (size == 0) throw new IllegalArgumentException();
        Random random = new Random();
        
        return nums.get(random.nextInt(size));
    }
    
    public static void main(String[] args) {
        LC0380 lc = new LC0380();
        lc.insert(0);
        lc.insert(1);
        lc.remove(0);
        lc.insert(2);
        lc.remove(1);
        System.out.println(lc.getRandom());
    }

}
