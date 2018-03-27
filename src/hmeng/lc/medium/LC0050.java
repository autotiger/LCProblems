package hmeng.lc.medium;

/*
50. Pow(x, n)

Implement pow(x, n).


Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
*/
public class LC0050 {
    /*
     * convert n into binary form.
     * for instance. 
     *              4       1  
     * n = 5 -> 0   1   0   1
     *             x^4 x^2 x^1
     * when bit == 1, times answer to current product of x, otherwise compute x^2, x^4, x^8 ...
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        long N = n;
        if (N < 0) {
            x = 1/x;
            N = -N;
        }
        
        double ans = 1.0;
        while (N > 0) {
            if ((N & 1) == 1) ans *= x;
            x *= x;
            N >>= 1;
        }
        
        return ans;
    }
}
