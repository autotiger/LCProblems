package hmeng.lc.medium;

import java.util.Arrays;

/*
416. Partition Equal Subset Sum

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class LC0416 {

    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = sum; j >= 0; j--) {
                if (dp[j] != 0) dp[j+num] = 1;
                if (dp[sum/2] == 1) return true;
            }
        }
        
        return false;
    }
    /*
     calculate all combinations. time exceeds. O(2^n)
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        
        return dfs(nums, 0, 0, sum/2);
    }
    
    private boolean dfs(int[] nums, int start, int cursum, int target) {
        if (start >= nums.length) return false;
        if (cursum > target) return false;
        if (cursum == target) return true;
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            if (dfs(nums, start+1, cursum+nums[start], target))
                return true;
            swap(nums, start, i);
        }
        return false;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    //
    
    public static void main(String[] args) {
        LC0416 lc = new LC0416();
        int[] nums = {99,2,3,98};
        System.out.println(lc.canPartition2(nums));
    }

}
