package hmeng.lc.easy;

/*
758. Bold Words in String

Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.

*/
public class LC0758 {

    public String boldWords(String[] words, String S) {
        boolean[] pos = new boolean[S.length()];
        for (String word : words) {
            markPostions(S, word, pos);
        }
               
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos.length; i++) {
            if (pos[i] && (i == 0 || !pos[i - 1])) sb.append("<b>");
            sb.append(S.charAt(i));
            if (pos[i] && (i == S.length() - 1 || !pos[i + 1])) sb.append("</b>");
        }
        
        return sb.toString();
    }
    
    private void markPostions(String s, String t, boolean[] pos) {
        int start = 0;
        for (int i = 0; i <= s.length() - t.length(); i++) {
            int index = s.indexOf(t, start);
            if (index == -1) return;
            for (int j = 0; j < t.length(); j++) pos[index+j] = true;
            start++;
        }
    }
    /*
    private static class Range {
        int start;
        int end;
        Range(int s, int e) {
            start = s;
            end = e;
        }
        
        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }
    
    public String boldWords(String[] words, String S) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) continue;
            List<Integer> list = getAllOccurrences(S, word);
            if (!list.isEmpty()) map.put(word, list);
        }
        
    
        List<Range> ranges = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            String w = entry.getKey();
            List<Integer> l = entry.getValue();
            for (Integer i : l) {
                ranges.add(new Range(i.intValue(), i.intValue() + w.length()));
            }
        }
        System.out.println(ranges);
        if (ranges.isEmpty()) return S;
        Collections.sort(ranges, (l, r) -> l.start - r.start);
        List<Range> mranges = new ArrayList<>();
        Range curr = ranges.get(0);
        for (Range range : ranges) {
            if (range.start <= curr.end) curr.end = Math.max(curr.end, range.end);
            else {
                mranges.add(curr);
                curr = range;
            }
        }
        mranges.add(curr);
        
        System.out.println(mranges);
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (Range r : mranges) {
            sb.append(S.substring(start,  r.start));
            sb.append("<b>").append(S.substring(r.start, r.end));
            sb.append("</b>");
            start = r.end;
        }
        if (start < S.length()) sb.append(S.substring(start));
        
        return sb.toString();
    }
    
    private List<Integer> getAllOccurrences(String s, String t) {
        int start = 0;
        List<Integer> res = new ArrayList<>();
        while (start >=0) {
            start = s.indexOf(t, start);
            if (start >= 0) {
                res.add(Integer.valueOf(start));
                start += t.length();
            }
        }
        
        return res;
    }
    */
    public static void main(String[] args) {
        LC0758 lc = new LC0758();
        String[] words = {"e","cab","de","cc","db"};
        String S = "cbccceeead";
        System.out.println(lc.boldWords(words, S));
    }

}
