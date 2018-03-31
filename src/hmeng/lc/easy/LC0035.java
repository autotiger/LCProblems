package hmeng.lc.easy;

/*
35. Search Insert Position

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 1:

Input: [1,3,5,6], 0
Output: 0
*/
public class LC0035 {

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int m = l + (r-l)/2;
            if (nums[m] == target) return m;
            else if (nums[m] < target) l = m+1;
            else r = m-1;
        }
        
        return l;
    }
    
    public static void main(String[] args) {
        LC0035 lc = new LC0035();
        int[] nums = {0, 2, 3, 4};
        System.out.println(lc.searchInsert(nums, 1));
    }
}
