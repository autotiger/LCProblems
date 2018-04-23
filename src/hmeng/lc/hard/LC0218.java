package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
218. The Skyline Problem

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
Buildings Skyline Contour

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 <= Li, Ri <= INT_MAX, 0 < Hi <= INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 */
public class LC0218 {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) return res;
        
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings) {
            int l = building[0], r = building[1], h = building[2];
            points.add(new int[] {l, -h});
            points.add(new int[] {r, h});
        }
        
        Collections.sort(points, (l, r) -> {
            if (l[0] == r[0]) return l[1] - r[1];
            return l[0] - r[0];
        });

        Queue<Integer> queue = new PriorityQueue<>((l, r) -> r - l);
        queue.offer(0);
        int prev = 0;
        for (int[] point : points) {
            int x = point[0], h = point[1];
            if (h < 0) queue.offer(-h);
            else queue.remove(h);
            
            int curr = queue.peek();
            if (curr != prev) {
                res.add(new int[] {x, curr});
                prev = curr;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0218 lc = new LC0218();
        int[][] buildings = {{2, 4, 3},{2, 4, 5}};
        List<int[]> res = lc.getSkyline(buildings);
        for (int[] point : res) {
            System.out.println(Arrays.toString(point));
        }
    }
}
