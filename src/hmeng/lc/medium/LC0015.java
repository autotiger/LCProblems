package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
15. 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets 
in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]


 */
public class LC0015 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3)  return res;
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || nums[i] != nums[i-1]) {
                int t = -nums[i], l = i+1, r = nums.length-1;
                while (l < r) {
                    int sum = nums[l] + nums[r];
                    if (sum == t) {
                        res.add(Arrays.asList(-t, nums[l], nums[r]));
                        r--;
                        l++;
                        while (nums[r] == nums[r+1] && r > l) r--;
                        while (nums[l-1] == nums[l] && l < r) l++;
                    } else if (sum > t) {
                        r--;
                    } else {
                        l++;
                    }

                }
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0015 lc = new LC0015();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(lc.threeSum(nums));
    }

}
