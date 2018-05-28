package hmeng.lc.medium;

import java.util.LinkedList;
import java.util.Queue;

import hmeng.lc.common.TreeNode;

/*
662. Maximum Width of Binary Tree

Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the 
same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the 
end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer. 
 */
public class LC0662 {
    private static class Pair {
        int id;
        TreeNode node;
        Pair(int id, TreeNode node) {
            this.id = id;
            this.node = node;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, root));
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                if (i == 0) min = p.id;
                if (i == size-1) max = p.id;
                
                if (p.node.left != null) queue.offer(new Pair(p.id*2, p.node.left));
                if (p.node.right != null) queue.offer(new Pair(p.id*2+1, p.node.right));
            }
            
            res = Math.max(res, max-min+1);
        }
        
        return res;
    }
}
