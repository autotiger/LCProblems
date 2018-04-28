package hmeng.lc.medium;

import hmeng.lc.common.TreeNode;

/*
337. House Robber III

The thief has found himself a new place for his thievery again. There is only one entrance to this area,
called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart
thief realized that "all houses in this place forms a binary tree". It will automatically contact the police
if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

     3
    / \
   2   3
    \   \ 
     3   1

Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

     3
    / \
   4   5
  / \   \ 
 1   3   1

Maximum amount of money the thief can rob = 4 + 5 = 9. 

 */
public class LC0337 {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        
        int[] res = robInternal(root);
        
        return res[0];
    }
    
    //0 -> child, 1 -> grandChild
    private int[] robInternal(TreeNode root) {
        if (root == null) return new int[] {0, 0};
        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);
        
        int pre2 = left[1] + right[1];
        int pre1 = left[0] + right[0];
        int curr = Math.max(root.val + pre2, pre1);
        return new int[] {curr, pre1};
    }
      
}
