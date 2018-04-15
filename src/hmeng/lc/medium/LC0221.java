package hmeng.lc.medium;
/*
221. Maximal Square

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/
public class LC0221 {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rowLen = matrix.length, colLen = matrix[0].length;
        int[][] dp = new int[rowLen+1][colLen+1];
        int res = 0;
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                if (matrix[row-1][col-1] == '0') continue;
                dp[row][col] = Math.min(dp[row][col-1], Math.min(dp[row-1][col], dp[row-1][col-1])) + 1;
                res = Math.max(res, dp[row][col]);
            }
        }
        
        return res * res;
    }
    
    public static void main(String[] args) {
        LC0221 lc = new LC0221();
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
//        char[][] matrix = {
//                {'1'}
//        };
        System.out.println(lc.maximalSquare(matrix));
    }

}
