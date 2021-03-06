package hmeng.lc.medium;

/**
304. Range Sum Query 2D - Immutable
DescriptionHintsSubmissionsDiscussSolution
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 <= row2 and col1 <= col2.

*/
public class LC0304 {

    private int[][] dp;
    
    private int rowLen;
    
    private int colLen;
    
    /**
     * O(mn) - init, O(1) - query
     */
    public LC0304(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        rowLen = matrix.length;
        colLen = matrix[0].length;
        dp = new int[rowLen+1][colLen+1];
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                dp[row][col] = dp[row-1][col] + dp[row][col-1] - dp[row-1][col-1] + matrix[row-1][col-1];
            }
        }         
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        //validate row, col
        return dp[row2+1][col2+1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row1][col1];
    }
    
    /* O(mn) init, O(m) query
    private int[][] prefixSumMatrix;
    
    public LC0304(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int rowLen = matrix.length, colLen = matrix[0].length;
        prefixSumMatrix = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            prefixSumMatrix[row][0] = matrix[row][0];
            for (int col = 1; col < colLen; col++) {
                prefixSumMatrix[row][col] = prefixSumMatrix[row][col-1] + matrix[row][col];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            int presum = col1 == 0 ? 0 : prefixSumMatrix[row][col1-1];
            sum += prefixSumMatrix[row][col2] - presum;
        }

        return sum;
    }*/
    
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        LC0304 lc = new LC0304(matrix);
        
        System.out.println(lc.sumRegion(2, 1, 4, 3));
    }

}
