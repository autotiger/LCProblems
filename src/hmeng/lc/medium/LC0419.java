package hmeng.lc.medium;

/*
419. Battleships in a Board

Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
*/        
public class LC0419 {
    //check the last cell of the battleship
    //if its right/down cell is . or out of board, count++
    public int countBattleships(char[][] board) {
        if (board == null || board[0].length == 0) return 0;
        int rowLen = board.length, colLen = board[0].length;
        int count = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (board[row][col] == 'X') {
                    if ((col+1 < colLen && board[row][col+1] == '.' || col+1 >= colLen)
                        && (row+1 < rowLen && board[row+1][col] == '.' || row+1 >= rowLen))
                        count++;
                }
            }
        }
        
        return count;
    }
}
