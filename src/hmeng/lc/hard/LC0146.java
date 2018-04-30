package hmeng.lc.hard;

import java.util.HashMap;
import java.util.Map;

/*
146. LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache(2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */

public class LC0146 {

    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private Node head;
    private Node tail;
    private int capacity;
    private Map<Integer, Node> cache = new HashMap<>();
    
    public LC0146(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            Node node = new Node(key, value);
            cache.put(key, node);
            if (cache.size() == 1) {
                head = node;
                tail = node;
                return;
            }
            node.next = head;
            head.prev = node;
            head = node;

            if (cache.size() > capacity) {
                cache.remove(tail.key);
                tail.prev.next = null;
                tail = tail.prev;
            }
        }
    }
    
    private void moveToHead(Node node) {
        if (node == head) return;
        node.prev.next = node.next;
        if (node.next == null) tail = node.prev;
        else node.next.prev = node.prev;
        node.next = head;
        head.prev = node;
        node.prev = null;
        head = node;
    }
    
    public static void main(String[] args) {
        LC0146 lruCache = new LC0146(1);
        lruCache.put(2, 1);
        lruCache.get(2);
        lruCache.put(3, 2);
        lruCache.get(2);
        lruCache.get(3);
    }

}
