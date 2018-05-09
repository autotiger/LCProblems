package hmeng.lc.medium;

/*
208. Implement Trie (Prefix Tree)
DescriptionHintsSubmissionsDiscussSolution

Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z. 
 */

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

public class LC0208 {
    private static class Node {
        Node[] children = new Node[26];
        boolean isWord = false;
    }
    
    private Node root = new Node();
    
    /** Initialize your data structure here. */
    public LC0208() {
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null) return;
        char[] chars = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = chars[i] - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Node();
            }
            cur = cur.children[index];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node res = searchInternal(word);
        return res != null && res.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchInternal(prefix) != null;
    }
    
    private Node searchInternal(String word) {
        if (word == null) return null;
        char[] chars = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = chars[i] - 'a';
            if (cur.children[index] == null) return null;
            cur = cur.children[index];
        }
        
        return cur;
    }
}
