package hmeng.lc.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
127. Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */
public class LC0127 {

    /*
     * two-end BFS, O(n*26^L) L = word length
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        
        Set<String> fset = new HashSet<>(), eset = new HashSet<String>();
        fset.add(beginWord); eset.add(endWord);
        Set<String> visited = new HashSet<>();
        int ans = 1;
        while (!fset.isEmpty() && !eset.isEmpty()) {
            if (fset.size() > eset.size()) {
                Set<String> tmp = eset;
                eset = fset;
                fset = tmp;
            }
            
            Set<String> workingSet = new HashSet<>();
            for (String w : fset) {
                visited.add(w);
                for (int i = 0; i < w.length(); i++) {
                    StringBuilder sb = new StringBuilder(w);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        String newWord = sb.toString();
                        if (eset.contains(newWord)) return ans + 1;
                        if (visited.contains(newWord) || !set.contains(newWord)) continue;
                        workingSet.add(newWord);
                    }
                }
            }
            ans++;
            fset = workingSet;
        }
                
        return 0;
    }
    
    /*
     * one-end BFS, O(n*26^L)
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        int ans = 1;
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                visited.add(word);
                for (int k = 0; k < word.length(); k++) {
                    StringBuilder sb = new StringBuilder(word);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(k, c);
                        String newWord = sb.toString();
                        if (newWord.equals(endWord)) return ans+1;
                        if (visited.contains(newWord) || !set.contains(newWord)) continue;
                        visited.add(newWord);
                        queue.offer(newWord);
                    }
                }
            }
            ans++;
        }
        
        return 0;
    }
    /*
     * Build graph and BFS, O(L*n^2), L = word length. time exceeded.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        String[] words = new String[wordList.size()];
        wordList.toArray(words);
        Map<String, Set<String>> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (hasOneLetterDiff(words[i], beginWord)) {
                queue.offer(words[i]);
                visited.add(words[i]);
            }
            for (int j = i+1; j < words.length; j++) {
                
                if (hasOneLetterDiff(words[i], words[j])) {
                    map.computeIfAbsent(words[i], k -> new HashSet<>()).add(words[j]);
                    map.computeIfAbsent(words[j], k -> new HashSet<>()).add(words[i]);
                }
            }
        }
        System.out.println(map);
        System.out.println(visited);
        if (visited.contains(endWord)) return 2;

        int ans = 2;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String neighber : map.get(word)) {
                    if (neighber.equals(endWord)) return ans;
                    if (!visited.contains(neighber)) {
                        queue.offer(neighber);
                        visited.add(neighber);
                    }
                }
            }

        }
        
        return 0;
    }
    
    private boolean hasOneLetterDiff(String lhs, String rhs) {
        if (lhs.length() != rhs.length()) return false;
        char[] l = lhs.toCharArray();
        char[] r = rhs.toCharArray();
        int diff = 0;
        for (int i = 0; i < l.length; i++) {
            if (l[i] != r[i]) diff++;
        }
        
        return diff == 1;
    }
    
    public static void main(String[] args) {
        LC0127 lc = new LC0127();
        //String beginWord = "hit", endWord = "cog";
        //String[] words = {"hot","dot","dog","lot","log","cog"};
        //String beginWord = "a", endWord = "c";
        //String[] words = {"a", "b", "c"};
        String beginWord = "hot", endWord = "dog";
        String[] words = {"hot", "dog"};

        System.out.println(lc.ladderLength3(beginWord, endWord, Stream.of(words).collect(Collectors.toList())));        
    }

}
