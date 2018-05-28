package hmeng.lc.medium;

/*
323. Number of Connected Components in an Undirected Graph
DescriptionHintsSubmissionsDiscussSolution
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an 
undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class LC0323 {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        for (int[] edge : edges) {
            union(parent, size, edge[0], edge[1]);
        }
        
        int res = 0;
        for (int i = 0; i < size.length; i++) {
            if (size[i] >= 1) res++;
        }
        
        return res;
    }
    
    private boolean union(int[] parent, int[] size, int l, int r) {
        int pl = find(parent, l), pr = find(parent, r);
        if (pl == pr) return false;
        if (size[pl] > size[pr]) {
            parent[pr] = pl;
            size[pl] += size[pr];
            size[pr] = 0;
        } else if (size[pl] < size[pr]) {
            parent[pl] = pr;
            size[pr] += size[pl];
            size[pl] = 0;
        } else {
            parent[pl] = pr;
            size[pr] += size[pl];
            size[pl] = 0;
        }

        return true;
    }
    
    private int find(int[] parent, int t) {
        if (parent[t] == t) return t;
        parent[t] = find(parent, parent[t]);
        return parent[t];
    }
}
