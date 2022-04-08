//A queue used to store the results of
//keysWithPrefix from TST.java
public class Queue
{
	Node start = null;
	
	public static class Node {
        public String val;                        
        public Node next;  
    }
	
	Queue()
	{
		
	}
	
	public void enqueue(String string)
	{
		Node newString = new Node();
		newString.val = string;
		Node currentString = start;
		if(currentString == null)
		{
			start = newString;
		}
		else
		{
			while(currentString.next != null )
			{
				currentString = currentString.next;
			}
			currentString.next = newString;
		}		
	}
	
	public Node dequeue()
	{
		if(start == null)
		{
			return null;
		}
		else
		{
			Node temp = start;
			start = start.next;
			return temp;
		}
	}
}