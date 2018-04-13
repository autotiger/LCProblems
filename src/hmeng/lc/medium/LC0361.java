package hmeng.lc.medium;

/*
361. Bomb Enemy

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/

public class LC0361 {

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] res = new int[rowLen][colLen];
     
        for (int i = 0; i < rowLen; i++) {
            int leftSoFar = 0, rightSoFar = 0;
            for (int j = 0; j < colLen; j++) {
                char c = grid[i][j];
                if (c == 'W') {
                    leftSoFar = 0;
                } else if (c == 'E') {
                    leftSoFar++;
                } else {
                    res[i][j] = leftSoFar;
                }
            }
            for (int j = colLen-1; j >= 0; j--) {
                char c = grid[i][j];
                if (c == 'W') {
                    rightSoFar = 0;
                } else if (c == 'E') {
                    rightSoFar++;
                } else {
                    res[i][j] += rightSoFar;
                }
            }
        }
        
        int result = 0;
        for (int j = 0; j < colLen; j++) {
            int upSoFar = 0, downSoFar = 0;
            for (int i = 0; i < rowLen; i++) {
                char c = grid[i][j];
                if (c == 'W') {
                    upSoFar = 0;
                } else if (c == 'E') {
                    upSoFar++;
                } else {
                    res[i][j] += upSoFar;
                }
            }
            for (int i = rowLen-1; i >= 0; i--) {
                char c = grid[i][j];
                if (c == 'W') {
                    downSoFar = 0;
                } else if (c == 'E') {
                    downSoFar++;
                } else {
                    res[i][j] += downSoFar;
                    result = Math.max(result, res[i][j]);
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        LC0361 lc = new LC0361();
        char[][] grid = {
            {'0', 'E', '0', '0'},
            {'E', '0', 'W', 'E'},
            {'0', 'E', '0', '0'},
        };
        System.out.println(lc.maxKilledEnemies(grid));
    }

}
