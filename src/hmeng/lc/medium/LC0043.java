package hmeng.lc.medium;

/*
43. Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"

Note:

    The length of both num1 and num2 is < 110.
    Both num1 and num2 contain only digits 0-9.
    Both num1 and num2 do not contain any leading zero, except the number 0 itself.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class LC0043 {

    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String s = num1;
            num1 = num2;
            num2 = s;
        }
        
        String res = "";
        for (int i = 0; i < num2.length(); i++) {
            String s = multiple(num1, num2.charAt(num2.length()-1-i) - '0', i);
            if (res != "") {
                res = add(res, s);
            } else {
                res = s;
            }
        }
        
        return res;
    }
    
    private String add(String l, String r) {
        if (l.length() < r.length()) {
            String s = l;
            l = r;
            r = s;
        }
        
        char[] ls = l.toCharArray(), rs = r.toCharArray();
        int li = ls.length-1, ri = rs.length-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (li >= 0 || ri >= 0 || carry > 0) {
            int ld = 0, rd = 0;
            if (li >= 0) ld = ls[li--]-'0';
            if (ri >= 0) rd = rs[ri--]-'0';
            int sum = ld + rd + carry;
            int remainder = sum % 10;
            carry = sum / 10;
            sb.append(remainder);
        }
        
        return sb.reverse().toString();
    }
    
    private String multiple(String num, int digit, int padding) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append("0");
        }
        
        char[] chars = num.toCharArray();
        int carry = 0;
        for (int i = 0; i < chars.length; i++) {
            int d = chars[chars.length-1-i]-'0';
            int multi = d * digit + carry;
            int remainder = multi % 10;
            carry = multi / 10;
            sb.append(remainder);
        }
        if (carry != 0) sb.append(carry);
        
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        LC0043 lc = new LC0043();
        System.out.println(lc.multiply("123", "456"));
    }

}
