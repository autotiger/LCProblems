package hmeng.lc.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        Node parent = this;
        double val = 1.0;
        String var;
        Node(String var, double val) {
            this.var = var;
            this.val = val;
        }
    }
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Node> uf = new HashMap<>();
        
        for (int i = 0; i < equations.length; i++) {
            String var1 = equations[i][0], var2 = equations[i][1];
            if (!uf.containsKey(var1) && !uf.containsKey(var2)) {
                Node n1 = new Node(var1, values[i]);
                Node n2 = new Node(var2, 1.0);
                uf.put(var1, n1);
                uf.put(var2, n2);
                n1.parent = n2;
            } else if (!uf.containsKey(var2)) {
                Node n1 = uf.get(var1);
                Node n2 = new Node(var2, n1.val/values[i]);
                uf.put(var2, n2);
                n2.parent = n1;
            } else if (!uf.containsKey(var1)) {
                Node n2 = uf.get(var2);
                Node n1 = new Node(var1, values[i] * n2.val);
                uf.put(var1, n1);
                n1.parent = n2;
            } else {
                union(uf, var1, var2, values[i]);
            }
        }
        
        double[] res = new double[queries.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            String var1 = query[0], var2 = query[1];
            if (uf.containsKey(var1) && uf.containsKey(var2) && find(uf, var1) == find(uf, var2)) {
                Node n1 = uf.get(var1), n2 = uf.get(var2);
                res[i] = n1.val / n2.val;
            }
        }
        
        return res;
    }
    
    private boolean union(Map<String, Node> uf, String var1, String var2, double value) {
        Node pn1 = find(uf, var1), pn2 = find(uf, var2);
        if (pn1 == pn2) return false;
        double v = value * uf.get(var2).val / uf.get(var1).val;
        for (Node node : uf.values()) {
            if (find(uf, node.var) == pn1) {
                node.val *= v;
            }
        }
        
        pn1.parent = pn2;
        return true;
    }
    
    private Node find(Map<String, Node> uf, String var) {
        Node node = uf.get(var);
        if (node.parent == node) return node;
        node.parent = find(uf, node.parent.var);
        return node.parent;
    }
    /*
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
    
    */
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
        String[][] equations = {{"a","b"},{"e","f"},{"b","e"}}; 
        double[] values = {3.4,1.4,2.3};
        //String[][] queries = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
        String[][] queries = {{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}};
        System.out.println(Arrays.toString(lc.calcEquation(equations, values, queries)));
    }

}
