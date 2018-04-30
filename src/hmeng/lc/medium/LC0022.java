package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.List;

/*
22. Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

 */
public class LC0022 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        
        generate(n, 0, 0, res, new StringBuilder());
        
        return res;
    }
    
    private void generate(int n, int left, int right, List<String> res, StringBuilder sb) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        
        if (left < n) {
            generate(n, left+1, right, res, sb.append("("));
            sb.deleteCharAt(sb.length()-1);
        }
        if (right < left) {
            generate(n, left, right+1, res, sb.append(")"));
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
