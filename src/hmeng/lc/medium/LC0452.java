package hmeng.lc.medium;

import java.util.Arrays;

/*
452. Minimum Number of Arrows to Burst Balloons
DescriptionHintsSubmissionsDiscussSolution

There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. 
Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be 
at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart <= x <= xend. 
There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that 
must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).


 */
public class LC0452 {

    public int findMinArrowShots2(int[][] points) {
        if (points == null || points.length == 0)
            return 0;
        
        int[] prev = points[0];
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            int[] cur = points[i];
            if (isOverlap(prev, cur)) {
                continue;
            } else {
                prev = prev[1] < cur[1] ? prev : cur;
                res++;
            }
        }
        
        return res;
    }

    private boolean isOverlap(int[] lhs, int[] rhs) {
        return lhs[1] >= rhs[0] && rhs[1] >= lhs[0];
    }
    
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0)
            return 0;
        Arrays.sort(points, (l, r) -> l[1] - r[1]);
        
        int[] prev = points[0];
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > prev[1]) {
                res++;
                prev = points[i];
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0452 lc = new LC0452();
        int[][] points = {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
        System.out.println(lc.findMinArrowShots(points));
    }

}
