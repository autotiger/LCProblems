package hmeng.lc.easy;

/*
198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 money you can rob tonight without alerting the police.

 */
public class LC0198 {

    public int rob(int[] nums) {
        int pre1 = 0, pre2 = 0;
        for (int num : nums) {
            int current = Math.max(pre2+num, pre1);
            pre2 = pre1;
            pre1 = current;
        }
        
        return pre1;
    }
}
