package hmeng.lc.hard;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
460. LFU Cache

Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least
frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

 */
public class LC0460 {

    private static class Node {
        int key;
        int val;
        int freq = 1;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public int hashCode() {
            int res = 17;
            res += key * 34;
            return res;
        }
    }
    
    Map<Integer, Node> map = new HashMap<>();
    Map<Integer, LinkedHashSet<Node>> cache = new HashMap<>();
    
    private int cap;
    
    private int min = 1;
    
    public LC0460(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();
        this.cap = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        Set<Node> set = cache.get(node.freq);
        set.remove(node);
        if (set.isEmpty() && min == node.freq) min++;
        node.freq++;
        cache.computeIfAbsent(node.freq, k -> new LinkedHashSet<>()).add(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        if (cap == 0) return;
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            get(key);
        } else {
            Node node = new Node(key, value);
            if (map.size() == cap) {
                Node remove = cache.get(min).iterator().next();
                map.remove(remove.key);
                cache.get(min).remove(remove);
            }
            
            min = 1;
            map.put(key, node);
            cache.computeIfAbsent(min, k -> new LinkedHashSet<>()).add(node);
        }
    }
    
    public static void main(String[] args) {
        LC0460 lc = new LC0460(2);
        //[[2],[2,1],[2,2],[2],[1,1],[4,1],[2]
        lc.put(2, 1);
        lc.put(2, 2);
        System.out.println(lc.get(2));
        lc.put(1, 1);
        lc.put(4, 1);
        System.out.println(lc.get(2));
    }

}
