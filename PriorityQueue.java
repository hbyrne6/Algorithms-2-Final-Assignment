
public class PriorityQueue {
	public static class Stop
	{
		int number;
		double distance;
		Stop next;
		
		Stop(int number, double distance)
		{
			this.number = number;
			this.distance = distance;
		}
		
		public void add(Stop next)
		{
			this.next = next;
		}
	}
	
	Stop start;
	
	PriorityQueue()
	{
		
	}
	
	public void addStop(int number, double distance)
	{
		Stop newStop = new Stop(number, distance);
		if(start == null)
		{
			start = newStop;
		}
		else
		{
			if(start.distance > newStop.distance)
			{
				newStop.next = start;
				start = newStop;
			}
			else if(start.next == null)
			{
				start.next = newStop;
			}
			else
			{
				boolean swapped = false;
				Stop currentStop = start;
				while(currentStop.next != null )
				{
					if((currentStop.next).distance > newStop.distance)
					{
						newStop.next = currentStop.next;
						currentStop.next = newStop;
						swapped = true;
						break;
					}
					currentStop = currentStop.next;
				}
				if(swapped == false)
				{
					currentStop.next = newStop;
				}
			}
		}
	}
	
	public Stop removeMin()
	{
		if(start == null)
		{
			return null;
		}
		else
		{
			Stop temp = start;
			start = start.next;
			return temp;
		}
	}
}
