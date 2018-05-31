package hmeng.lc.hard;

import java.util.PriorityQueue;
import java.util.Queue;

/*
295. Find Median from Data Stream
DescriptionHintsSubmissionsDiscussSolution

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
For example,

[2,3,4] , the median is 3 

[2,3], the median is (2 + 3) / 2 = 2.5 

Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

 */
public class LC0295 {
    private Queue<Integer> upper = new PriorityQueue<>();
    private Queue<Integer> lower = new PriorityQueue<>((l, r) -> r - l);
    /** initialize your data structure here. */
    public LC0295() {
    }
    
    public void addNum(int num) {
        upper.offer(num);
        lower.offer(upper.poll());
        if (upper.size() < lower.size()) {
            upper.offer(lower.poll());
        }
    }
    
    public double findMedian() {
        if (upper.size() == lower.size()) {
            return (double)upper.peek()/2 + (double)lower.peek()/2;
        }
        
        return upper.peek();
    }
    
    public static void main(String[] args) {
        LC0295 lc = new LC0295();
        lc.addNum(-1);
        System.out.println(lc.findMedian());
        lc.addNum(-2);
        System.out.println(lc.findMedian());
    }
}
