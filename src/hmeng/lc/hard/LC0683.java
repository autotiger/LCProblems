package hmeng.lc.hard;

import hmeng.lc.common.BIT;

/*
683. K Empty Slots

There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, 
there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the 
range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of 
flowers between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
Note:
The given array will be in the range [1, 20000].
 */
public class LC0683 {
   
    /*
     * Binary indexed tree, O(nlogn)
     */
    public int kEmptySlots(int[] flowers, int k) {
        int size = flowers.length;
        BIT bit = new BIT(size);
        int[] dp = new int[size+1];
        for (int i = 0; i < flowers.length; i++) {
            bit.update(flowers[i], 1);
            dp[flowers[i]] = 1;
            if (flowers[i] - k -1 > 0 && dp[flowers[i] - k -1] == 1) {
                if (bit.query(flowers[i]-1) - bit.query(flowers[i] - k -1) == 0)
                    return i+1;
            }
            
            if (flowers[i] + k + 1 <= size && dp[flowers[i] + k + 1] == 1) {
                if (bit.query(flowers[i]+k) - bit.query(flowers[i]) == 0)
                    return i+1;
            }
        }
        
        return -1;
    }
    /* TreeSet, O(nlogn)
    public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length < 2 | k < 0) return -1;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < flowers.length; i++) {
            Integer lower = ts.lower(flowers[i]);
            if (lower != null) {
                if (flowers[i] - lower.intValue() == k+1) return i+1;
            }
            Integer higher = ts.higher(flowers[i]);
            if (higher != null) {
                if (higher.intValue() - flowers[i] == k+1) return i+1;
            }
            ts.add(flowers[i]);
        }
        
        return -1;
    } */
    
    /* TLE O(n^2)
    public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length < 2 | k < 0) return -1;
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(flowers[0]);
        for (int i = 1; i < flowers.length; i++) {
            tree.add(flowers[i]);
            Iterator<Integer> iter = tree.iterator();
            int prev = -1, diff = -1;
            while (iter.hasNext()) {
                int val = iter.next().intValue();
                if (prev > 0) {
                    diff = val - prev;
                    if (diff == k+1) return i + 1;
                }
                prev = val;
            }
        }
        
        return -1;
    } */
    
    public static void main(String[] args) {
        LC0683 lc = new LC0683();
        int[] flowers = new int[] {1,2,3};
        int k = 1;
        System.out.println(lc.kEmptySlots(flowers, k));
    }

}
