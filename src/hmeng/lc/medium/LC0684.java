package hmeng.lc.medium;

import java.util.Arrays;

/*11
684. Redundant Connection

In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
    
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
*/    
public class LC0684 {
    
    /*
     * Union-Find O(nlog*n);
     */
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) return new int[] {};
        
        int[] uf = new int[2000];
        for (int i = 0; i < uf.length; i++) uf[i] = i;
        
        for (int[] edge : edges) {
            if (find(edge[0], uf) == find(edge[1], uf)) return edge;
            union(edge[0], edge[1], uf);
        }
        
        return new int[] {};
    }
    
    private void union(int lhs, int rhs, int[] uf) {
        int lroot = find(lhs, uf);
        int rroot = find(rhs, uf);
        if (lroot == rroot) return;
        uf[rroot] = lroot;
    }
    
    private int find(int t, int[] uf) {
        if (uf[t] == t) return t;
        uf[t] = find(uf[t], uf);
        return uf[t];
    }
    
    /* DFS, O(n^2)
    private Map<Integer, Set<Integer>> adj = new HashMap<>();
    
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) return new int[] {};
        for (int[] edge : edges) {
            int src = edge[0], dst = edge[1];
            if (dfs(src, dst, new HashSet<>())) return edge;
            adj.computeIfAbsent(src, k -> new HashSet<>()).add(dst);
            adj.computeIfAbsent(dst, k -> new HashSet<>()).add(src);
        }
        
        return new int[]{};
    }
       
    private boolean dfs(int src, int dst, Set<Integer> visited) {
        if (src == dst) return true;
        if (visited.contains(src)) return false;
        
        visited.add(src);
        for (Integer node : adj.getOrDefault(src, new HashSet<Integer>())) {
            if (dfs(node, dst, visited)) return true;
        }
        
        return false;
    }
     */
    
    public static void main(String[] args) {
        LC0684 lc = new LC0684();
        int[][] edges = {{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(lc.findRedundantConnection(edges)));
    }

}
