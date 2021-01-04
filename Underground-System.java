/*Implement the UndergroundSystem class:

1. void checkIn(int id, string stationName, int t)
A customer with a card id equal to id, gets in the station stationName at time t.
A customer can only be checked into one place at a time.

2. void checkOut(int id, string stationName, int t)
A customer with a card id equal to id, gets out from the station stationName at time t.

3. double getAverageTime(string startStation, string endStation)
Returns the average time to travel between the startStation and the endStation.
The average time is computed from all the previous traveling from startStation to endStation that happened directly.
Call to getAverageTime is always valid.
You can assume all calls to checkIn and checkOut methods are consistent. If a customer gets in at time t1 at some station, they get out at time t2 with t2 > t1. All events happen in chronological order.

 

Example 1:

Input
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

Output
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);
undergroundSystem.checkOut(27, "Waterloo", 20);
undergroundSystem.checkOut(32, "Cambridge", 22);

undergroundSystem.getAverageTime("Paradise", "Cambridge");       // return 14.00000. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)

undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.00000. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.00000

undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);
undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 12.00000

*/


class UndergroundSystem 
{
    
    Map<Integer, Pair<String, Integer>> checkinData = new HashMap<>();
    Map<String, Pair<Double, Double>> journeyData = new HashMap<>();

    public UndergroundSystem() 
    {
    }
    
    public void checkIn(int id, String stationName, int t) 
    {
        checkinData.put(id, new Pair(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t)
    {
		// Look up the checkin station and checkin time for this id.
		
        int traveltime = t - checkinData.get(id).getValue();
        String fromStation = checkinData.get(id).getKey();
		
		// Lookup the current travel time data for this route.
		String routeKey = stations(fromStation, stationName);
		Pair<Double, Double> routeValue = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
		double counter = routeValue.getValue();
		double totaltime = routeValue.getKey();
		
		// Update the travel time data with this trip.
        journeyData.put(routeKey, new Pair(totaltime + traveltime, counter + 1));
    }
    
    public double getAverageTime(String startStation, String endStation) 
    {
		 // Lookup how many times this journey has been made, and the total time
		String routeKey = stations(startStation, endStation);
		Pair<Double, Double> stats = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0)); 
        double distance =  stats.getKey();
        double counter = stats.getValue();
		
		 // The average is simply the total divided by the number of trips.
        return (double)(distance / counter);
    }
	
	private static String stations(String fromStation, String toStation)
	{
		return fromStation + "-" + toStation;
	}
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
 
 /*
 
Complexity Analysis

Time complexity : O(1) for all.

checkIn(...): Inserting a key/value pair into a HashMap is an O(1) operation.
checkOut(...): Additionally, getting the corresponding value for a key from a HashMap is also an O(1) operation.
getAverageTime(...): Dividing two numbers is also an O(1) operation.

Space complexity : Space complexity : O(P + S^2), where S is the number of stations on the network, and P is the number of passengers making a journey concurrently during peak time

Since two HashMaps are used,we need to determine the maximum sizes these could become.

	1. Firstly, we'll consider checkInData. This HashMap holds one entry for each passenger who has checkIn(...)ed, but not checkOut(...)ed. Therefore, the maximum size this HashMap could be is the maximum possible number of passengers making a journey at the same time, which we defined to be PP. Therefore, the size of this HashMap is O(P).

	2. Secondly, we need to consider journeyData. This HashMap has one entry for each pair of stations that has had at least one passenger start and end a journey at those stations. Over time, we could reasonably expect every possible pair of the S stations on the network to have an entry in this HashMap, which would be O(S^2).

Seeing as we don't know whether S^2 or P is larger, we need to add these together, giving a total space complexity of O(P + S^2)
*/