package hmeng.lc.medium;

/*
494. Target Sum

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class LC0494 {

    /*
     * dp, O(n*L), L = sum of all nums
     */
    public int findTargetSumWays2(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum < S || (sum + S) % 2 != 0) return 0; 
        
        return subsetSum(nums, (sum + S)/2);
    }

    private int subsetSum(int[] nums, int sum) {
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                dp[i] += dp[i-num];
            }
        }
        
        return dp[sum];
    }
    
    /*
     * dfs, O(2^n)
     */
    private int ans = 0;
    
    private int target;
    
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        target = S;
        
        int[] memo = new int[nums.length];
        memo[nums.length-1] = nums[nums.length-1];
        for (int i = nums.length-2; i >= 0; i--) memo[i] = nums[i]+memo[i+1];
        
        dfs(nums, memo, 0, 0);
        return ans;
    }
    
    private void dfs(int[] nums, int[] memo, int start, int sum) {
        if (start == nums.length) {
            if (sum == target) ans++;
            return;
        }
        if (memo[start] < Math.abs(target - sum)) return;
        
        dfs(nums, memo, start+1, sum-nums[start]);
        dfs(nums, memo, start+1, sum+nums[start]);
    }
    
    /* time exeeded
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        int n = (int)Math.pow(2, nums.length) - 1;
        
        int count = 0;
        for (int i = 0; i <= n; i++) {
            int[] sign = new int[nums.length];
            int index = 0;
            int m = i;
            while (m > 0 ) {
                if (m % 2 == 1) {
                    sign[index] = -1;
                }
                m >>= 1;
                index++;
            }
            int sum = 0;
            for (int j = 0; j < sign.length; j++) {
                if (sign[j] == 0) sum += nums[j];
                else sum -= nums[j];
            }
            if (sum == S) count++;
        }
        
        return count;
    }*/
    
    public static void main(String[] args) {
        LC0494 lc = new LC0494();
        int[] nums = {2,20,24,38,44,21,45,48,30,48,14,9,21,10,46,46,12,48,12,38};
        int S = 48;
        long time = System.currentTimeMillis();
        System.out.println(lc.findTargetSumWays(nums, S));
        System.out.println(System.currentTimeMillis() - time);
    }

}
