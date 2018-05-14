package hmeng.other;

/*
 * Greatest common divisor of two integers using Euclidean algorithm
 */
public class GCD {

    public int gcd(int l, int r) {
        int dividend = Math.max(l, r);
        int divisor = Math.min(l, r);
        int remainder = dividend % divisor;
        if (remainder == 0) return divisor;
        int answer = 0;
        while (remainder != 0) {
            dividend = divisor;
            divisor = remainder;
            answer = remainder;
            remainder = dividend % divisor;
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
        GCD gcd = new GCD();
        System.out.println(gcd.gcd(1701, 3768));
        System.out.println(gcd.gcd(40, 35));
        
    }

}
