package hmeng.lc.medium;

/*
162. Find Peak Element
DescriptionHintsSubmissionsDiscussSolution
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] != num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -inf.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
*/
public class LC0162 {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) return i;
        }
        
        return nums.length-1;
    }
}
