package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;
/*
77. Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

 */
public class LC0077 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        combine(results, new ArrayList<Integer>(), 1, n, k);
        
        return results;
    }
    
    private void combine(List<List<Integer>> results, List<Integer> tempResult, int start, int n, int k) {
        if (tempResult.size() == k) results.add(new ArrayList<>(tempResult));
        else {
            for (int i = start; i <= n; i++) {
                //dedupe
                if (tempResult.contains(Integer.valueOf(i))) continue;
                tempResult.add(Integer.valueOf(i));
                combine(results, tempResult, i+1, n, k);
                tempResult.remove(tempResult.size() - 1);
            }
        }
    }
}
