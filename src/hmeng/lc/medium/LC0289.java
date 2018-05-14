package hmeng.lc.medium;

import java.util.Arrays;

/*
289. Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British 
mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors 
(horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.

Follow up:

    Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first 
    and then use their updated values to update other cells.
    In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when 
    the active area encroaches the border of the array. How would you address these problems?

 */
public class LC0289 {

    /*
     * time: O(mn), space: O(mn)
     */
    private static final int[][] DIR = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
    
    public void gameOfLife(int[][] board) {
        int rowLen = board.length, colLen = board[0].length;
        int[][] copy = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            copy[row] = Arrays.copyOf(board[row], board[row].length);
        }
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int liveNeighbor = 0;
                for (int[] dir : DIR) {
                    int x = row + dir[0], y = col + dir[1];
                    liveNeighbor += getLiveNeighbor(x, y, rowLen, colLen, copy);
                }
                if (isLiveCell(row, col, copy)) {
                    if (liveNeighbor < 2) board[row][col] = 0;
                    else if (liveNeighbor == 2 || liveNeighbor == 3) continue;
                    else if (liveNeighbor > 3) board[row][col] = 0;
                } else {
                    if (liveNeighbor == 3)
                        board[row][col] = 1;
                }
            }
        }
    }
    
    private boolean isLiveCell(int row, int col, int[][] board) {
        return board[row][col] == 1;
    }
    
    private int getLiveNeighbor(int row, int col, int rowLen, int colLen, int[][] board) {
        if (row < 0 || col < 0 || row >= rowLen || col >= colLen || board[row][col] == 0) return 0;
        return 1;
    }
    
}
