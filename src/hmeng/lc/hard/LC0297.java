package hmeng.lc.hard;

import java.util.LinkedList;
import java.util.Queue;

import hmeng.lc.common.TreeNode;

public class LC0297 {
    // Encodes a tree to a single string.
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
        System.out.println(lc.deserialize(lc.serialize(root)));
    }

}
