/*
Convert a non-negative integer num to its English words representation.

 

Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

*/

class Solution 
{
    static private TreeMap<Integer, String> map = new TreeMap<>(){{
        put(0, "Zero");
        put(1, "One");
        put(2, "Two");
        put(3, "Three");
        put(4, "Four");
        put(5, "Five");
        put(6, "Six");
        put(7,"Seven");
        put(8,"Eight");
        put(9,"Nine");
        put(10,"Ten");
        put(11,"Eleven");
        put(12,"Twelve");
        put(13,"Thirteen");
        put(14,"Fourteen");
        put(15,"Fifteen");
        put(16,"Sixteen");
        put(17,"Seventeen");
        put(18,"Eighteen");
        put(19,"Nineteen");
        put(20,"Twenty");
        put(30,"Thirty");
        put(40,"Forty");
        put(50,"Fifty");
        put(60,"Sixty");
        put(70,"Seventy");
        put(80,"Eighty");
        put(90,"Ninety");
        put(100,"Hundred");
        put(1000,"Thousand");
        put(1000000,"Million");
        put(1000000000,"Billion");
    }};
    
    public String numberToWords(int num)
    {
        if(num == 0)
            return "Zero";
        
        StringBuilder output = new StringBuilder();
        while(num > 0)
        {
            int key = map.floorKey(num); //  returns the greatest key less than or equal to given key from the num
            int n = num / key;
            
            if(key < 100 && map.containsKey(key))
                output.append(" ").append(map.get(key));
            
            else if(n < 100 && map.containsKey(n))
                output.append(" ").append(map.get(n)).append(" ").append(map.get(key));
                
            else
                output.append(" ").append(numberToWords(n)).append(" ").append(map.get(key));
            
            num = num % key;
        }
        return String.valueOf(output).trim();
    }
}

/*
Complexity Analysis

Time Complexity : O(N). The output is proportional to the number N of digits in the input.

Space Complexity : O(logN) TreeMap's Complexity for insertion and lookup.

*/