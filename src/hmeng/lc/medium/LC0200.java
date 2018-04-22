package hmeng.lc.medium;

import hmeng.lc.common.UnionFind;

/*
200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000

Answer: 1

Example 2:

11000
11000
00100
00011

Answer: 3
 */
public class LC0200 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rowLen = grid.length, colLen = grid[0].length;
        UnionFind uf = new UnionFind(rowLen * colLen);
        int zerocount = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int id = row*colLen+col;
                if (grid[row][col] == '0') {
                    zerocount++;
                    continue;
                }
                if (grid[row][col] == '1') {
                    if (col-1 >= 0 && grid[row][col-1] == '1') {
                        uf.union(id, id-1);
                    }
                    if (row-1 >= 0 && grid[row-1][col] == '1') {
                        uf.union(id, id-colLen);
                    }
                }
            }
        }

        return uf.getComponentCount() - zerocount;
    }
    
    /* DFS O(mn)
    private int rowLen;
    
    private int colLen;
    
    private char[][] grid;
    
    private static final int[][] DIR = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
                                        
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        rowLen = grid.length;
        colLen = grid[0].length;
        this.grid = grid;
        
        boolean[][] visited = new boolean[rowLen][colLen];
        
        int res = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (visited[row][col] || grid[row][col] == '0') continue;
                dfs(row, col, visited);
                res++;
            }
        }
        
        return res;
    }
    
    private void dfs(int row, int col, boolean[][] visited) {
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || visited[row][col] || grid[row][col] == '0') return;
        visited[row][col] = true;
        
        for (int[] dir : DIR) {
            dfs(row+dir[0], col+dir[1], visited);
        }
    }
    */
    public static void main(String[] args) {
        //char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        LC0200 lc = new LC0200();
        System.out.println(lc.numIslands(grid));
        
    }

}
