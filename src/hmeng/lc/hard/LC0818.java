package hmeng.lc.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
818. Race Car

Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)

Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).

When you get an instruction "A", your car does the following: position += speed, speed *= 2.

When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.

Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.

 */
public class LC0818 {

    /*
     * improved BFS
     */
    public int racecar2(int target) {
        if (target == 0) return 0;
        //point[0] = pos, point[1] = speed
        int[] point = new int[] {0, 1};
        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        queue.offer(point);
        visited.computeIfAbsent(0, k -> new HashSet<>()).add(1);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                if (p[0] == target) return step;
                //do R
                int[] next = new int[] {p[0], p[1] > 0 ? -1 : 1};
                if (Math.abs(p[0] - target) < target && (!visited.containsKey(next[0]) || !visited.get(next[0]).contains(next[1]))) {
                    queue.offer(next);
                    visited.computeIfAbsent(next[0], k -> new HashSet<>()).add(next[1]);
                }
                //do A
                next = new int[] {p[0]+p[1], 2 * p[1]};
                if (Math.abs(p[0] + p[1] - target) < target && (!visited.containsKey(next[0]) || !visited.get(next[0]).contains(next[1]))) {
                    queue.offer(next);
                    visited.computeIfAbsent(next[0], k -> new HashSet<>()).add(next[1]);
                }
            }
            step++;
        }
        
        return -1;
    }
    
    /*
     * pure BFS, time exceeded
     */
    public int racecar(int target) {
        if (target == 0) return 0;
        //point[0] = pos, point[1] = speed
        int[] point = new int[] {0, 1};
        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        queue.offer(point);
        visited.computeIfAbsent(0, k -> new HashSet<>()).add(1);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                //do R
                int[] next = new int[] {p[0], p[1] > 0 ? -1 : 1};
                if (next[0] == target) return step+1;
                if (Math.abs(next[0] - target) < target && (!visited.containsKey(next[0]) || !visited.get(next[0]).contains(next[1]))) {
                    queue.offer(next);
                    visited.computeIfAbsent(next[0], k -> new HashSet<>()).add(next[1]);
                }
                //do A
                next = new int[] {p[0]+p[1], 2 * p[1]};
                if (next[0] == target) return step+1;
                if (Math.abs(next[0] + next[1] - target) < target && (!visited.containsKey(next[0]) || !visited.get(next[0]).contains(next[1]))) {
                    queue.offer(next);
                    visited.computeIfAbsent(next[0], k -> new HashSet<>()).add(next[1]);
                }
            }
            step++;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
