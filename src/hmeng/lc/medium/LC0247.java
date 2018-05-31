package hmeng.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
247. Strobogrammatic Number II

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].
 */
public class LC0247 {
    
    public List<String> findStrobogrammatic(int n) {
        return dfs(n, n);
    }
    
    private List<String> dfs(int n, int N) {
        if (n == 0) return Arrays.asList("");
        if (n == 1) return Arrays.asList("0", "1", "8");
        
        List<String> subres = dfs(n-2, N);
        List<String> res = new ArrayList<>();
        for (String s : subres) {
            res.add("6"+s+"9");
            res.add("9"+s+"6");
            res.add("1"+s+"1");
            res.add("8"+s+"8");
            if (n != N) res.add("0"+s+"0");
        }
        
        return res;
    }
}
