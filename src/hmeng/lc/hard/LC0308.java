package hmeng.lc.hard;

/*
308. Range Sum Query 2D - Mutable

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
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 <= row2 and col1 <= col2.
 */
public class LC0308 {

    private int[][] pre;
    
    private int[][] matrix;
    
    private int rowLen;
    
    private int colLen;
    
    public LC0308(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        this.matrix = matrix;
        rowLen = matrix.length;
        colLen = matrix[0].length;
        pre = new int[rowLen+1][colLen+1];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                pre[row][col] = pre[row][col-1] + matrix[row][col-1];
            }
        }
    }
    
    public void update(int row, int col, int val) {
        if (row >= rowLen || col >= rowLen || row < 0 || col < 0) return;
        int old = matrix[row][col];
        matrix[row][col] = val;
        int diff = val - old;
        for (int c = col+1; c <= colLen; c++) {
            pre[row][c] += diff;
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        //validate range
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            sum += pre[row][col2+1] - pre[row][col1];
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2}
        };
        LC0308 lc = new LC0308(matrix);
        lc.sumRegion(0, 0, 0, 0);
        lc.sumRegion(0, 1, 0, 1);
        lc.sumRegion(0, 0, 0, 1);
        lc.update(0, 0, 3);
        lc.update(0, 1, 5);
        lc.sumRegion(0, 0, 0, 0);
    }

}
