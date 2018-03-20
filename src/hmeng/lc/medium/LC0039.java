package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;

/**
39. Combination Sum

Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
 */
public class LC0039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        combinationSum(candidates, 0, new ArrayList<Integer>(), results, target);
        
        return results;
    }
    
    private void combinationSum(int[] candidates, int startIndex, List<Integer> tmpResult, List<List<Integer>> results, int remaining) {
        if (remaining < 0) return;
        else if (remaining == 0) results.add(new ArrayList<>(tmpResult));
        else {
            for (int i = startIndex; i < candidates.length; i++) {
                tmpResult.add(Integer.valueOf(candidates[i]));
                //still pass i in as numbers can be used repeatedly
                combinationSum(candidates, i, tmpResult, results, remaining - candidates[i]);
                tmpResult.remove(tmpResult.size()-1);
            }
        }
    }
}
