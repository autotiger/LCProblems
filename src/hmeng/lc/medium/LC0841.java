package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
841. Keys and Rooms

There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v 
opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
Explanation:  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.

Example 2:

Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.

Note:

    1 <= rooms.length <= 1000
    0 <= rooms[i].length <= 1000
    The number of keys in all rooms combined is at most 3000.

 */
public class LC0841 {
    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int n;
        int componentCount;
        
        UnionFind(int n) {
            this.n = n;
            this.componentCount = n;
            init();
        }
        
        private void init() {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        boolean union(int i, int j) {
            int pi = find(i), pj = find(j);
            if (pi == pj) return false;
            if (rank[pi] > rank[pj]) {
                parent[pj] = pi;
            } else if (rank[pi] < rank[pj]) {
                parent[pi] = pj;
            } else {
                parent[pj] = pi;
                rank[pi]++;
            }
            componentCount--;
            return true;
        }
        
        int find(int t) {
            if (parent[t] == t) return t;
            parent[t] = find(parent[t]);
            return parent[t];
        }
    }
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) return false;
        int size = rooms.size();
        UnionFind uf = new UnionFind(size);
        
        for (int i = 0; i < size; i++) {
            List<Integer> rms = rooms.get(i);
            if (rms.isEmpty()) continue;
            for (int rm : rms) {
                uf.union(i, rm);
            }
        }
        
        return uf.componentCount == 1;
    }
    
    
    public static void main(String[] args) {
        LC0841 lc = new LC0841();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(new ArrayList<>());
        System.out.println(lc.canVisitAllRooms(rooms));
    }

}
