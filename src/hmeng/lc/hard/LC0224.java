package hmeng.lc.hard;

import java.util.Deque;
import java.util.LinkedList;

/*
224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2

Example 2:

Input: " 2-1 + 2 "
Output: 3

Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

Note:

    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.


 */
public class LC0224 {
    Deque<Integer> nStk = new LinkedList<>();
    Deque<Character> opStk = new LinkedList<>();
    
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') continue;
            if (isNumber(chars[i])) {
                int n = chars[i]-'0';
                for (;i+1 < chars.length && isNumber(chars[i+1]);i++) {
                    n = n * 10 + chars[i+1]-'0';
                }
                nStk.push(n);
            } else if (chars[i] == '(') {
                opStk.push('(');
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!opStk.isEmpty() && opStk.peek() != '(') {
                    popStack();
                }
                opStk.push(chars[i]);
            } else if (chars[i] == ')') {
                while (opStk.peek() != '(') {
                    popStack();
                }
                opStk.pop();
            } else {
                throw new IllegalArgumentException("invalid input!");
            }
        }
        
        while (!opStk.isEmpty())
            popStack();
        
        return nStk.peek().intValue();
    }
    
    private void popStack() {
        Integer r = nStk.pop(), l = nStk.pop();
        char op = opStk.pop().charValue();
        if (op == '+') {
            nStk.push(r + l);
        } else {
            nStk.push(l - r);
        }
    }
    
    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
    
    public static void main(String[] args) {
        LC0224 lc = new LC0224();
        System.out.println(lc.calculate(" 2-1 + 2 "));
    }

}
