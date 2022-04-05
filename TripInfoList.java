

public class TripInfoList {
	
	public static class TripInfo {
		int tripID;
		int startTime;
		int endTime;
		double totalDistance;
		StopList stops;
		TripInfo next;
		
		TripInfo(int tripID, int time, int stop)
		{
			this.tripID = tripID;
			startTime = time;
			endTime = time;
			stops = new StopList();
			stops.addToList(stop);
		}
	}
	
	TripInfo start;
	TripInfo sortedStart;
	
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
}
