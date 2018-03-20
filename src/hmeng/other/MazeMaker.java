package hmeng.other;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 0 - unvisited cell
 * 1 - path 
 * 2 - blocker
 */
public class MazeMaker {
    
    private static final Random RANDOM = new Random();
    
    private int rowLen;
    private int colLen;
    private int[] exit;
    private int[][] maze;
    private boolean done = false;
    
    public int[][] makeMaze(int rowLen, int colLen, int[] entrance, int[] exit, List<int[]> blockers) {
        this.rowLen = rowLen;
        this.colLen = colLen;
        this.exit = exit;
        maze = new int[rowLen][colLen];
        for (int[] blocker : blockers) {
            maze[blocker[0]][blocker[1]] = 2;
        }

        dfs(entrance[0], entrance[1]);
        
        return maze;
    }
    
    private boolean dfs(int row, int col) {
        if (isExit(row, col)) {
            maze[row][col] = 1;
            done = true;
            return true;
        }
        if (row < 0 || col < 0 || row >= rowLen || col >= colLen || isBlocker(row, col) || visited(row, col)) return false;
        maze[row][col] = 1;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        shuffleDirection(dir);
        boolean result = false;
        for (int[] d : dir) {
            result &= dfs(row+d[0], col+d[1]);
            if (done) return true;
        }
        
        if (!result) maze[row][col] = 0;
        
        return result;
    }
    
    private boolean isBlocker(int row, int col) {
        return maze[row][col] == 2;
    }
    
    private boolean visited(int row, int col) {
        return maze[row][col] == 1;
    }
    
    private boolean isExit(int row, int col) {
        return row == exit[0] && col == exit[1];
    }
    
    private void shuffleDirection(int[][] dir) {
        for (int i = dir.length; i > 0; i--) {
            int index = RANDOM.nextInt(i);
            int[] temp = dir[index];
            dir[index] = dir[i-1];
            dir[i-1] = temp;
        }
    }
    
    public static void main(String[] args) {
        MazeMaker maker = new MazeMaker();
        List<int[]> blockers = new ArrayList<>();
        blockers.add(new int[] {1, 1});
        blockers.add(new int[] {2, 5});
        blockers.add(new int[] {4, 6});
        blockers.add(new int[] {3, 3});
        int[][] maze = maker.makeMaze(6, 8, new int[] {1, 0}, new int[] {5, 7}, blockers);
        printMaze(maze);
    }

    public static void printMaze(int[][] maze) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                System.out.print(maze[row][col] + " ");
            }
            System.out.println("\n");
        }
    }
}
