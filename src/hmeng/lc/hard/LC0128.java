package hmeng.lc.hard;

import java.util.HashSet;
import java.util.Set;

/*
128. Longest Consecutive Sequence

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.


 */
public class LC0128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(Integer.valueOf(num));
        }
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(Integer.valueOf(down))) {
                set.remove(Integer.valueOf(down));
                down--;
            }
            int up = nums[i] + 1;
            while (set.contains(Integer.valueOf(up))) {
                set.remove(Integer.valueOf(up));
                up++;
            }
            
            res = Math.max(res, up-down-1);
        }
        
        return res;
    }
}
