package hmeng.lc.hard;

import java.util.PriorityQueue;
import java.util.Queue;

/*
407. Trapping Rain Water II

Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
 */
public class LC0407 {

    private static class Cell {
        int x;
        int y;
        int h;
        Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 2 || heightMap[0].length < 2) return 0;
        Queue<Cell> pq = new PriorityQueue<>((l, r) -> l.h - r.h);
        
        int rowLen = heightMap.length, colLen = heightMap[0].length;
        int[] rows = new int[] {0, rowLen-1}, cols = new int[] {0, colLen-1};
        boolean[][] visited = new boolean[rowLen][colLen];
        for (int col = 0; col < colLen; col++) {
            for (int row = 0; row < rows.length; row++) {
                pq.offer(new Cell(rows[row], col, heightMap[rows[row]][col]));
                visited[rows[row]][col] = true;
            }
        }
        for (int row = 1; row < rowLen-1; row++) {
            for (int col = 0; col < cols.length; col++) {
                pq.offer(new Cell(row, cols[col], heightMap[row][cols[col]]));
                visited[row][cols[col]] = true;
            }
        }
        
        int water = 0, level = -1;
        int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        while (!pq.isEmpty()) {
            Cell c = pq.poll();
            if (level < c.h) level = c.h;
            for (int[] d : dir) {
                int x = d[0] + c.x, y = d[1] + c.y;
                if (x < 0 || y < 0 || x >= rowLen || y >= colLen || visited[x][y]) continue;
                if (heightMap[x][y] < level) water += level - heightMap[x][y];
                visited[x][y] = true;
                pq.offer(new Cell(x, y, heightMap[x][y]));
            }
        }
        
        return water;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
