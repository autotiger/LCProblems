package hmeng.lc.hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

/*
480. Sliding Window Median

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */
public class LC0480 {

    private TreeMap<Integer, Integer> upper = new TreeMap<>();
    private TreeMap<Integer, Integer> lower = new TreeMap<>(Collections.reverseOrder());
    private int uppercount = 0;
    private int lowercount = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);
            if (uppercount+lowercount > k) {
                remove(nums[i-k]);
            }            
            if (uppercount+lowercount == k) {
                res[index++] = getMedian();
            }
        }
        
        return res;
    }
    
    private void addNum(int num) {
        upper.put(num, upper.getOrDefault(num, 0)+1);
        lower.put(upper.firstKey(), lower.getOrDefault(upper.firstKey(), 0) + 1);
        lowercount++;
        if (upper.get(upper.firstKey()) == 1) upper.remove(upper.firstKey());
        else upper.put(upper.firstKey(), upper.get(upper.firstKey()) - 1);
        if (uppercount < lowercount) {
            upper.put(lower.firstKey(), upper.getOrDefault(lower.firstKey(), 0) + 1);
            uppercount++;
            if (lower.get(lower.firstKey()) == 1) lower.remove(lower.firstKey());
            else lower.put(lower.firstKey(), lower.get(lower.firstKey())-1);
            lowercount--;
        }
    }
    
    private void remove(int num) {
        if (upper.containsKey(num)) {
            uppercount--;
            if (upper.get(num) == 1) upper.remove(num);
            else upper.put(num, upper.get(num)-1);
            if (uppercount < lowercount) {
                upper.put(lower.firstKey(), upper.getOrDefault(lower.firstKey(), 0) + 1);
                uppercount++;
                if (lower.get(lower.firstKey()) == 1) lower.remove(lower.firstKey());
                else lower.put(lower.firstKey(), lower.get(lower.firstKey())-1);
                lowercount--;
            }        
        } else {
            lowercount--;
            if(lower.get(num) == 1) lower.remove(num);
            else lower.put(num, lower.get(num)-1);
            if (uppercount - lowercount == 2) {
                lower.put(upper.firstKey(), lower.getOrDefault(upper.firstKey(), 0) + 1);
                lowercount++;
                if (upper.get(upper.firstKey()) == 1) upper.remove(upper.firstKey());
                else upper.put(upper.firstKey(), upper.get(upper.firstKey())-1);
                uppercount--;
            }
        }
    }
    
    private double getMedian() {
        if (uppercount == lowercount) {
            return (double)upper.firstKey()/2 + (double)lower.firstKey()/2;
        }
        return upper.firstKey();
    }
    
    public static void main(String[] args) {
        LC0480 lc = new LC0480();
        int[] nums = {5,2,2,7,3,7,9,0,2,3};
        int k = 9;
        System.out.println(Arrays.toString(lc.medianSlidingWindow(nums, k)));
    }

}
