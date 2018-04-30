package hmeng.lc.medium;

/*
809. Expressive Words

Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  Here, we have groups,
of adjacent letters that are all the same character, and adjacent characters to the group are different.  A group is extended if
that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.
As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of that string.

For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  Formally, we are allowed
to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of
the group is 3 or more.  Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave
the group extended - ie., at least 3 characters long.

Given a list of query words, return the number of words that are stretchy. 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.

Notes:

    0 <= len(S) <= 100.
    0 <= len(words) <= 100.
    0 <= len(words[i]) <= 100.
    S and all words in words consist only of lowercase letters

 */
public class LC0809 {

    private static class Pair {
        String s;
        int[] freq;
        Pair(String s, int[] freq) {
            this.s = s;
            this.freq = freq;
        }
    }
    
    public int expressiveWords(String S, String[] words) {
        Pair sp = unstrech(S);
        int res = 0;
        for (String w : words) {
            if (isStrechy(sp, unstrech(w))) res++;
        }
        
        return res;
    }
    
    private boolean isStrechy(Pair l, Pair r) {
        if (!l.s.equals(r.s)) return false;
        for (int i = 0; i < l.s.length(); i++) {
            if (l.freq[i] >= 3 && r.freq[i] <= l.freq[i]) continue;
            else if (l.freq[i] != r.freq[i]) {
                 return false;
            }
        }
        
        return true;
    }
    
    private Pair unstrech(String s) {
        char[] chars = s.toCharArray();
        int pre = 0, cur = 0;
        int[] freq = new int[chars.length];
        StringBuilder sb = new StringBuilder();
        while (cur < chars.length) {
            while (cur < chars.length && chars[cur] == chars[pre]) cur++;
            sb.append(chars[pre]);
            freq[sb.length()-1] = cur - pre;
            pre = cur;
        }

        return new Pair(sb.toString(), freq);
    }   
    
    public static void main(String[] args) {
        LC0809 lc = new LC0809();
        String S = "heeellooo";
        String[] words = {"hello", "hi", "helo"};
        System.out.println(lc.expressiveWords(S, words));

    }

}
