package hmeng.lc.medium;

/*
562. Longest Line of Consecutive One in Matrix

Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LC0562 {
    private static class Cell {
        int h;
        int v;
        int d;
        int a;
        Cell() {
        }
        Cell(int h, int v, int d, int a) {
            this.h = h;
            this.v = v;
            this.d = d;
            this.a = a;
        }
    }
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int rowLen = M.length, colLen = M[0].length;
        Cell[][] cells = new Cell[rowLen][colLen];
        int res = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                cells[row][col] = new Cell();
                if (M[row][col] == 0) continue;
                cells[row][col] = new Cell(1,1,1,1);
                if (row-1 >= 0) {
                    cells[row][col].v = cells[row-1][col].v+1;
                    if (col-1 >= 0)
                        cells[row][col].d = cells[row-1][col-1].d+1;
                    if (col+1 < colLen)
                        cells[row][col].a = cells[row-1][col+1].a+1;
                }
                if (col-1 >= 0) {
                    cells[row][col].h = cells[row][col-1].h+1;
                }
                
                res = Math.max(res, Math.max(Math.max(cells[row][col].h, cells[row][col].v),
                               Math.max(cells[row][col].d, cells[row][col].a)));
            }
        }
        
        return res;
    }
}
