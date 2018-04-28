package hmeng.other;

/*
183. Wood Cut
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

 Notice
You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0.

Example
For L=[232, 124, 456], k=7, return 114.

 */
public class LintCode183 {

    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max,  L[i]); 
        }
        
        int l = 1, r = max;
        while (l+1 < r) {
            int mid = l + (r-l)/2;
            int p = 0;
            for (int len : L) {
                p += len / mid;
            }
            if (p >= k) l = mid;
            else r = mid;
        }
        
        return l;
    }
    
    public static void main(String[] args) {
        LintCode183 wc = new LintCode183();
        int[] L = {232, 124, 456};
        int k = 7;
        System.out.println(wc.woodCut(L, k));
    }

}
