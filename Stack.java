
//Used to store stop numbers for
//returning the list of stops that
//must be traversed to get from one
//stop to another with the least
//cost.
public class Stack
{
	Node start = null;
	
	public static class Node {
        public int val;                        
        public Node next;  
    }
	
	Stack()
	{
		
	}
	
	public void push(int value)
	{
		Node newNode = new Node();
		newNode.val = value;
		Node currentString = start;
		if(currentString == null)
		{
			start = newNode;
		}
		else
		{
			Node temp = start;
			start = newNode;
			newNode.next = temp;
		}		
	}
	
	public Node pop()
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
