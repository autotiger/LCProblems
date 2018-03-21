package hmeng.lc.easy;

/*
800. Similar RGB Color

In the following, every capital letter represents some hexadecimal digit from 0 to f.

The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example, "#15c" is shorthand for the color "#1155cc".

Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.

Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ"

Example 1:
Input: color = "#09f166"
Output: "#11ee66"
Explanation:  
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.
Note:

- color is a string of length 7.
- color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
- Any answer which has the same (highest) similarity as the best answer will be accepted.
- All inputs and outputs should use lowercase letters, and the output is 7 characters.

 */
public class LC0800 {

    public String similarRGB(String color) {
        StringBuilder sb = new StringBuilder().append("#");
        for (int i = 1; i < color.length(); i += 2) {
            char c1 = color.charAt(i);
            char c2 = color.charAt(i+1);
            sb.append(getSimilarColor(c1, c2));            
        }
        
        return sb.toString();
    }
    
    private String getSimilarColor(char c1, char c2) {
        if (c1 == c2) return String.valueOf(c1) + String.valueOf(c2);
        int i1 = Integer.parseInt(String.valueOf(c1), 16);
        int i2 = Integer.parseInt(String.valueOf(c2), 16);
        if (i1 < i2) {
            if (i2 - i1 > 8) return Integer.toHexString(i1+1) + Integer.toHexString(i1+1);
            return String.valueOf(c1) + String.valueOf(c1);
        } else {
            if (i1 - i2 > 8) return Integer.toHexString(i1-1) + Integer.toHexString(i1-1);
            return String.valueOf(c1) + String.valueOf(c1);
        }
    }
    
    public static void main(String[] args) {
        LC0800 lc = new LC0800();
        System.out.println(lc.similarRGB("#4887d6"));
    }

}
