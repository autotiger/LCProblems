package hmeng.lc.medium;

/*
5. Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
 
Example:

Input: "cbbd"

Output: "bb"
 */
public class LC0005 {
    
    /*
    Solution 1: expand from center
    private String res = "";
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i+1);
        }
        
        return res;
    }
    
    private void helper(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        
        String tmp = s.substring(start+1, end);
        if (res.length() < tmp.length()) res = tmp;
    } */
    
    /*
     * Solution 2: dynamic programming
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (s == null || len == 0) return s;
        //dp[i][j] means s.substring(i, j+1) is palindrome or not
        //dp[i][j] == true when s(i) == s(j) and
        //  j - i == 0 -> i, j is pointing to the same character, e.g. "a"
        //  j - i == 1 -> i, j are adjacent, e.g. "aa"
        //  j - i == 2 -> there is only one char between i, j, e.g. 
        // or dp[i+1][j-1] is palindrome
        String res = "";
        boolean dp[][] = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1]);
                if (dp[i][j]) {
                    if (res.length() < j - i + 1) res = s.substring(i,  j+1);
                }
            }
        }

        return res;
    }
    public static void main(String[] args) {
        LC0005 lc = new LC0005();
        System.out.println(lc.longestPalindrome("a"));
    }

}
