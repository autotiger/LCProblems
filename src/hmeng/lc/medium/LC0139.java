package hmeng.lc.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
139. Word Break
DescriptionHintsSubmissionsDiscussSolution

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false


 */
public class LC0139 {
    
    /* DP, O(n^2)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] memo = new boolean[s.length()+1];
        memo[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (memo[j] && dict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        
        return memo[s.length()];
    }
     */
    /*
     * DFS solution, O(n^2)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return wb(s, 0, words, memo);
    }
    
    private boolean wb(String s, int start, Set<String> words, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start].booleanValue();
        
        for (int i = start+1; i <= s.length(); i++) {
            String prefix = s.substring(start, i);
            if (words.contains(prefix) && wb(s, i, words, memo)) {
                return true;
            }
        }
        
        memo[start] = Boolean.FALSE;
        
        return false;
    }
}
