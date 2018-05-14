package hmeng.lc.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
406. Queue Reconstruction by Height

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
where h is the height of the person and k is the number of people in front of this person who have a height greater 
than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 */
public class LC0406 {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][0];
        Arrays.sort(people, (l, r) -> {
            if (l[0] == r[0]) return l[1] - r[1];
            else return r[0] - l[0];
        });
        
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        
        int[][] res = new int[people.length][2];
        int index = 0;
        for (int[] p : list) {
            res[index++] = p;
        }
        
        return res;
    }
}
