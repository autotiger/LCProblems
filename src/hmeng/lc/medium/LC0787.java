package hmeng.lc.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
787. Cheapest Flights Within K Stops

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
*/
public class LC0787 {

    /*
     * Dijsktra
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) return 0;
        if (flights == null || flights.length == 0) return -1;
        
        //u -> (v, price)
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], price = flight[2];
            adj.computeIfAbsent(u, k -> new HashMap<>()).put(v, price);
        }
        
        //{price, v, k}
        Queue<int[]> queue = new PriorityQueue<>((l, r) -> l[0] - r[0]);
        queue.add(new int[] {0, src, K+1});
        while (!queue.isEmpty()) {
            int[] node = queue.remove();
            int price = node[0];
            int city = node[1];
            int stop = node[2];
            if (city == dst) return price;
            if (stop > 0) {
                Map<Integer, Integer> map = adj.getOrDefault(city, new HashMap<>());
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    int d = entry.getKey();
                    queue.add(new int[] {price + entry.getValue(), d, stop-1});
                }
            }
        }
        
        return -1;
    }
    
    /*
     * Bellman-Ford
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) return 0;
        if (flights == null || flights.length == 0) return -1;
        
        int[] cities = new int[n];
        Arrays.fill(cities, Integer.MAX_VALUE);
        cities[src] = 0;
        
        for (int i = 0; i <= K; i++) {
            int[] temp = Arrays.copyOf(cities, n);
            for (int[] flight : flights) {
                int s = flight[0];
                int d = flight[1];
                int price = flight[2];
                if (cities[s] == Integer.MAX_VALUE) continue;
                if (cities[s] + price < temp[d]) temp[d] = cities[s] + price;
            }
            cities = temp;
        }
        
        if (cities[dst] == Integer.MAX_VALUE) return -1;
        return cities[dst];
    }
    
    public static void main(String[] args) {
        LC0787 lc = new LC0787();
        int n = 3;
        int[][] flights = { {0, 1, 100}, {1, 2, 100}, {0, 2, 500} };
        int src = 0;
        int dst = 2;
        int K = 0;
        System.out.println(lc.findCheapestPrice2(n, flights, src, dst, K));
        
        Integer.valueOf(1);
    }

}
