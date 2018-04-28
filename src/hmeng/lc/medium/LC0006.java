package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
6. ZigZag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I

 */
public class LC0006 {

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return "";
        if (numRows == 1) return s;
        
        Map<Integer, List<Character>> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int start = -1, d = 1;
        for (int i = 0; i < chars.length; i++) {
            if(i == numRows || ((i - numRows) > 0 && (i - numRows) % (numRows - 1)  == 0)) d = -d;
            start += d;
            List<Character> list = map.get(start);
            if (list == null) {
                list = new ArrayList<>();
                map.put(start, list);
            }
            list.add(chars[i]);
            
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.size(); i++) {
            List<Character> list = map.get(i);
            for (Character c : list) sb.append(c);
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
       LC0006 lc = new LC0006();
       System.out.println(lc.convert("PAYPALISHIRING", 3));
    }

}
