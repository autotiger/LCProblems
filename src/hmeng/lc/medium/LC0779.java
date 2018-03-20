package hmeng.lc.medium;

/*
779. K-th Symbol in Grammar

On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
Note:
- N will be an integer in the range [1, 30].
- K will be an integer in the range [1, 2^(N-1)].
*/
public class LC0779 {

    //observation: when k is 0-indexed
    // - it is like a binary tree if we expand. so the k-th position in row N is only decided by k/2 -th postion in row N-1
    //                          0
    //                        0   1
    //                       0 1 1 0
    // - for a subtree, left child has same digit as parent -> if k is even (left child), it has same digit, if k is odd (right child), it has different digit
    //       0      1
    //      0 1    1 0
    // Take N = 4, k = 5 (0-indexed) as an example, we calculate a path from (#4, 5), a leaf, back to root (#1, 0)
    // (#4, 5) -> (#3, 2) -> (#2, 1) -> (#1, 0)
    // we know root is 0, based on observation above, when a child's position is odd, it flips the digit. So we only need to calculation how many time it flips from root to (#4, 5), in this case:
    //  flip   <-  same   <-  flip   <-  root  .  So answer for (#4, 5) or 1-indexed (#4, 6) is 0        
    public int kthGrammar(int N, int K) {
        int count = 0;
        K--; //make it 0-indexed
        while (K != 0) {
            if (K % 2 == 1) count++;
            K /= 2;
        }
        
        return count % 2;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
