
public class StopList {
	
	public class StopNumberNode
	{
		int stopNumber;
		StopNumberNode next;
		
		StopNumberNode(int stopNumber)
		{
			this.stopNumber = stopNumber;
		}
	}
	
	StopNumberNode start;
	int stopNumber = 0;
	
	StopList()
	{
		StopNumberNode start = null;
	}
	
	public void addToList(int stopNumber)
	{
		if(start == null)
		{
			start = new StopNumberNode(stopNumber);
		}
		else
		{
			StopNumberNode currentNode = start;
			while(currentNode.next != null)
			{
				currentNode = currentNode.next;
			}
			currentNode.next = new StopNumberNode(stopNumber);
		}
	}
}
