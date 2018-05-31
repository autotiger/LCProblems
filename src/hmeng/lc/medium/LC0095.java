package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hmeng.lc.common.TreeNode;

/*
95. Unique Binary Search Trees II

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


 */
public class LC0095 {
       
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) return new ArrayList<>();
        Map<String, List<TreeNode>> memo = new HashMap<>();
        return generateTrees(1, n, memo);
    }
    
    private List<TreeNode> generateTrees(int lo, int hi, Map<String, List<TreeNode>> memo) {
        if (lo > hi) {
            List<TreeNode> res = new ArrayList<>();
            res.add(null);
            return res;
        }
        if (lo == hi) return Arrays.asList(new TreeNode(lo));
        String key = String.format("%d-%d", lo, hi);
        if (memo.containsKey(key)) return memo.get(key);        
        List<TreeNode> result = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            List<TreeNode> left = generateTrees(lo, i-1, memo);
            List<TreeNode> right = generateTrees(i+1, hi, memo);
            result.addAll(merge(i, left, right));
        }
        
        memo.put(key, result);
        return result;
    }
    
    private List<TreeNode> merge(int rootVal, List<TreeNode> left, List<TreeNode> right) {
        List<TreeNode> res = new ArrayList<>();
        for (TreeNode l : left) {
            for (TreeNode r : right) {
                TreeNode newRoot = new TreeNode(rootVal);
                newRoot.left = l;
                newRoot.right = r;
                res.add(newRoot);
            }
        }
        
        return res;
    }
    public static void main(String[] args) {
        LC0095 lc = new LC0095();
        List<TreeNode> res = lc.generateTrees(0);
        System.out.println(res);
    }

}
