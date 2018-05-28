package hmeng.lc.hard;

/*
839. Similar String Groups
DescriptionHintsSubmissionsDiscussSolution

Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they 
are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of unique strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:

Input: ["tars","rats","arts","star"]
Output: 2

Note:

    A.length <= 2000
    A[i].length <= 1000
    A.length * A[i].length <= 20000
    All words in A consist of lowercase letters only.
    All words in A have the same length and are anagrams of each other.
    The judging time limit has been increased for this question.

 */
public class LC0839 {
    public int numSimilarGroups(String[] A) {
        int[] parent = new int[A.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                if (isSimilar(A[i], A[j])) union(parent, i, j);
            }
        }
        
        int res = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) res++;
        }
        
        return res;
    }
    
    private void union(int[] parent, int i, int j) {
        int pi = find(parent, i), pj = find(parent, j);
        if (pi == pj) return;
        parent[pi] = pj;
    }
    
    private int find(int[] parent, int t) {
        if (t == parent[t]) return t;
        parent[t] = find(parent, parent[t]);
        return parent[t];
    }
    
    private boolean isSimilar(String l, String r) {
        int diffcount = 0;
        for (int i = 0; i < l.length(); i++) {
            if (l.charAt(i) != r.charAt(i) && ++diffcount > 2) return false;
        }
        
        return true;
    }
}
