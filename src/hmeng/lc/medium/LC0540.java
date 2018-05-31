package hmeng.lc.medium;

/*
540. Single Element in a Sorted Array

Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element 
that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.
 */
public class LC0540 {

    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length % 2 == 0) return -1;
        int l = 0, r = nums.length-1;
        while (l < r) {
            int m = l + (r-l)/2;
            int mp = m % 2 == 0 ? m+1 : m-1;
            if (nums[m] == nums[mp]) {
                l = m+1;
            } else {
                r = m;
            }
        }
        
        return nums[l];
    }
    
    public static void main(String[] args) {
        LC0540 lc = new LC0540();
        int[] nums = {1,1,2,3,3,4,4,5,5};
        System.out.println(lc.singleNonDuplicate(nums));
    }

}
