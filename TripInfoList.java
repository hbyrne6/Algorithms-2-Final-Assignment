
//A list of nodes containing the information about all trips with the
//same arrival time as the arrival time associated with this list
public class TripInfoList {
	
	//A node containing all the information of a trip, bar its arrival time
	//as that will be stored by the list it is a part of
	public static class TripInfo {
		int tripID;
		String departureTime;
		int stopID;
		int stopSequence;
		String stopHeadsign;
		int pickupType;
		int dropOffType;
		double distance;
		TripInfo next;
		
		TripInfo()
		{
			
		}
		
		@Override
		public String toString()
		{
			return  "Departure Time: " + departureTime + ".\n" + 
					"Stop ID: " + stopID + ".\n" + 
					"Stop Sequence: " + stopSequence + ".\n" + 
					"Stop Headsign: " + stopHeadsign + ".\n" + 
					"Pickup Type: " + pickupType + ".\n" + 
					"Drop Off Type: " + dropOffType + ".\n" + 
					"Distance since first stop: " + distance + ".\n";
		}
	}
	
	TripInfo start;
	TripInfo sortedStart;
	String arrivalTime;
	
	TripInfoList(int arrivalTimeInteger)
	{
		int hour = (arrivalTimeInteger / 3600);
		int minute = (arrivalTimeInteger % 3600) / 60;
		int second = (arrivalTimeInteger % 3600) % 60;
		arrivalTime = hour + ":" + ((minute < 10) ? "0" : "") + minute
						+ ":" + ((second < 10) ? "0" : "") + second;
	}
	
	public void addTrip(TripInfo newTrip)
	{
		if(start == null)
		{
			start = newTrip;
		}
		else
		{
			TripInfo currentNode = start;
			while(currentNode.next != null)
			{
				currentNode = currentNode.next;
			}
			currentNode.next = newTrip;
		}
	}
	
	public void sort()
    {
		sortedStart = null;
        TripInfo currentTrip = start;
        while (currentTrip != null)
        {
        	TripInfo next = currentTrip.next;
        	sort(currentTrip);
            currentTrip = next;
        }
        start = sortedStart;
    }
	
	private void sort(TripInfo tripToSort)
    {
        if (sortedStart == null || sortedStart.tripID >= tripToSort.tripID)
        {
        	tripToSort.next = sortedStart;
        	sortedStart = tripToSort;
        }
        else
        {
        	TripInfo currentTrip = sortedStart;
            while (currentTrip.next != null && currentTrip.next.tripID < tripToSort.tripID)
            {
            	currentTrip = currentTrip.next;
            }
            tripToSort.next = currentTrip.next;
            currentTrip.next = tripToSort;
        }
    }
	
	@Override
	public String toString()
	{
		String returnString = "";
		TripInfo currentTrip = start;
		while(currentTrip != null)
		{
			returnString += "Trip ID: " + currentTrip.tripID + ".\n" + 
							"Arrival Time: " + arrivalTime + ".\n" +
							currentTrip.toString() + "\n";
			currentTrip = currentTrip.next;
		}
		return returnString;
	}
}
