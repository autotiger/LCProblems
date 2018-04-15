package hmeng.lc.hard;

import java.util.Deque;
import java.util.LinkedList;

/*
85. Maximal Rectangle

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
*/
public class LC0085 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rowLen = matrix.length, colLen = matrix[0].length;
        int[] heights = new int[colLen+1];
        int res = 0;
        for (int row = 0; row < rowLen; row++) {
            Deque<Integer> stack = new LinkedList<>();
            for (int col = 0; col <= colLen; col++) {
                if (col < colLen) {
                    if (matrix[row][col] == '1') heights[col]++;
                    else heights[col] = 0;
                }
                while (!stack.isEmpty() && heights[col] < heights[stack.peek()]) {
                    int val = heights[stack.pop()];
                    int start = stack.isEmpty() ? -1 : stack.peek();
                    res = Math.max(res, val * (col - start - 1));
                }
                stack.push(col);
            }
        }

        return res;
    }
}
