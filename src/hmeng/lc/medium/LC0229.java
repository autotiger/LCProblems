package hmeng.lc.medium;

import java.util.LinkedList;
import java.util.List;

/*
229. Majority Element II

Given an integer array of size n, find all elements that appear more than n/3 times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]

Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]


 */
public class LC0229 {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return new LinkedList<>();
        
        int can1 = nums[0], can2 = nums[0], count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == can1) count1++;
            else if (num == can2) count2++;
            else if (count1 == 0) {
                can1 = num;
                count1++;
            } else if (count2 == 0) {
                can2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == can1) count1++;
            else if (num == can2) count2++;
        }
        
        List<Integer> res = new LinkedList<>();
        double len = (double)nums.length;
        if ((double)count1 > len/3) res.add(can1);
        if ((double)count2 > len/3) res.add(can2);
        
        return res;
    }
}
