package hmeng.lc.common;

public class UnionFind {
    //union-find array
    private int[] uf;
    private int[] rank;
    //count of components
    private int count;
    
    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("size must be positive");
        uf = new int[size];
        init(uf);
        rank = new int[size];
    }
    
    private void init(int[] uf) {
        for (int i = 0; i < uf.length; i++) uf[i] = i;
        count = uf.length;
    }
    
    public int getComponentCount() {
        return count;
    }
    
    public boolean union(int lhs, int rhs) {
        int lroot = find(lhs);
        int rroot = find(rhs);
        if (lroot == rroot) return false;
        if (rank[lroot] < rank[rroot]) {
            uf[lroot] = rroot;
        } else if (rank[lroot] > rank[rroot]) {
            uf[rroot] = lroot;
        } else {
            uf[lroot] = rroot;
            rank[rroot]++;
        }
        count--;
        return true;
    }
    
    public int find(int t) {
        if (t >= uf.length) throw new IllegalArgumentException("target is not in set");
        if (t == uf[t]) return t;
        uf[t] = find(uf[t]);
        return uf[t];
    }
}
