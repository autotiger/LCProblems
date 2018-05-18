package hmeng.lc.medium;

/*
829. Consecutive Numbers Sum

Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3

Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4

Example 3:

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5

Note: 1 <= N <= 10 ^ 9.
 */
public class LC0829 {

    public int consecutiveNumbersSum(int N) {
        int res = 0;
        N *= 2;
        for (int n = 1; n < Math.sqrt(N); n++) {
            if (N % n == 0 && (N/n - n + 1) % 2 == 0)
                res++;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0829 lc = new LC0829();
        System.out.println(lc.consecutiveNumbersSum(1));
    }

}
