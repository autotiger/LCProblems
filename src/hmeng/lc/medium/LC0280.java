package hmeng.lc.medium;

import java.util.Arrays;

/*
280. Wiggle Sort

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 */
public class LC0280 {
   
    /*
     * Sort, O(nlogn)
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        Arrays.sort(nums);
        for (int i = 1; i+1 < nums.length; i += 2) {
            swap(nums, i, i+1);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) {
        LC0280 lc = new LC0280();
        int[] nums = {3,5,2,1,6,4};
        lc.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
