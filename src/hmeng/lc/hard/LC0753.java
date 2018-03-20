package hmeng.lc.hard;

import java.util.HashSet;
import java.util.Set;

/*
 * 753. Cracking the Safe
 * 
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * Note:
 * - n will be in the range [1, 4].
 * - k will be in the range [1, 10].
 * - k^n will be at most 4096.
 */
public class LC0753 {

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        //calculate node count
        int nodeCount = (int)Math.pow(k, n);
        //start with first node "00..00"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());
        dfs(sb, visited, nodeCount, n, k);
        
        return sb.toString();
        
    }
    
    private boolean dfs(StringBuilder sb, Set<String> visited, int nodeCount, int n, int k) {
        //return true if all nodes are visited
        if (visited.size() == nodeCount) return true;
        int len = sb.length();
        String prefix = sb.substring(len-n+1, len);
        for (int j = 0; j < k; j++) {
            String node = prefix + j;
            if (visited.contains(node)) continue;
            visited.add(node);
            sb.append(String.valueOf(j));
            if (dfs(sb, visited, nodeCount, n, k)) return true;
            //if visited duplicated node, back out one node
            visited.remove(node);
            sb.deleteCharAt(sb.length()-1);
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        LC0753 lc = new LC0753();
        System.out.println(lc.crackSafe(2, 3));
    }

}
