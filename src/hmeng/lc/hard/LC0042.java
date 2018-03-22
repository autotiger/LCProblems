package hmeng.lc.hard;

/*
42. Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

https://leetcode.com/static/images/problemset/rainwatertrap.png

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 */
public class LC0042 {

    /* O(n), O(1) solution
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length-1;
        int water = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                water += leftMax - height[left];
                left++;
            } else {
                water += rightMax - height[right];
                right--;
            }
        }
        
        return water;
    }*/
    
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        
        int[] leftMaxSoFar = new int[len];
        int[] rightMaxSoFar = new int[len];      
        leftMaxSoFar[0] = height[0];
        rightMaxSoFar[len-1] = height[len-1];
        
        int leftMax = height[0];
        int rightMax = height[len-1];
        for (int i = 1; i < len; i++) {
            leftMax = Math.max(leftMax, height[i]);
            leftMaxSoFar[i] = leftMax;
            rightMax = Math.max(rightMax, height[len-i-1]);
            rightMaxSoFar[len-i-1] = rightMax;
        }
        
        int result = 0;
        for (int i = 1; i < len-1; i++) {
            result += Math.min(leftMaxSoFar[i], rightMaxSoFar[i]) - height[i];
        }
        
        return result;
    }
    
}
