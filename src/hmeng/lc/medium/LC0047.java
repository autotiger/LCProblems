package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;

/*
47. Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/
public class LC0047 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(nums, new ArrayList<>(), res, new boolean[nums.length]);
        
        return res;
    }
    
    private void helper(int[] nums, List<Integer> list, List<List<Integer>> res, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        //int pre = nums[0]-1;
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            //if (!used[i] && nums[i] != pre) {
                //pre = nums[i];
                used[i] = true;
                list.add(nums[i]);
                helper(nums, list, res, used);
                used[i] = false;
                list.remove(list.size()-1);
            //}
        }
    }
    
    public static void main(String[] args) {
        LC0047 lc = new LC0047();
        int[] nums = {1,1,2};
        System.out.println(lc.permuteUnique(nums));
    }

}
