package hmeng.lc.hard;

/*
52. N-Queens II

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]


 */
public class LC0052 {
    private int count = 0;
    
    public int totalNQueens(int n) {
        if (n <= 0) return count;
        
        int[] cols = new int[n];
        dfs(cols, 0, n);
        
        return count;
    }
    
    private void dfs(int[] cols, int row, int n) {
        if (row == n) {
            count++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (canPut(i, cols, row)) {
                cols[row] = i;
                dfs(cols, row+1, n);
            }
        }
    }
    
    private boolean canPut(int i, int[] cols, int row) {
        for (int j = 0; j < row; j++) {
            if (cols[j] == i) return false;
            if (row-j == i-cols[j] || row-j == cols[j]-i) return false;
        }

        return true;
    }
}
