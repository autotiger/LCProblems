package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
347. Top K Frequent Elements
DescriptionHintsSubmissionsDiscussSolution

Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:

    You may assume k is always valid, 1 <= k <= number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
public class LC0347 {

    /*
     * bucket sort, O(n)
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            Integer value = freq.get(nums[i]);
            if (value == null) value = 0;
            freq.put(nums[i], value+1);
            maxFreq = Math.max(maxFreq, value+1);
        }
        
        final Map<Integer, List<Integer>> reverseFreq = new HashMap<>();
        final int[] bucket = new int[maxFreq+1];
        freq.entrySet().forEach(entry -> {
            //reverseFreq.computeIfAbsent(entry.getValue(), key -> new ArrayList<>()).add(entry.getKey());
            List<Integer> l = reverseFreq.get(entry.getValue());
            if (l == null) l = new ArrayList<Integer>();
            l.add(entry.getKey());
            reverseFreq.put(entry.getValue(), l);
            bucket[entry.getValue().intValue()] = 1;
        });
        
        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length-1; i >= 0 && k > 0; i--) {
            if (bucket[i] == 0) continue;
            List<Integer> l = reverseFreq.get(i);
            result.addAll(l);
            k -= l.size();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        LC0347 lc = new LC0347();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(lc.topKFrequent(nums, k));
    }

}
