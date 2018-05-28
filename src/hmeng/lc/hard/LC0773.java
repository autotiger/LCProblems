package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
773. Sliding Puzzle

On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, 
return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Input: board = [[3,2,4],[1,5,0]]
Output: 14

Note:

    board will be a 2 x 3 array as described above.
    board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

 */
public class LC0773 {

    private static final Map<Integer, List<Integer>> swapMap = new HashMap<>();
    
    public int slidingPuzzle(int[][] board) {
        swapMap.put(0, Arrays.asList(1, 3));
        swapMap.put(1, Arrays.asList(0, 2, 4));
        swapMap.put(2, Arrays.asList(1, 5));
        swapMap.put(3, Arrays.asList(0, 4));
        swapMap.put(4, Arrays.asList(1, 3, 5));
        swapMap.put(5, Arrays.asList(2, 4));
        
        Set<String> fore = new HashSet<>();
        Set<String> back = new HashSet<>();
        Set<String> visited = new HashSet<>();
        String start = encode(board);
        fore.add(start);
        String end = "123450";
        back.add(end);
        
        int steps = 0;
        while (!fore.isEmpty() && !back.isEmpty()) {
            if (fore.size() > back.size()) {
                Set<String> tmp = fore;
                fore = back;
                back = tmp;
            }
            
            Set<String> tmp = new HashSet<>();
            for (String state : fore) {
                if (back.contains(state)) return steps;
                visited.add(state);
                List<String> next = getNeighbers(state);
                for (String n : next) {
                    if (visited.contains(n)) continue;
                    tmp.add(n);
                }
            }
            fore = tmp;
            steps++;
        }
        
        return -1;
    }
    
    private List<String> getNeighbers(String state) {
        List<String> res = new ArrayList<>();
        int zero = state.indexOf("0");
        char[] chars = state.toCharArray();
        for (int j : swapMap.get(zero)) {
            swap(chars, zero, j);
            res.add(new String(chars));
            swap(chars, zero, j);
        }
        
        return res;
    }
    
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
    
    private String encode(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                sb.append(String.valueOf(board[row][col]));
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        LC0773 lc = new LC0773();
        int[][] board = {{1,2,3},{4,0,5}};
        System.out.println(lc.slidingPuzzle(board));
    }

}
