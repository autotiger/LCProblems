package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;

/*
163. Missing Ranges

Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"]. */
public class LC0163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for(int i : nums) {
            if(i > lower) res.add(lower+((i-1 > lower)?"->"+(i-1):""));
            if(i == upper) return res; // Avoid overflow
            lower = i+1;
        }
        if(lower <= upper) res.add(lower + ((upper > lower)?"->"+(upper):""));
        return res;
    }
    /*
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            String s = generateRangeString(lower, upper);
            res.add(s);
            return res;
        }

        for (int i = 0; i < nums.length-1; i++) {
            if ((long)nums[i+1] > (long)nums[i] + 1) {
                res.add(generateRangeString(nums[i]+1, nums[i+1]-1));
            }
        }
        if (nums[0] > lower) res.add(0, generateRangeString(lower, nums[0]-1));
        if (nums[nums.length-1] < upper) res.add(generateRangeString(nums[nums.length-1]+1, upper));
        
        return res;
    }
    
    private String generateRangeString(int start, int end) {
        if (start == end) return String.valueOf(start);
        return String.format("%s->%s", start, end);
    }*/
    
    public static void main(String[] args) {
        LC0163 lc = new LC0163();
        int[] nums = {-2147483648,-2147483648,0,2147483647,2147483647};
        int lower = -2147483648;
        int upper = 2147483647;
        System.out.println(lc.findMissingRanges(nums, lower, upper));
    }

}
