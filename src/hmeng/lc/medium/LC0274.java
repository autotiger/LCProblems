package hmeng.lc.medium;

import java.util.Arrays;

/*
274. H-Index

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, 
and the other N - h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

 */
public class LC0274 {
    
    /*
     * Bucket sort, O(n)
     */
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int[] array = new int[citations.length+1];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length) array[citations.length]++;
            else array[citations[i]]++;
        }
        int sum = 0;
        for (int j = array.length-1; j > 0; j--) {
            sum += array[j];
            if (sum >= j) return j;
        }
        
        return sum;
    }
    
    /*
     * Sort, O(nlogn)
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        
        Arrays.sort(citations);
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            int cur = citations[i];
            h = citations.length - i;
            if (h <= cur) return h;
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
    }

}
