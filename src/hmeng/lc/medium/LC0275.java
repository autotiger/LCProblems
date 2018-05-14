package hmeng.lc.medium;

/*
275. H-Index II

Given an array of citations in ascending order (each citation is a non-negative integer) of a researcher, write a function to
compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations 
each, and the other N - h papers have no more than hcitations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

 */
public class LC0275 {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0 || citations[citations.length-1] == 0) return 0;
        int l = 0, r = citations.length-1, n = citations.length;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (citations[mid] < n-mid) l = mid+1;
            else r = mid;
        }
        
        return n-r;
    }
    
    public static void main(String[] args) {
        LC0275 lc = new LC0275();
        int[] citations = {0, 0, 1};
        System.out.println(lc.hIndex(citations));
    }

}
