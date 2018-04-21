package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;

/*
78. Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public class LC0078 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        
        int n = (int)Math.pow(2, nums.length) - 1;
        for (int i = 0; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            int index = 0;
            int m = i;
            while (m != 0) {
                if (m % 2 == 1) {
                    list.add(nums[index]);
                }
                m >>= 1;
                index++;
            }
            res.add(list);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0078 lc = new LC0078();
        int[] nums = {1,2,3};
        System.out.println(lc.subsets(nums));
    }

}
