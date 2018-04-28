package hmeng.lc.medium;

/*
213. House Robber II

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that
he will not get too much attention. This time, all houses at this place are arranged in a circle. That means
the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the
same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum
amount of money you can rob tonight without alerting the police.

 */
public class LC0213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int len = nums.length;
        return Math.max(rob1(nums, 0, len-2), rob1(nums, 1, len-1));
    }
    
    private int rob1(int[] nums, int start, int end) {
        int pre2 = 0, pre1 = 0;
        for (int i = start; i <= end; i++) {
            int curr = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = curr;
        }
        
        return pre1;
    }
    
}
