package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
140. Word Break II
DescriptionHintsSubmissionsDiscussSolution

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]


 */
public class LC0140 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, words, memo);
    }
    
    private List<String> dfs(String s, int start, Set<String> words, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) return memo.get(start);
        
        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }
        
        for (int end = start+1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (words.contains(prefix)) {
                List<String> list = dfs(s, end, words, memo);
                for (String tmp : list) {
                    res.add(prefix + (tmp == "" ? "" : " ") + tmp);
                }
            }
        }
        
        memo.put(start, res);
        return res;
    }
    
    public static void main(String[] args) {
        LC0140 lc = new LC0140();
        System.out.println(lc.wordBreak("pineapplepenapple", Arrays.asList("apple","pen","applepen","pine","pineapple")));
    }

}
