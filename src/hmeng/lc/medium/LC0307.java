package hmeng.lc.medium;

import hmeng.lc.common.BIT;

/*
307. Range Sum Query - Mutable

Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:

    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.

 */
public class LC0307 {
    private BIT bit;
    
    private int[] nums;
    public LC0307(int[] nums) {
        this.nums = nums;
        bit = new BIT(nums.length);
        for (int i = 0; i < nums.length; i++)
            bit.update(i+1, nums[i]);
    }
    
    public void update(int i, int val) {
        bit.update(i+1, val-nums[i]);
        nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        return bit.query(j+1) - bit.query(i);
    }
}
