package hmeng.lc.easy;

import java.util.ArrayList;
import java.util.List;

/*
448. Find All Numbers Disappeared in an Array

Given an array of integers where 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 */
//
// Keep swapping numbers to its sorted index (number - 1),
// numbers that appear more than once will be left in array with a wrong index
// loop again to find the missing numbers
public class LC0448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i+1 || nums[nums[i]-1] == nums[i]) {
                i++;
                continue;
            }
            swap(nums, i, nums[i]-1);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j+1) res.add(Integer.valueOf(j+1));
        }
        
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) {
        LC0448 lc = new LC0448();
        int[] nums = {4,3,2,7,8,2,3,1};
        lc.findDisappearedNumbers(nums);
    }
}
