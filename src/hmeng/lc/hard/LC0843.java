package hmeng.lc.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
843. Guess the Word

This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the 
given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these 
guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen 
independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.

Note:  Any solutions that attempt to circumvent the judge will result in disqualification.

 */
public class LC0843 {

    private static class Master {
        private String secret;
        private int count = 0;
        Master(String secret) {
            this.secret = secret;
        }
        
        int guess(String word) {
            count++;
            int res = 0;
            for (int i = 0; i < secret.length(); i++) {
                if (secret.charAt(i) == word.charAt(i))
                    res++;
            }
            
            return res;
        }
        
        int getCount() {
            return count;
        }
          
    }
    
    private static final Random RANDOM = new Random();
    public void findSecretWord(String[] wordlist, Master master) {
        
        List<String> words = Arrays.asList(wordlist);
        int len = 0;
        while (!words.isEmpty()) {
            String sample = words.get(RANDOM.nextInt(words.size()));
            len = master.guess(sample);
            if (len == 6) return;
            words = filter(sample, len, words);
        }
    }
    
    private List<String> filter(String tar, int len, List<String> src) {
        List<String> res = new ArrayList<>();
        for (String word : src) {
            if (match(tar, word, len)) res.add(word);
        }
        
        return res;
    }
    
    private boolean match(String tar, String word, int len) {
        int match = 0;
        for (int i = 0; i < tar.length(); i++) {
            if (tar.charAt(i) == word.charAt(i)) match++;
        }
        
        return match == len;
    }
    
    public static void main(String[] args) {
        Master master = new Master("ccoyyo");
        String[] wordlist = {"wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt","kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc","kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr","oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi","hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow","yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux","ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm","yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc","bssybb","ccoyyo","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf","chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq"};
        LC0843 lc = new LC0843();
        lc.findSecretWord(wordlist, master);
        System.out.println(master.getCount());
    }

}
