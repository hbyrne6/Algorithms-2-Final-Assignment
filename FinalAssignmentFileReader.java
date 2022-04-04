import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FinalAssignmentFileReader {
	//Creates an array of lists to create an adjacency list
		//Creates an array as large as the maximum stop number, however,
		//if a stop does not exist, there is no list stored in the array reference
		public static EdgeList[] readStops()
		{
			EdgeList[] array = null;
	    	FileReader fileReaderOne;
	    	FileReader fileReaderTwo;
			try {
				fileReaderOne = new FileReader("stops.txt");
				fileReaderTwo = new FileReader("stops.txt");
				Scanner findMaxScanner = new Scanner(fileReaderOne);
				findMaxScanner.useDelimiter(",|\\n");
				findMaxScanner.nextLine();
				int max = -1;
				while(findMaxScanner.hasNext())
				{
					int nextStopNumber = findMaxScanner.nextInt();
					if(nextStopNumber > max)
					{
						max = nextStopNumber;
					}
					
					findMaxScanner.nextLine();
				}
				findMaxScanner.close();
				System.out.println("Stop is " + max);
				array = new EdgeList[max + 1];
				for(int index = 0; index  <  array.length; index++)
	        	{
	        		array[index] = null;
	        	}
				Scanner myScanner = new Scanner(fileReaderTwo);
				myScanner.useDelimiter(",|\\n");
				myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
	        		array[myScanner.nextInt()] = new EdgeList();
	        		myScanner.nextLine();
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return array;
		}
		
		public static EdgeList[] readTransfers(EdgeList[] edgeWeightedDigraph)
		{
	    	FileReader EWD;
			try {
				EWD = new FileReader("transfers.txt");
				Scanner myScanner = new Scanner(EWD);
				myScanner.useDelimiter(",|\\n");
				myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
	        		int stopFrom = myScanner.nextInt();
	        		int stopTo = myScanner.nextInt();
	        		int transferType = myScanner.nextInt();
	        		if(transferType == 0)
	        		{
	        			edgeWeightedDigraph[stopFrom].addToList(stopFrom, stopTo, 2);
	        			myScanner.next();
	        		}
	        		else
	        		{
	        			edgeWeightedDigraph[stopFrom].addToList(stopFrom, stopTo, myScanner.nextDouble() / 100);
	        		}
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return edgeWeightedDigraph;
		}
		
		public static EdgeList[] readStopTimes(EdgeList[] edgeWeightedDigraph)
		{
	    	FileReader EWD;
			try {
				EWD = new FileReader("stop_times.txt");
				Scanner myScanner = new Scanner(EWD);
				myScanner.useDelimiter(",|\\n");
				for(int i = 0; i < 9; i++)
				{
					myScanner.next();
				}
				int tripID = myScanner.nextInt();
				myScanner.next();
				myScanner.next();
				int stopTo = myScanner.nextInt();
				int lastTripID;
				int stopFrom;
				myScanner.nextLine();
				
	        	while(myScanner.hasNext())
				{
	        		lastTripID = tripID;
	        		stopFrom = stopTo;
	        		tripID = myScanner.nextInt();
					myScanner.next();
					myScanner.next();
					stopTo = myScanner.nextInt();
					myScanner.nextLine();
	        		if(lastTripID == tripID)
	        		{
	        			edgeWeightedDigraph[stopFrom].addToList(stopFrom, stopTo, 1);
	        		}
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return edgeWeightedDigraph;
		}
		
		public static TST readStopNames()
		{
			TST tst = new TST();
	    	FileReader fileReader;
			try {
				fileReader = new FileReader("stops.txt");
				Scanner myScanner = new Scanner(fileReader);
				myScanner.useDelimiter(",|\\n");
				myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
	        		int stopNumber = myScanner.nextInt();
	        		myScanner.next();
	        		
	        		String stopName = myScanner.next();
	        		if(stopName.substring(0, 8).equalsIgnoreCase("FLAGSTOP"))
	        		{
	        			stopName = stopName.substring(9, stopName.length()) + " " + stopName.substring(0, 8);
	        		}
	        		if(stopName.substring(0, 2).equalsIgnoreCase("EB") || stopName.substring(0, 2).equalsIgnoreCase("WB") || stopName.substring(0, 2).equalsIgnoreCase("NB") || stopName.substring(0, 2).equalsIgnoreCase("SB"))
	        		{
	        			stopName = stopName.substring(3, stopName.length()) + " " + stopName.substring(0, 2);
	        		}
	        		myScanner.nextLine();
					tst.put(stopName, stopNumber);
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return tst;
		}
		
		public static StopList[] readStopTimes()
		{
			StopList[] stopList = new StopList[24*60*60];
			for(int i = 0; i < stopList.length;i++)
			{
				stopList[i] = new StopList();
			}
	    	FileReader fileReader;
			try {
				int previousStop = -2;
				int currentStop = -1;
				int nextStop = 0;
				int currentTime = 0;
				int nextTime = 0;
				int previousTrip = -2;
				int currentTrip = -1;
				int nextTrip = 0;
				fileReader = new FileReader("stop_times.txt");
				Scanner myScanner = new Scanner(fileReader);
				myScanner.useDelimiter(",\\s|:|,|\\n");
				myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
	        		
	        		previousTrip = currentTrip;
	        		currentTrip = nextTrip;
	        		nextTrip = myScanner.nextInt();
	        		
	        		currentTime = nextTime;
	        		
	        		int hour =  myScanner.nextInt();
	        		int minute = myScanner.nextInt();
	        		int second = myScanner.nextInt();
	        		if(hour < 24 && hour > 0)
	        		{
	        			nextTime = (hour * 60 * 60) + (minute * 60) + second;
	        		}
	        		else
	        		{
	        			nextTime = -1;
	        		}
	        		
	        		myScanner.nextInt();
	        		myScanner.nextInt();
	        		myScanner.nextInt();
	        		
	        		previousStop = currentStop;
	        		currentStop = nextStop;
	        		nextStop = myScanner.nextInt();
	        		if(currentTime != -1)
	        		{
	        			if(previousTrip == currentTrip && currentTrip == nextTrip)
		        		{
		        			stopList[currentTime].addToList(previousStop, currentStop, nextStop);
		        		}
		        		else if(previousTrip == currentTrip)
		        		{
		        			stopList[currentTime].addToList(previousStop, currentStop, -1);
		        		}
		        		else if(currentTrip == nextTrip)
		        		{
		        			stopList[currentTime].addToList(-1, currentStop, nextStop);
		        		}
	        		}
	        		myScanner.nextLine();
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return stopList;
		}
}
