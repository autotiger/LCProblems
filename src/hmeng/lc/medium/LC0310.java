package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
310. Minimum Height Trees
DescriptionHintsSubmissionsDiscussSolution

For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, 
those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

return [3, 4]

Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class LC0310 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return Arrays.asList(0);
        }
        
        int[] degree = new int[n];
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            degree[u]++;
            degree[v]++;
            adj.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            adj.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        }
        
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                set.add(i);
            }
        }

        int count = n;
        while (count > 2) {
            Set<Integer> tmp = new HashSet<>();
            for (int u : set) {
                for (int v : adj.get(u)) {
                    if (set.contains(v)) continue;
                    degree[v]--;
                    if (degree[v] == 1) {
                        tmp.add(v);
                    }
                }
            }
            count -= set.size();
            set = tmp;
        }
        
        return new ArrayList<>(set);
    }
    
    public static void main(String[] args) {
        LC0310 lc = new LC0310();
        int n = 6;
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        //int[][] edges = {{0,1},{0,2},{0,3},{3,4},{4,5}};
        System.out.println(lc.findMinHeightTrees(n, edges));
    }

}
