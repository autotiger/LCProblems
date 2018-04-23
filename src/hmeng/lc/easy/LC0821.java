package hmeng.lc.easy;

import java.util.Arrays;

/*
821. Shortest Distance to a Character

Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 

Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.

 */
public class LC0821 {

    public int[] shortestToChar(String S, char C) {
        if (S == null || S.length() == 0) return new int[] {};
        int[] res = new int[S.length()];
        char[] cs = S.toCharArray();
        int pos = -S.length();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == C) pos = i;
            res[i] = i - pos;
        }

        pos = 2 * S.length();
        for (int i = cs.length-1; i >= 0; i--) {
            if (cs[i] == C) pos = i;
            res[i] = Math.min(pos - i, res[i]);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        LC0821 lc = new LC0821();
        String S = "loveleetcode";
        char C = 'e';
        System.out.println(Arrays.toString(lc.shortestToChar(S, C)));

    }

}
