
public class List {
	public static class DirectedEdge
    {
    	public int stopFrom;
    	public int stopTo;
    	public double weight;
    	public DirectedEdge next;
    	
    	DirectedEdge(int stopFrom, int stopTo, double weight)
    	{
    		this.stopFrom = stopFrom;
    		this.stopTo = stopTo;
    		this.weight = weight;
    		next = null;
    	}
    	
    	public void addNewEdge(int stopFrom, int stopTo, double weight)
    	{
    		next = new DirectedEdge(stopFrom, stopTo,weight);
    	}
    }
	
	DirectedEdge start;
	
	List()
	{
		DirectedEdge start = null;
	}
	
	public void addToList(int stopFrom, int stopTo, double weight)
	{
		if(start == null)
		{
			start = new DirectedEdge(stopFrom,stopTo,weight);
		}
		else
		{
			DirectedEdge currentNode = start;
			if(currentNode.stopFrom == stopFrom && currentNode.stopTo == stopTo)
			{
				return;
			}
			while(currentNode.next != null)
			{
				currentNode = currentNode.next;
				if(currentNode.stopFrom == stopFrom && currentNode.stopTo == stopTo && currentNode.weight == weight)
				{
					return;
				}
			}
			currentNode.addNewEdge(stopFrom,stopTo, weight);
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
			DirectedEdge currentEdge = start;
			returnString += "From " + currentEdge.stopFrom + " to " + currentEdge.stopTo + " as a";
			if(currentEdge.weight == 1)
			{
				returnString += " bus journey one stop away.\n";
			}
			else if(currentEdge.weight == 2)
			{
				returnString += "n instantaneous transfer.\n";
			}
			else
			{
				returnString += " transfer of length " + currentEdge.weight + ".\n";
			}
			while(currentEdge.next != null)
			{
				currentEdge = currentEdge.next;
				returnString += "From " + currentEdge.stopFrom + " to " + currentEdge.stopTo + " as a";
				if(currentEdge.weight == 1)
				{
					returnString += " bus journey one stop away.\n";
				}
				else if(currentEdge.weight == 2)
				{
					returnString += "n instantaneous transfer.\n";
				}
				else
				{
					returnString += " transfer of length " + currentEdge.weight + ".\n";
				}
			}
		}
		return returnString;
	}
}
