package hmeng.lc.hard;

import java.util.LinkedList;
import java.util.Queue;

import hmeng.lc.common.TreeNode;
/*
297. Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

public class LC0297 {
    // Encodes a tree to a single string.
    // used level by level traversal to serialize tree
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) sb.append("null");
                else {
                    sb.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                sb.append(",");
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] vals = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int leftIndex = index * 2 - 1;
                if (leftIndex >= vals.length) break;
                if (!vals[leftIndex].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(vals[leftIndex]));
                    queue.offer(node.left);
                }
                int rightIndex = index * 2;
                if (rightIndex >= vals.length) break;
                if (!vals[rightIndex].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(vals[rightIndex]));
                    queue.offer(node.right);
                }
                index++;
            }
        }
        
        return root;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LC0297 lc = new LC0297();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node3;
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node3.left = node4;
        node3.right = node5;
        String s = lc.serialize(root);
        System.out.println(s);
        lc.deserialize(s);
    }

}
