package hmeng.lc.hard;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/*
815. Bus Routes

We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.
*/
public class LC0815 {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (routes == null || routes.length == 0) return -1;
        if (S == T) return 0;
        
        //stop id -> set of routes
        int startRoute = -1, endRoute = -1;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (routes[i][j] == S) startRoute = i;
                if (routes[i][j] == T) endRoute = i;
                map.computeIfAbsent(Integer.valueOf(routes[i][j]), k -> new HashSet<>()).add(Integer.valueOf(i));
            }
            if (startRoute > 0 && startRoute == endRoute) return 1;
        }
        
        if (startRoute < 0 || endRoute < 0) return -1;
        
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        visitedRoutes.add(Integer.valueOf(startRoute));
        for (int i = 0; i < routes[startRoute].length; i++) {
            queue.offer(Integer.valueOf(routes[startRoute][i]));
            visited.add(Integer.valueOf(routes[startRoute][i]));
        }
        
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean newRouteAdded = false;
            for (int i = 0; i < size; i++) {
                Integer stop = queue.poll();
                for (int route : map.get(stop)) {
                    if (visitedRoutes.contains(Integer.valueOf(route))) continue;
                    visitedRoutes.add(Integer.valueOf(route));
                    newRouteAdded = true;
                    for (int j = 0; j < routes[route].length; j++) {
                        if (routes[route][j] == T) return res+1;
                        if (visited.contains(Integer.valueOf(routes[route][j]))) continue;
                        queue.offer(Integer.valueOf(routes[route][j]));
                    }
                }
            }
            if (newRouteAdded) res++;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        LC0815 lc = new LC0815();
        int[][] routes = {
            //{0,1,6,16,22,23},{14,15,24,32},{4,10,12,20,24,28,33},{1,10,11,19,27,33},{11,23,25,28},{15,20,21,23,29},{29}
             {1,2,7},{3,6,7}
        };
        int S = 1;//4;
        int T = 6;//21;
        System.out.println(lc.numBusesToDestination(routes, S, T));
    }

}
