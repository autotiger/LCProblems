package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import hmeng.lc.common.BIT;

/*
315. Count of Smaller Numbers After Self

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] 
is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Return the array [2, 1, 1, 0]. 
 */
public class LC0315 {

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        int index = 0;
        for (int i = 0; i < copy.length; i++) {
            if (valueToIndex.containsKey(copy[i])) continue;
            valueToIndex.put(copy[i], index++);
        }
        
        List<Integer> result = new LinkedList<>();
        BIT bit = new BIT(valueToIndex.size());
        for (int i = nums.length-1; i >= 0; i--) {
            int idx = valueToIndex.get(nums[i]);
            bit.update(idx+1, 1);
            result.add(0, bit.query(idx));
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
