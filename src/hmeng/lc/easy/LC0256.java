package hmeng.lc.easy;
/*
256. Paint House

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. You have to paint all the houses such 
that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, 
costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 
with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
 */
public class LC0256 {

    /*
     * DP, O(n)
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int[] pre = new int[3];
        for (int house = 0; house < costs.length; house++) {
            int[] cur = new int[3];
            for (int color = 0; color < 3; color++) {
                cur[color] = costs[house][color] + findMinCost(color, pre);
            }
            pre = cur;
        }

        int res = Integer.MAX_VALUE;
        for (int color = 0; color < 3; color++) {
            res = Math.min(res, pre[color]);
        }
        
        return res;
    }
    
    private int findMinCost(int color, int[] pre) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i == color) continue;
            res = Math.min(res, pre[i]);
        }
        
        return res;
    }
}
