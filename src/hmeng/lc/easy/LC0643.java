package hmeng.lc.easy;

/*
643. Maximum Average Subarray I

Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
 */
public class LC0643 {

    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return 0.0;
        double[] presum = new double[nums.length];
        presum[0] = (double)nums[0];
        for (int i = 1; i < nums.length; i++) {
            presum[i] = presum[i-1] + (double)nums[i];
        }
        
        double res = presum[k-1]/k;
        for (int i = k; i < nums.length; i++) {
            res = Math.max(res, (presum[i]-presum[i-k])/k);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0643 lc = new LC0643();
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(lc.findMaxAverage(nums, k));
    }

}
