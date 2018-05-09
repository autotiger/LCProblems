package hmeng.lc.medium;

/*
801. Minimum Swaps To Make Sequences Increasing

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.

Note:

    A, B are arrays with the same length, and that length will be in the range [1, 1000].
    A[i], B[i] are integer values in the range [0, 2000].

 */
public class LC0801 {

    public int minSwap(int[] A, int[] B) {
        if (A.length == 1) return 0;
        int[] noswap = new int[A.length], swap = new int[A.length];
        noswap[0] = 0;
        swap[0] = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i-1] || B[i] <= B[i-1]) { //must swap
                noswap[i] = swap[i-1];
                swap[i] = noswap[i-1] + 1;
            } else if (A[i] <= B[i-1] || B[i] <= A[i-1]) { // cannot swap
                noswap[i] = noswap[i-1];
                swap[i] = swap[i-1]+1;
            } else {
                noswap[i] = Math.min(noswap[i-1], swap[i-1]);
                swap[i] = Math.min(noswap[i-1], swap[i-1])+1;
            }            
        }
        
        return Math.min(noswap[A.length-1], swap[A.length-1]);
    }
    
    public static void main(String[] args) {
        LC0801 lc = new LC0801();
        int[] A = {3,3,8,9,10};
        int[] B = {1,7,4,6,8};
        System.out.println(lc.minSwap(A, B));

    }

}
