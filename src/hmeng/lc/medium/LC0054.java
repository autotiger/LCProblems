package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
54. Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 */
public class LC0054 {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return Collections.emptyList();
        
        int left = 0, right = matrix[0].length-1;
        int up = 1, down = matrix.length-1;
        int total = matrix.length * matrix[0].length;
        
        List<Integer> res = new ArrayList<>(total);
        
        int row = 0, col = 0;
        //0,   1,    2,    3
        //up, down, left, right
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //go right first
        int d = 3;
        while (total > 0) {
            res.add(Integer.valueOf(matrix[row][col]));
            if (d == 0 && row + dir[d][0] < up) {
                up++;
                d = 3;
            }
            if (d == 1 && row + dir[d][0] > down) {
                down--;
                d = 2;
            }
            if (d == 2 && col + dir[d][1] < left) {
                left++;
                d = 0;
            }
            if (d == 3 && col + dir[d][1] > right) {
                right--;
                d = 1;
            }
            row += dir[d][0];
            col += dir[d][1];
            total--;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0054 lc = new LC0054();
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        lc.spiralOrder(matrix);
    }

}
