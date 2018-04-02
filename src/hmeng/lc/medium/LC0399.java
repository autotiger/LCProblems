package hmeng.lc.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
399. Evaluate Division

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/
public class LC0399 {

    private static class Node {
        String key;
        double val;
        Node(String key, double val) {
            this.key = key;
            this.val = val;
        }
    }
    private Map<String, Set<Node>> adj = new HashMap<>();
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        for (int i = 0; i < equations.length; i++) {
            String v1 = equations[i][0];
            String v2 = equations[i][1];
            adj.computeIfAbsent(v1, key -> new HashSet<>()).add(new Node(v2, values[i]));
            adj.computeIfAbsent(v2, key -> new HashSet<>()).add(new Node(v1, 1/values[i]));
        }
        
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = dfs(queries[i][0], queries[i][1], 1.0, new HashSet<>());
        }
        
        return result;
    }
    
    private double dfs(String start, String end, double value, Set<String> visited) {
        if (visited.contains(start) || !adj.containsKey(start)) return -1.0;
        if (start.equals(end)) return value;
        
        visited.add(start);
        for (Node node : adj.get(start)) {
            double res = dfs(node.key, end, value * node.val, visited);
            if (res != -1.0) return res;
        }
        visited.remove(start);
        return -1.0;
    }
/*    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i < equations.length; i++) {
            String v1 = equations[i][0];
            String v2 = equations[i][1];
            if (!map.containsKey(v1)) map.put(v1, Integer.valueOf(index++));
            if (!map.containsKey(v2)) map.put(v2, Integer.valueOf(index++));
        }
        
        int size = map.size();
        double[][] matrix = new double[size][size];
        Map<String, Set<String>> adj = new HashMap<>();
        for (int i = 0; i < size; i++) matrix[i][i] = 1.0;
        for (int i = 0; i < equations.length; i++) {
            String v1 = equations[i][0];
            String v2 = equations[i][1];
            int row = map.get(v1).intValue();
            int col = map.get(v2).intValue();
            matrix[row][col] = values[i];
            matrix[col][row] = 1 / values[i];
            adj.computeIfAbsent(v1, key -> new HashSet<>()).add(v2);
            adj.computeIfAbsent(v2, key -> new HashSet<>()).add(v1);
        }
        System.out.println(adj);
        
        double[] result = new double[queries.length];
        for (int j = 0; j < queries.length; j++) {
            String v1 = queries[j][0], v2 = queries[j][1];
            List<String> path = getPath(v1, v2, adj, map);
            System.out.println(path);
            if (path.isEmpty()) {
                result[j] = -1.0;
                continue;
            }
            result[j] = 1.0;
            for (int k = 0; k < path.size()-1; k++) {
                int row = map.get(path.get(k));
                int col = map.get(path.get(k+1));
                result[j] *= matrix[row][col];
            }
        }
        
        return result;
    }
    
    private List<String> getPath(String s, String e, Map<String, Set<String>> adj, Map<String, Integer> map) {
        List<String> result = new ArrayList<>();
        if (!map.containsKey(s) || !map.containsKey(e)) return result;
        if (s.equals(e)) {
            result.add(s);
            result.add(e);
            return result;
        }
        result.add(s);
        if (!dfs(s, e, adj, result)) return new ArrayList<>();
        
        return result;
    }
    
    private boolean dfs(String s, String e, Map<String, Set<String>> adj, List<String> result) {
        Set<String> children = adj.get(s);
        if (children == null) return false;
        if (children.contains(e)) {
            result.add(e);
            return true;
        }
        for (String child : children) {
            if (result.contains(child)) continue;
            result.add(child);
            if (dfs(child, e, adj, result)) return true;
            result.remove(result.size()-1);
        }
        
        return false;
    }
*/    
    public static void main(String[] args) {
        LC0399 lc = new LC0399();
        String[][] equations = { {"x1","x2"},{"x2","x3"}, {"x3","x4"}, {"x4","x5"}};
        double[] values = {3.0, 4.0, 5.0, 6.0};
        //String[][] queries = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
        String[][] queries = {{"x2","x4"}};
        System.out.println(Arrays.toString(lc.calcEquation(equations, values, queries)));
    }

}
