package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import hmeng.lc.common.TreeNode;

/*
314. Binary Tree Vertical Order Traversal

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */
public class LC0314 {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, root));
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            min = Math.min(min, p.level);
            max = Math.max(max, p.level);
            if (p.node.left != null) queue.offer(new Pair(p.level-1, p.node.left));
            if (p.node.right != null) queue.offer(new Pair(p.level+1, p.node.right));
            map.computeIfAbsent(p.level, k -> new ArrayList<>()).add(p.node.val);
        }
        
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        
        return res;
    }
    
    private static class Pair {
        int level;
        TreeNode node;
        Pair(int l, TreeNode n) {
            level = l;
            node = n;
        }
    }
    
}
