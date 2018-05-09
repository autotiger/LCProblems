package hmeng.lc.medium;

/*
261. Graph Valid Tree

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class LC0261 {
    private static class UnionFind {
        int[] parent;
        int[] rank;
        int component;
        int n;
        
        UnionFind(int n) {
            this.n = n;
            init();
        }
        private void init() {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            rank = new int[n];
            component = n;
        }
        
        boolean union(int i, int j) {
            int pi = find(i), pj = find(j);
            if (pi == pj) return false;
            if (rank[pi] < rank[pj]) {
                parent[pi] = pj;                
            } else if (rank[pj] < rank[pi]) {
                parent[pj] = pi;
            } else {
                parent[pj] = pi;
                rank[pi]++;
            }
            component--;
            return true;
        }
        
        int find(int t) {
            if (t == parent[t]) return t;
            parent[t] = find(parent[t]);
            return parent[t];
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if (n < 0) return false;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) return false;
        }
        
        return uf.component==1;
    }
}
