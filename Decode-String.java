/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
 
 */

class Solution
{
    public String decodeString(String s) 
    {
		if(s == null || s.length() == 0)
			return "";
			
		Stack<Character> stack = new Stack<>();
		int n = s.length();
		for(int i=0; i<n; i++)
		{
			char c = s.charAt(i);
			if(c == ']')
			{
				List<Character> list = new ArrayList<>();
				while(stack.peek() != '[')
					list.add(stack.pop());
				
				stack.pop();
				
				int k = 0;
				int base = 1;
				
				while(!stack.isEmpty() && Character.isDigit(stack.peek()))
				{
					k = k + (stack.pop() - '0') * base;
					base = base * 10;
				}
				while(k > 0)
				{
					for(int j = list.size()-1; j>=0; j--)
					{
						stack.push(list.get(j));
					}
					k--;
				}
				
			}
			else
			{
				stack.push(c);
			}
		}
		char[] result = new char[stack.size()];
		for(int i = result.length-1; i >= 0; i--)
		{
			result[i] = stack.pop();
		}
		return new String (result);
    }
}


/*

Time Complexity: O(maxK*N) - K is the maximum value of K and N is the length of the string.
Space Complexity: O(M+N) - M is the letters from a-z and n is the digits from 0-9.

*/