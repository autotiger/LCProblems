package hmeng.lc.easy;

import java.util.Arrays;

/*
581. Shortest Unsorted Continuous Subarray

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending 
order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
 */
public class LC0581 {

    /*
     * two pointers, time: O(n), space: O(1)
     */
    public int findUnsortedSubarray2(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
          max = Math.max(max, A[i]);
          min = Math.min(min, A[n-1-i]);
          if (A[i] < max) end = i;
          if (A[n-1-i] > min) beg = n-1-i; 
        }
        return end - beg + 1;
    }
    
    /*
     * sort, O(nlogn)
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int i = 0, j = nums.length-1;
        int left = -1, right = -1;
        while (i < j) {
            if (copy[i] == nums[i]) i++;
            else left = i;
            if (copy[j] == nums[j]) j--;
            else right = j;
            
            if (left >= 0 && right >= 0) return right - left + 1;
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        LC0581 lc = new LC0581();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(lc.findUnsortedSubarray(nums));
    }

}
