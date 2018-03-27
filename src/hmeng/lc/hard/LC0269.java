package hmeng.lc.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
269. Alien Dictionary
DescriptionHintsSubmissionsDiscussSolution
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

 */
public class LC0269 {

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int[] degree = new int[26];
        int count = 0;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (degree[c - 'a'] == 0) {
                    degree[c - 'a'] = 1;
                    count++;
                }
            }
        }
        
        //make adjacency list for the graph
        Map<Character, Set<Character>> adjList = new HashMap<>();
        for (int i = 0; i < words.length-1; i++) {
            String curr = words[i];
            String next = words[i+1];
            int len = Math.min(curr.length(), next.length());
            for (int k = 0; k < len; k++) {
                char cc = curr.charAt(k);
                char cn = next.charAt(k);
                if (cc == cn) continue;
                if (adjList.computeIfAbsent(cc, key -> new HashSet<>()).add(cn)) degree[cn-'a']++;
                break;
            }
        }

        //use Kahn’s algorithm for Topological Sorting
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (degree[i] == 1) queue.offer((char)(i+'a'));
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            sb.append(c.charValue());
            if (adjList.containsKey(c)) {
                for (Character cc : adjList.get(c)) {
                    if (--degree[cc.charValue()-'a'] == 1) queue.offer(cc);
                }
            }
        }
        
        if (sb.length() == count) return sb.toString();
        else return "";
    }
    
    public static void main(String[] args) {
//        String[] words = {
//                "wrt",
//                "wrf",
//                "er",
//                "ett",
//                "rftt"
//                };
        String[] words = {
                "wrt","wrf","er","ett","rftt","te"
        };
        
        LC0269 lc = new LC0269();
        System.out.println(lc.alienOrder(words));
    }

}
