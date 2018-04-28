package hmeng.lc.medium;

/*
64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1->3->1->1->1 minimizes the sum.
 */
public class LC0064 {
    
    public int minPathSum(int[][] grid) {
        if (grid == null || grid[0].length == 0) throw new IllegalArgumentException();
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] sums = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (row == 0) {
                    if (col-1 >= 0) sums[row][col] = sums[row][col-1] + grid[row][col];
                    else sums[row][col] = grid[row][col];
                    continue;
                }
                if (col == 0) {
                    sums[row][col] = sums[row-1][col] + grid[row][col];
                    continue;
                }
                sums[row][col] = Math.min(sums[row-1][col], sums[row][col-1]) + grid[row][col];
            }
        }
        
        return sums[rowLen-1][colLen-1];
    }
}
