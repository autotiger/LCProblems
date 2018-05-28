package hmeng.lc.easy;

import java.util.Deque;
import java.util.LinkedList;

/*
665. Non-decreasing Array

Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].
 */
public class LC0665 {

    /*
     * one pass, O(n)
     */
    public boolean checkPossibility2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) {
                count++;
                if (i > 0 && nums[i-1] > nums[i+1]) nums[i+1] = nums[i];
                else nums[i] = nums[i+1];
            }
        }
        
        return count <= 1;
    }
    
    /*
     * monotonic stack, O(n)
     */
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        
        Deque<Integer> stk = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stk.isEmpty() && nums[i] < stk.peek()) {
                stk.pop();
                count++;
            }
            stk.push(nums[i]);
        }

        stk = new LinkedList<>();
        int count2 = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            while (!stk.isEmpty() && nums[i] > stk.peek()) {
                stk.pop();
                count2++;
            }
            stk.push(nums[i]);
        }
        
        return count <= 1 || count2 <= 1;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
