package hmeng.lc.hard;

/**
329. Longest Increasing Path in a Matrix
DescriptionHintsSubmissionsDiscussSolution
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 */
public class LC0329 {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int rowLen = matrix.length, colLen = matrix[0].length;
        
        int[][] visited = new int[rowLen][colLen];

        int res = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                res = Math.max(res, dfs(row, col, rowLen, colLen, matrix, visited));
            }
        }
        
        return res;
    }
    
    private int dfs(int row, int col, int rowLen, int colLen, int[][] matrix, int[][] visited) {
        if (visited[row][col] >= 1 ) return visited[row][col];
        int len = 1;
        if (row - 1 >= 0 && matrix[row-1][col] > matrix[row][col]) {
            len = Math.max(len, 1 + dfs(row-1, col, rowLen, colLen, matrix, visited));
        }
        if (row + 1 < rowLen && matrix[row+1][col] > matrix[row][col]) {
            len = Math.max(len, 1 + dfs(row+1, col, rowLen, colLen, matrix, visited));
        }
        if (col - 1 >= 0 && matrix[row][col-1] > matrix[row][col]) {
            len = Math.max(len, 1 + dfs(row, col-1, rowLen, colLen, matrix, visited));
        }
        if (col + 1 < colLen && matrix[row][col+1] > matrix[row][col]) {
            len = Math.max(len, 1 + dfs(row, col+1, rowLen, colLen, matrix, visited));
        }
        
        visited[row][col] = len;
        return len;
    }
}
