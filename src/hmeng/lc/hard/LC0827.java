package hmeng.lc.hard;

import java.util.HashSet;
import java.util.Set;

/*
827. Making A Large Island

In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 1.
 

Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.
 */
public class LC0827 {
    private static class UnionFind {
        int[] uf;
        int[] size;
        int n;
        UnionFind(int n) {
            this.n = n;
            init(n);
        }
        
        boolean union(int l, int r) {
            int pl = find(l);
            int pr = find(r);
            if (pl == pr) return false;
            
            if (size[pl] >= size[pr]) {
                uf[pr] = pl;
                size[pl] += size[pr];
            } else if (size[pl] < size[pr]) {
                uf[pl] = pr;
                size[pr] += size[pl];
            }
            
            return true;
        }
        
        int getSize(int t) {
            if (t >= n) throw new IllegalArgumentException("msg");
            return size[find(t)];
        }
        
        int find(int t) {
            if (t >= n) throw new IllegalArgumentException("msg");
            if (uf[t] == t) return t;
            uf[t] = find(uf[t]);
            return uf[t];
        }
        
        private void init(int n) {
            uf = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                uf[i] = i;
                size[i] = 1;
            }
        }
    }
    
    private int rowLen;
    private int colLen;
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        rowLen = grid.length;
        colLen = grid[0].length;
    
        UnionFind uf = new UnionFind(rowLen * colLen);
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] != 1) continue;
                if (row-1 >= 0 && grid[row-1][col] == 1) uf.union(getId(row-1, col), getId(row, col));
                if (col-1 >= 0 && grid[row][col-1] == 1) uf.union(getId(row, col-1), getId(row, col));
            }
        }
        
        int res = 0;

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] != 0) continue;
                Set<Integer> set = new HashSet<>();
                for (int[] d : DIR) {
                    int x = row + d[0], y = col + d[1];
                    if (isValid(x, y) && grid[x][y] == 1) {
                        set.add(uf.find(getId(x, y)));
                    }
                }
                int size = 0;
                for(int id : set) size += uf.getSize(id);
                res = Math.max(res, size+1);
            }
        }
        
        return res == 0 ? rowLen * colLen : res;
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
    }
    
    private int getId(int row, int col) {
        return colLen * row + col;
    }
    public static void main(String[] args) {
        LC0827 lc = new LC0827();
        int[][] grid = {{1,0,0},{0,1,0},{1,1,0}};
        System.out.println(lc.largestIsland(grid));
    }

}
