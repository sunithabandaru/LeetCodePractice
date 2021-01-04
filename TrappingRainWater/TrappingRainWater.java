/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.


Example:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.


Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9

*/

class Solution 
{
    public int trap(int[] height) 
    {
        if(height == null || height.length <2)
            return 0;

        int n = height.length;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        int result = 0;
        int distance = 0, trapped = 0;
        while(current < n)
        {
            while(!stack.isEmpty() && height[current] > height[stack.peek()])
            {
                int top = stack.peek();
                stack.pop();
                if(stack.isEmpty())
                    break;
                distance = current - stack.peek() -1;
                trapped = Math.min(height[current] - height[top], height[stack.peek()] - height[top]);
                result += (trapped * distance);
            }
            
            stack.push(current++);
        }
        
		return result;   
    }
}
