package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;

/*
46. Permutations

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/
public class LC0046 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(nums, 0, res);
        
        return res;
    }
    
    private void helper(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            res.add(list);
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            helper(nums, start+1, res);
            swap(nums, start, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) {
        LC0046 lc = new LC0046();
        int[] nums = {1,2,3};
        System.out.println(lc.permute(nums));
    }

}
