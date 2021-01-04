/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

*/
class Solution 
{
	class IntervalComparator implements Comparator<int[]>
	{
		// sort the given intervals[] by start-time
		public int compare(int[] a, int[] b)
		{
			return (a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1);
		}
	}

    public int minMeetingRooms(int[][] intervals)
    {
		 // Check for the base case. If there are no intervals, return 0
		if (intervals.length == 0) 
		{
			return 0;
		}
		
		Collections.sort(Arrays.asList(intervals), new IntervalComparator()); 
        PriorityQueue<Integer> room = new PriorityQueue<>();
		
		for(int[] interval : intervals)
		{
			if(!(room.isEmpty()) && room.peek() <= interval[0])
			{
				room.remove();
			}
			room.add(interval[1]);
		}
		return room.size();
    }
}

/*
Time Complexity: O(NlogN + NlogN) which is O(NlogN)

NlogN - to sort the given intervals. Traverse through the given intervals(N) and extracting min-heap from the priority queue is logN

Where N number of Intervals


Space Complexity: O(N) because we construct the min-heap and that can contain N elements in the worst case 
*/