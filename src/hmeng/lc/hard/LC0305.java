package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.List;

import hmeng.lc.common.UnionFind;

/*
305. Number of Islands II

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class LC0305 {

    private int rowLen;
    
    private int colLen;
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        rowLen = m;
        colLen = n;
        int size = m * n;
        int water = size;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] grid = new int[m][n];
        UnionFind uf = new UnionFind(size);
        List<Integer> res = new ArrayList<>();
        for (int[] pos : positions) {
            grid[pos[0]][pos[1]] = 1;
            int id = getId(pos[0], pos[1]);
            water--;
            for (int[] d : dir) {
                int x = pos[0] + d[0], y = pos[1] + d[1];
                if (x < 0 || x >= rowLen || y < 0 || y >= colLen) continue;
                if (grid[x][y] == 1) uf.union(id, getId(x, y));
            }
         
            res.add(uf.getComponentCount() - water);
        }
        
        return res;
    }
    
    private int getId(int x, int y) {
        return x * colLen + y;
    }
    
    public static void main(String[] args) {
        LC0305 lc = new LC0305();
        //int m = 3, n = 3;
        //int[][] positions = {{0,0}, {0,1}, {1,2}, {2,1}};
        int m = 8;
        int n = 4;
        int[][] positions = {{0,0},{7,1},{6,1},{3,3},{4,1}};
        System.out.println(lc.numIslands2(m, n, positions));
    }
}
