package hmeng.lc.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
803. Bricks Falling When Hit

We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly 
connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) 
on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.

Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause 
no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.

 

Note:

    The number of rows and columns in the grid will be in the range [1, 200].
    The number of erasures will not exceed the area of the grid.
    It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
    An erasure may refer to a location with no brick - if it does, no bricks drop.


 */
public class LC0803 {
    
    private static class UnionFind {
        int[] parent;
        int[] rank;
        int[] size;
        int n;
        int col;
        int count;
        
        UnionFind(int row, int col) {
            this.n = row * col;
            this.col = col;
            this.count = n;
            init();
        }
        
        private void init() {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
            
            rank = new int[n];
            Arrays.fill(rank, Integer.MIN_VALUE);
            for (int i = 0; i < col; i++)
                rank[i] = 0;
                
            size = new int[n];
            Arrays.fill(size, 1);
        }
        
        boolean union(int i, int j) {
            int pi = find(i), pj = find(j);
            if (pi == pj) return false;
            if (rank[pi] > rank[pj]) {
                parent[pj] = pi;
                size[pi] += size[pj];
            } else if (rank[pi] < rank[pj]) {
                parent[pi] = pj;
                size[pj] += size[pi];
            } else {
                if (size[pj] < size[pi]) {
                    parent[pj] = pi;
                    size[pi] += size[pj];
                    rank[pi]++;
                } else {
                    parent[pi] = pj;
                    size[pj] += size[pi];
                    rank[pj]++;                    
                }
            }
            
            count--;
            return true;
        }
        
        int find(int t) {
            if (parent[t] == t) return t;
            parent[t] = find(parent[t]);
            return parent[t];
        }
        
    }
    
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[] hitBricks2(int[][] grid, int[][] hits) {
        if (grid == null || grid.length < 2 || grid[0].length == 0 || hits == null || hits.length == 0) return new int[0];
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) grid[hit[0]][hit[1]] = 0;
            else grid[hit[0]][hit[1]] = -1;
        }
        
        int rowLen = grid.length, colLen = grid[0].length;
        UnionFind uf = new UnionFind(rowLen, colLen);
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] != 1) continue;
                if (row-1 >= 0 && grid[row-1][col] == 1)
                    uf.union(getId(row-1, col, colLen), getId(row, col, colLen));
                if (col-1 >= 0 && grid[row][col-1] == 1)
                    uf.union(getId(row, col-1, colLen), getId(row, col, colLen));
            }
        }
        
        int[] res = new int[hits.length];
        for (int i = hits.length-1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == -1) {
                grid[x][y] = 0;
                continue; 
            }
            Set<Integer> neighbor = new HashSet<>();
            for (int[] dir : DIR) {
                int newX= x + dir[0], newY = y + dir[1];
                if (newX < 0 || newY < 0 || newX >= rowLen || newY >= colLen || grid[newX][newY] != 1) continue;
                neighbor.add(uf.find(getId(newX, newY, colLen)));
            }
            grid[x][y] = 1;
            for (int n : neighbor) {
                int pn = uf.find(n);
                if (uf.rank[pn] < 0) res[i] += uf.size[pn];
                uf.union(getId(x, y, colLen), pn);
            }
            if (uf.rank[uf.find(getId(x, y, colLen))] < 0) res[i] = 0;
        }
        
        return res;
    }
    
    private int getId(int row, int col, int colLen) {
        return row * colLen + col;
    }
    
    /*
     * DFS, stack overflow
     */   
    private int rowLen;
    
    private int colLen;
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        if (grid == null || grid.length == 0 || grid[0].length < 2 || hits == null || hits.length == 0) return new int[0];
        
        rowLen = grid.length;
        colLen = grid[0].length;
        int total = getNumberOfBricks(grid);

        int[] res = new int[hits.length];
        for (int i = 0; i < hits.length; i++) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 0) continue;
            grid[x][y] = 0;
            int num = getNumberOfBricks(grid);
            res[i] = total - num - 1;
            res[i] = res[i] < 0 ? 0 : res[i];
            total = num;
        }
        
        return res;
    }
    
    private int getNumberOfBricks(int[][] grid) {
        boolean[][] visited = new boolean[rowLen][colLen];
        int res = 0;
        for (int col = 0; col < colLen; col++) {
            if (!visited[0][col] && grid[0][col] == 1) res += dfs(0, col, visited, grid);
        }
        
        return res;
    }
    
    private int dfs(int row, int col, boolean[][] visited, int[][] grid) {
        if (row < 0 || col < 0 || row >= rowLen || col >= colLen || visited[row][col] || grid[row][col] == 0) return 0;
        visited[row][col] = true;
        int sum = 0;
        for (int[] d : DIR) {
            int x = row + d[0], y = col + d[1];
            sum += dfs(x, y, visited, grid);
        }
        
        return sum + 1;
    }
    ///////////////////
    
    public static void main(String[] args) {
        LC0803 lc = new LC0803();
        //int[][] grid = {{1},{1},{1},{1},{1}};
        //int[][] hits = {{3, 0}, {4, 0}, {1, 0}, {2, 0}, {0, 0}};
        //int[][] grid = {{1,0,1},{1,1,1}};
        //int[][] hits = {{0,0},{0,2},{1,1}};
        int[][] grid = {{0,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{0,0,0,1,0,0,1,1,1},{0,0,1,1,0,1,1,1,0},{0,0,0,0,0,1,1,1,1},{0,0,0,0,0,0,0,1,0}};
        //int[][] hits = {{1,8},{2,1},{1,4},{3,0},{3,4},{0,7},{1,6},{0,8},{2,5},{3,2},{2,0},{0,2},{0,5},{0,1},{4,8},{3,7},{0,6},{5,7},{5,3},{2,6},{2,2},{5,8},{2,8},{4,0},{3,3},{1,1},{0,0},{4,7},{0,3},{2,4},{3,1},{1,0},{5,2},{3,8},{4,2},{5,0},{1,2},{1,7},{3,6},{4,1},{5,6},{0,4},{5,5},{5,4},{1,5},{4,4},{3,5},{4,6},{2,3},{2,7}};
        int[][] hits = {{1,8},{2,1},{1,4},{3,0},{3,4},{0,7},{1,6}};
        System.out.println(Arrays.toString(lc.hitBricks2(grid, hits)));
    }

}
