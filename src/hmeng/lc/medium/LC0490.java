package hmeng.lc.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
490. The Maze

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/
public class LC0490 {
    
    private static int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rowLen;
    private int colLen;

    //bfs solution
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (Arrays.equals(start, destination)) return true;
        rowLen = maze.length;
        colLen = maze[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] currStart = queue.poll();
            visited[currStart[0]][currStart[1]] = true;
            for (int i = 0; i < DIR.length; i++) {
                int[] newStart = roll(currStart, i, maze);
                if (Arrays.equals(newStart, destination)) return true;
                if (visited[newStart[0]][newStart[1]]) continue;
                queue.offer(newStart);
            }
        }
        
        return false;
    }
    
    private int[] roll(int[] start, int d, int[][] maze) {
        int row = start[0], col = start[1];
        while (canRoll(start, row+DIR[d][0], col+DIR[d][1], maze)) {
            row += DIR[d][0];
            col += DIR[d][1];
        }
        
        return new int[] {row, col};
    }
    
    private boolean canRoll(int[] start, int row, int col, int[][] maze) {
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || maze[row][col] == 1) return false;
        return true;
    }
    
    
    /*
    //dfs solution
    private int rowLen;
    private int colLen;
    
    private int [][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        rowLen = maze.length;
        colLen = maze[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        
        return dfs(maze, start, destination, visited);
    }
    
    private boolean dfs(int[][] maze, int[] start, int[] dest, boolean[][] visited) {
        if (visited[start[0]][start[1]]) return false;
        if (Arrays.equals(start, dest)) return true;
        
        visited[start[0]][start[1]] = true;
        for (int i = 0; i < dir.length; i++) {
            int[] newStart = roll(maze, start, i);
            if (dfs(maze, newStart, dest, visited)) return true;
        }
        
        return false;
    }
    
    private int[] roll(int[][] maze, int[] start, int d) {
        int row = start[0], col = start[1];
        while (canRoll(maze, row+dir[d][0], col+dir[d][1])) {
            row += dir[d][0];
            col += dir[d][1];
        }
        
        return new int[] {row, col};
    }
    
    private boolean canRoll(int[][] maze, int row, int col) {
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || maze[row][col] == 1) return false;
        return true;
    }
*/
    public static void main(String[] args) {
        LC0490 lc = new LC0490();
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start = {0,4};
        int[] destination = {3,2};
        System.out.println(lc.hasPath(maze, start, destination));
    }

}
