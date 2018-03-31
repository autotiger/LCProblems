package hmeng.lc.medium;

import java.util.Arrays;

/*
300. Longest Increasing Subsequence

Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/

public class LC0300 {

    /*
     * nlog(n) solution
     */
    public int lengthOfLIS(int[] nums) {
        System.out.println(Arrays.toString(nums));
        if (nums == null || nums.length == 0) return 0;
        int[] tails = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = 0, r = size;
            while (l < r) {
                int m = l + (r - l)/2;
                if (tails[m] < nums[i]) l = m+1;
                else r = m;
            }
            
            tails[l] = nums[i];
            if (l == size) size++;
        }

        return size;
    }
    
    /*
     * n^1 solution
     */
//    public int lengthOfLIS(int[] nums) {
//        if (nums == null || nums.length == 0) return 0;
//        int[] dp = new int[nums.length];
//        int result = 0;
//        for (int i = 0; i < nums.length; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j]+1);
//                }
//            }
//            result = Math.max(result, dp[i]);
//        }
//        return result;
//    }
    
    public static void main(String[] args) {
        LC0300 lc = new LC0300();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lc.lengthOfLIS(nums));
    }

}
