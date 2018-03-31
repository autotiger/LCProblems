package hmeng.lc.easy;

/*
463. Island Perimeter

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, 
and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water 
inside that isn't connected to the water around the island). One cell is a square with side length 1. 
The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
https://leetcode.com/static/images/problemset/island.png

  */
public class LC0463 {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rowLen = grid.length, colLen = grid[0].length;
        int island = 0, edge = 0;;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    island++;
                    if (row + 1 < rowLen && grid[row+1][col] == 1) edge++;
                    if (col + 1 < colLen && grid[row][col+1] == 1) edge++;
                }
            }
        }
        
        return island * 4 - edge * 2;
    }
}
