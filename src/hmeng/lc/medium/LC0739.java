package hmeng.lc.medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
739. Daily Temperatures
DescriptionHintsSubmissionsDiscussSolution
Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.

For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class LC0739 {

    public int[] dailyTemperatures2(int[] temp) {
        if (temp == null ||  temp.length == 0) return new int[0];
        int[] ans = new int[temp.length];
        
        for (int i = ans.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (ans[j] != 0 && temp[j] <= temp[i]) {
                j = j + ans[j];
            }
                
            if (temp[j] > temp[i]) ans[i] = j - i;
        }
        
        return ans;
    }
    
    /*
     * monotonic stack, O(n)
     */
    public int[] dailyTemperatures(int[] temp) {
        if (temp == null ||  temp.length == 0) return new int[0];
        
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            while (!stack.isEmpty() && temp[stack.peek()] < temp[i]) {
                int index = stack.pop();
                ans[index] = i - index;
            }
            stack.push(i);
        }

        return ans;
    }
    
    public static void main(String[] args) {
        LC0739 lc = new LC0739();
        int[] temp = {89,62,70,58,47,47,46,76,100,70};
        System.out.println(Arrays.toString(lc.dailyTemperatures2(temp)));
    }
}
