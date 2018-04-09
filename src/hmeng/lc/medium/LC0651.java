package hmeng.lc.medium;

/*
651. 4 Keys Keyboard

Imagine you have a special keyboard with the following keys:

Key 1: (A): Print one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.
*/

public class LC0651 {
    
    /**
     * use dp. dp[i] = the max # of A's under operation i times.
     * for dp[n], you can either
     * - press n times A to get here,
     * - or based on dp[i]  (i < n), do ctrl+A (1 operation), ctrl+C (i operation), ctrl+V (n-i-2 operation) many times
     * the latter operation will generate dp[i] + (n-i-2) * dp[i] = (n-i-1) * dp[i] A's
     */
    public int maxA(int N) {
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; i++) {
            dp[i] = i;
            for (int j = 0; j < i-1; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i-j-1));
            }
        }
        return dp[N];
    }
}
