package hmeng.lc.medium;

/*
 215. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth
distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Note:
You may assume k is always valid, 1 <= k <= array's length.

 */
public class LC0215 {

    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length-1;
        int i = -1;
        while ((i = partition(nums, start, end)) != k-1) {
            if (i < k-1) {
                start = i+1;
            } else {
                end = i-1;
            }
        }
        
        return nums[i];
    }
    
    private int partition(int[] nums, int start, int end) {
        int i = start - 1, j = start;
        int p = nums[end];
        while (j < end) {
            if (nums[j] >= p) {
                i++;
                swap(nums, i, j);
            }
            j++;
        }
        swap(nums, i+1, j);
        return i+1;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) {
        LC0215 lc = new LC0215();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(lc.findKthLargest(nums, k));
    }

}
