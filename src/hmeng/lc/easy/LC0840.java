package hmeng.lc.easy;

/*
840. Magic Squares In Grid

A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

 

Example 1:

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.

Note:

    1 <= grid.length <= 10
    1 <= grid[0].length <= 10
    0 <= grid[i][j] <= 15


 */
public class LC0840 {
    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) return 0;
        int count = 0;
        for (int row = 0; row <= grid.length-3; row++) {
            for (int col = 0; col <= grid[0].length-3; col++) {
                if (isMagic(grid, row, col)) count++;
            }
        }
        
        return count;
    }
    
    private boolean isMagic(int[][] grid, int row, int col) {
        int[] count = new int[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = grid[row+i][col+j];
                if (val > 9 || val <= 0) return false;
                else count[val]++;
            }
        }
        
        for (int i = 1; i <= 9; i++) {
            if (count[i] != 1) return false;
        }
        
        int sum = 15;
        for (int i = 0; i < 3; i++) {
            int val = grid[row+i][col] + grid[row+i][col+1] + grid[row+i][col+2];
            if (val != sum) return false;
        }
        for (int j = 0; j < 3; j++) {
            int val = grid[row][col+j] + grid[row+1][col+j] + grid[row+2][col+j];
            if (val != sum) return false;
        }
        
        if (sum != grid[row][col] + grid[row+1][col+1] + grid[row+2][col+2]) return false;
        if (sum != grid[row][col+2] + grid[row+1][col+1] + grid[row+2][col]) return false;
        
        return true;
    }
    
    public static void main(String[] args) {
        LC0840 lc = new LC0840();
        int[][] grid = {{10,3,5},{1,6,11},{7,9,2}};
        System.out.println(lc.numMagicSquaresInside(grid));
    }
}
