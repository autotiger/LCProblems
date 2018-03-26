package hmeng.lc.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
288. Unique Word Abbreviation
DescriptionHintsSubmissionsDiscussSolution
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/
public class LC0288 {
    Map<String, Set<String>> map = new HashMap<>();
    
    public LC0288(String[] dictionary) {
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            map.computeIfAbsent(abbr, key -> new HashSet<>()).add(word);
        }
    }
    
    private String getAbbr(String word) {
        if (word.length() <= 2) return word;
        return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length()-1);
    }
    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (map.containsKey(abbr)) {
            return map.get(abbr).size() == 1 && map.get(abbr).contains(word);
        }
        
        return true;
    }
}


/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new LC0288(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */