package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.List;

/*
51. N-Queens

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.


 */
public class LC0051 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        
        int[] cols = new int[n];
        dfs(res, cols, 0, n);
        
        return res;
    }
    
    private void dfs(List<List<String>> res, int[] cols, int row, int n) {
        if (row == n) {
            res.add(buildResult(cols));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (canPut(i, cols, row)) {
                cols[row] = i;
                dfs(res, cols, row+1, n);
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
    
    private List<String> buildResult(int[] cols) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cols.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols[i]; j++)
                sb.append(".");
            sb.append("Q");
            for (int j = cols[i]+1; j < cols.length; j++)
                sb.append(".");
            res.add(sb.toString());
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0051 lc = new LC0051();
        System.out.println(lc.solveNQueens(4));
    }

}
