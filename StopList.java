
public class StopList {
	public class StopInfo {
		public int previousStop;
		public int currentStop;
		public int nextStop;
		public StopInfo next;
		
		StopInfo(int previousStop, int currentStop, int nextStop)
    	{
    		this.previousStop = previousStop;
    		this.currentStop = currentStop;
    		this.nextStop = nextStop;
    		next = null;
    	}
    	
    	public void addNewStopInfo(int previousStop, int currentStop, int nextStop)
    	{
    		next = new StopInfo(previousStop, currentStop, nextStop);
    	}
	}
	
	StopInfo start;
	
	StopList()
	{
		StopInfo start = null;
	}
	
	public void addToList(int previousStop, int currentStop, int nextStop)
	{
		if(start == null)
		{
			start = new StopInfo(previousStop, currentStop, nextStop);
		}
		else
		{
			StopInfo currentNode = start;
			if(currentNode.previousStop == previousStop && currentNode.currentStop == currentStop && currentNode.nextStop == nextStop)
			{
				return;
			}
			while(currentNode.next != null)
			{
				currentNode = currentNode.next;
				if(currentNode.previousStop == previousStop && currentNode.currentStop == currentStop && currentNode.nextStop == nextStop)
				{
					return;
				}
			}
			currentNode.addNewStopInfo(previousStop, currentStop, nextStop);
		}
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		if(start == null)
		{
			return "";
		}
		else
		{
			StopInfo currentStopInfo = start;
			if(currentStopInfo.previousStop == -1)
			{
				returnString += "At this time a bus route starts at " + currentStopInfo.currentStop + 
						" and will go to " + currentStopInfo.nextStop + ".\n";
			}
			else if(currentStopInfo.nextStop == -1)
			{
				returnString += "At this time a bus route finishes at " + currentStopInfo.currentStop + 
						" and came from " + currentStopInfo.previousStop + ".\n";
			}
			else
			{
				returnString += "At this time a bus route arrives at " + currentStopInfo.currentStop + 
						", came from " + currentStopInfo.previousStop + " and will continue to " + currentStopInfo.nextStop + ".\n";
			}
			while(currentStopInfo.next != null)
			{
				currentStopInfo = currentStopInfo.next;
				if(currentStopInfo.previousStop == -1)
				{
					returnString += "At this time a bus route starts at " + currentStopInfo.currentStop + 
							" and will go to " + currentStopInfo.nextStop + ".\n";
				}
				else if(currentStopInfo.nextStop == -1)
				{
					returnString += "At this time a bus route finishes at " + currentStopInfo.currentStop + 
							" and came from " + currentStopInfo.previousStop + ".\n";
				}
				else
				{
					returnString += "At this time a bus route arrives at " + currentStopInfo.currentStop + 
							", came from " + currentStopInfo.previousStop + " and will continue to " + currentStopInfo.nextStop + ".\n";
				}
			}
		}
		return returnString;
	}
}
