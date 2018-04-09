package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
417. Pacific Atlantic Water Flow

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

*/
public class LC0417 {

    private static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private void bfs(int[][] matrix, boolean[][] ap, int rowLen, int colLen) {
        Deque<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++)
                if (ap[i][j]) queue.offer(new int[] {i, j});
        }
        
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : DIR) {
                int row = cell[0] + dir[0];
                int col = cell[1] + dir[1];
                if (row >= 0 && row < rowLen && col >=0 && col < colLen && !ap[row][col]
                    && matrix[row][col] >= matrix[cell[0]][cell[1]]) {
                    ap[row][col] = true;
                    queue.offer(new int[] {row, col});
                }
            }
        }
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<int[]>();
        
        int rowLen = matrix.length, colLen = matrix[0].length;
        boolean[][] p = new boolean[rowLen][colLen];
        boolean[][] a = new boolean[rowLen][colLen];
        
        for (int i = 0; i < rowLen; i++) {
            p[i][0] = true;
            a[i][colLen-1] = true;
        }
        for (int j = 0; j < colLen; j++) {
            p[0][j] = true;
            a[rowLen-1][j] = true;
        }
        
        bfs(matrix, p, rowLen, colLen);
        bfs(matrix, a, rowLen, colLen);
        
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++)
                if (a[i][j] && p[i][j]) res.add(new int[] {i, j});
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0417 lc = new LC0417();
        int[][] matrix = {
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        };
        System.out.println(lc.pacificAtlantic(matrix));
    }
}
