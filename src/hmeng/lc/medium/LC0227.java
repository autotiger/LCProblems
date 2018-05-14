package hmeng.lc.medium;

import java.util.Deque;
import java.util.LinkedList;

/*
227. Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should 
truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7

Example 2:

Input: " 3/2 "
Output: 1

Example 3:

Input: " 3+5 / 2 "
Output: 5

Note:

    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.


 */
public class LC0227 {

    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        char[] chars = s.toCharArray();
        
        Deque<Integer> stk = new LinkedList<>();
        Deque<Character> opStk = new LinkedList<>();
        int num = 0;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                stk.push(num);
                num = 0;
                if (!opStk.isEmpty()) {
                    if (c == '+' || c == '-' || opStk.peek() == '/' || opStk.peek() == '*') {
                        stk.push(calculateInternal(stk.pop().intValue(), opStk.pop().charValue(), stk.pop().intValue()));
                    }
                }
                
                opStk.push(c);
            }
        }
        stk.push(num);
        while (!opStk.isEmpty()) {
            stk.push(calculateInternal(stk.pop().intValue(), opStk.pop().charValue(), stk.pop().intValue()));  
        }
        
        return stk.peek();
    }
    
    private int calculateInternal(int r, char op, int l) {
        switch(op) {
            case '+':
                return l+r;
            case '-':
                return l-r;
            case '*':
                return l*r;
            case '/':
                return l/r;
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        LC0227 lc = new LC0227();
        System.out.println(lc.calculate("1*2-3/4+5*6-7*8+9/10"));
    }

}
