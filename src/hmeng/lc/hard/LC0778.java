package hmeng.lc.hard;

import java.util.PriorityQueue;
import java.util.Queue;

/*
778. Swim in Rising Water

On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if 
and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the 
boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Note:

    2 <= N <= 50.
    grid[i][j] is a permutation of [0, ..., N*N - 1].

 */
public class LC0778 {
    private static final int[][] DIR = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length, cols = grid[0].length;
        //height, x, y
        Queue<int[]> queue = new PriorityQueue<>((l, r) -> l[0] - r[0]);
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        visited[0][0] = true;
        queue.offer(new int[] {grid[0][0], 0, 0});
        int res = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            res = Math.max(res, cur[0]);
            if (cur[1] == rows-1 && cur[2] == cols-1) return res;
            for (int[] dir : DIR) {
                int x = dir[0] + cur[1], y = dir[1] + cur[2];
                if (x < 0 || y < 0 || x >= rows || y >= cols || visited[x][y])
                    continue;
                visited[x][y] = true;
                int[] cell = new int[] {grid[x][y], x, y};
                queue.offer(cell);
            }
        }
        
        return -1;
    }
}
