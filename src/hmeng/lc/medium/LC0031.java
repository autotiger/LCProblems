package hmeng.lc.medium;

import java.util.Arrays;

/*
31. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 -> 1,3,2
3,2,1 -> 1,2,3
1,1,5 -> 1,5,1
*/

public class LC0031 {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int i = nums.length-2;
        while (i >= 0 && nums[i] >= nums[i+1]) i--;
        if (i < 0) {
            reverse(nums, 0);
            return;
        }
        
        int j = nums.length-1;
        while (j >= 0 && nums[j] <= nums[i]) j--;
        swap(nums, i, j);
        reverse(nums, i+1);
    }
    
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length-1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /*
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = nums.length-1; i >= 0; i--) {
            if (i - 1 >= 0) {
                if (nums[i] > nums[i-1]) {
                    int next = findNext(nums, i, nums[i-1]);
                    swap(nums, next, i-1);
                    Arrays.sort(nums, i, nums.length);
                    return;
                }
            } else {
                reverse(nums);
                return;
            }
        }
    }
    
    private int findNext(int[] nums, int start, int target) {
        int res = start;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > target && nums[i] < nums[res]) res = i;
        }
        
        return res;
    }
    
    private void reverse(int[] nums) {
        int i = 0, j = nums.length-1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    */
    public static void main(String[] args) {
        LC0031 lc = new LC0031();
        int[] nums = {1,5,1};
        lc.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

    }

}
