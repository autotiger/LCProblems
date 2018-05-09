package hmeng.lc.hard;

/*
265. Paint House II

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house 
with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, 
costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with 
color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
 */
public class LC0265 {
    /*
     * DP, O(nk), space O(1)
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int nhouse = costs.length;
        //{color, min} / {color, secondmin}
        int[][] dp = new int[2][2];
        dp[0][0] = 0; dp[0][1] = Integer.MAX_VALUE;
        dp[1][0] = 0; dp[1][1] = Integer.MAX_VALUE;
        updateCostArray(dp, costs[0]);
        if (costs.length == 1) return dp[0][1];
        for (int house = 1; house < nhouse; house++) {
            dp = updateCostArray2(dp, costs[house]);
        }
        
        return dp[0][1];
    }
    
    private int[][] updateCostArray2(int[][] dp, int[] cost) {
        int[][] res = new int[2][2];
        res[0][0] = 0; res[0][1] = Integer.MAX_VALUE;
        res[1][0] = 0; res[1][1] = Integer.MAX_VALUE;

        for (int color = 0; color < cost.length; color++) {
            int min = cost[color] + (color == dp[0][0] ? dp[1][1] : dp[0][1]);
            if (min < res[0][1]) {
                res[1][0] = res[0][0]; res[1][1] = res[0][1];
                res[0][0] = color; res[0][1] = min;
            } else if (min < res[1][1]) {
                res[1][0] = color; res[1][1] = min;
            }
        }
        
        return res;
    }
    
    private void updateCostArray(int[][] dp, int[] cost) {
        for (int color = 0; color < cost.length; color++) {
            if (cost[color] < dp[0][1]) {
                dp[1][0] = dp[0][0];
                dp[1][1] = dp[0][1];
                dp[0][0] = color;
                dp[0][1] = cost[color];
            } else if (cost[color] < dp[1][1]) {
                dp[1][0] = color;
                dp[1][1] = cost[color];
            }
        }        
    }
    
    public static void main(String[] args) {
        LC0265 lc = new LC0265();
        int[][] costs = {{1,5,3},{2,9,4}};
        System.out.println(lc.minCostII(costs));
    }
}
