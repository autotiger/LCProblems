package hmeng.lc.medium;

/*
73. Set Matrix Zeroes

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

 */
public class LC0073 {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        boolean r = false, c = false;
        
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    if (row == 0) r = true;
                    if (col == 0) c = true;
                    matrix[0][col] = matrix[row][0] = 0;
                }
            }
        }
        
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 1; col < matrix[0].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }
        
        for (int col = 1; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 1; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }
        
        if (r) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[0][col] = 0;
            }
        }
        
        if (c) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}
