package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hmeng.lc.common.UndirectedGraphNode;

/*
133. Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

public class LC0133 {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<Integer, UndirectedGraphNode> visited = new HashMap<>();
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        dfs(root, node, visited);
        return root;
    }
    
    private void dfs(UndirectedGraphNode root, UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> visited) {
        if (visited.containsKey(Integer.valueOf(node.label))) return;
        visited.put(Integer.valueOf(node.label), root);
        root.neighbors = new ArrayList<>(node.neighbors.size());
        for (UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode clonedNeighbor = visited.get(neighbor.label);
            if (clonedNeighbor == null) clonedNeighbor = new UndirectedGraphNode(neighbor.label);
            root.neighbors.add(clonedNeighbor);
            dfs(clonedNeighbor, neighbor, visited);
        }
    }
    
    public static void main(String[] args) {
        UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode(0);
        UndirectedGraphNode node2 = new UndirectedGraphNode(0);
        node0.neighbors.add(node1);
        node0.neighbors.add(node2);
        
        LC0133 lc = new LC0133();
        lc.cloneGraph(node0);
    }

}
