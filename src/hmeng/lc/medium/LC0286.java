package hmeng.lc.medium;

import java.util.LinkedList;
import java.util.Queue;

/*
286. Walls and Gates

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance 
to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */
public class LC0286 {
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public void wallsAndGates(int[][] rooms) {
        
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[0].length; col++) {
                if (rooms[row][col] == 0) {
                    bfs(row, col, rooms);
                }
            }
        }
    }
    
    private void bfs(int row, int col, int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : DIR) {
                    int x = cur[0] + d[0], y = cur[1] + d[1];
                    if (x < 0 || y < 0 || x >= rooms.length || y >= rooms[0].length || rooms[x][y] == 0 || rooms[x][y] == -1) continue;
                    if (rooms[x][y] > rooms[cur[0]][cur[1]] + 1) {
                        rooms[x][y] = rooms[cur[0]][cur[1]] + 1;
                        queue.offer(new int[] {x, y});
                    }
                }
            }
        }
    }
}
