package hmeng.lc.hard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
239. Sliding Window Maximum
DescriptionHintsSubmissionsDiscussSolution

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 <= k <= input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

 */
public class LC0239 {

    /*
     * monotonic queue, O(n)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return new int[]{};
        int[] res = new int[nums.length-k+1];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && queue.peekFirst() < i-k+1) queue.pollFirst();
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) queue.pollLast();
            queue.offerLast(i);
            if (i >= k-1) res[i-k+1] = nums[queue.peekFirst()];
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0239 lc = new LC0239();
        int[] nums = {1,3,1,2,0,5};
        int k = 3;
        //int[] nums = {1};
        //int k = 1;
        System.out.println(Arrays.toString(lc.maxSlidingWindow(nums, k)));
    }

}
