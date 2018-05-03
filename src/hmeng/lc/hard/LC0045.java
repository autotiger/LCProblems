package hmeng.lc.hard;

/*
45. Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class LC0045 {
    /*
    BFS, O(n)
    */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int curMax = 0, nextMax = 0, step = 0;
        for (int i = 0; i < nums.length-1; i++) {
            nextMax = Math.max(nextMax, nums[i] + i);
            if (curMax == i) {
                step++;
                curMax = nextMax;
            }
        }
        
        return step;
        
        
        /*
        BFS, time exceeded, O (n*m), m = sum of nums
        
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        
        int target = nums.length-1;
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(Integer.valueOf(0));
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int l = 0; l < size; l++) {
                int index = queue.poll().intValue();
                visited.add(Integer.valueOf(index));
                for (int i = 1; i <= nums[index]; i++) {
                    int newIndex = index + i;
                    if (newIndex == target) return step+1;
                    if (newIndex > target) break;
                    if (visited.contains(Integer.valueOf(newIndex))) continue;
                    queue.offer(Integer.valueOf(newIndex));
                    visited.add(Integer.valueOf(newIndex));
                }
            }
            step++;
        }
        
        return -1;
        */
        
        /*
        DP, time exceeded, O(n^2)
        
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
                        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] >= 0 && j+nums[j] >= i) {
                    if (dp[i] < 0) dp[i] = dp[j] + 1;
                    else dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }

        return dp[nums.length-1]; 
        */
    }
}
