import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FinalAssignmentFileReader {
	//Creates an array of lists to create an adjacency list
		//Creates an array as large as the maximum stop number, however,
		//if a stop does not exist, there is no list stored in the array reference
		public static List[] readStops()
		{
			List[] array = null;
	    	FileReader fileReaderOne;
	    	FileReader fileReaderTwo;
			try {
				fileReaderOne = new FileReader("stops.txt");
				fileReaderTwo = new FileReader("stops.txt");
				Scanner findMaxScanner = new Scanner(fileReaderOne);
				findMaxScanner.useDelimiter(",|\\n");
				for(int i = 0; i < 10; i++)
				{
					findMaxScanner.next();
				}
				int max = -1;
				while(findMaxScanner.hasNext())
				{
					int nextStopNumber = findMaxScanner.nextInt();
					if(nextStopNumber > max)
					{
						max = nextStopNumber;
					}
					
					for(int i = 0; i < 9; i++)
					{
						findMaxScanner.next();
					}
				}
				findMaxScanner.close();
				System.out.println("Stop is " + max);
				array = new List[max + 1];
				for(int index = 0; index  <  array.length; index++)
	        	{
	        		array[index] = null;
	        	}
				Scanner myScanner = new Scanner(fileReaderTwo);
				myScanner.useDelimiter(",|\\n");
				for(int i = 0; i < 10; i++)
				{
					myScanner.next();
				}
	        	while(myScanner.hasNext())
				{
	        		array[myScanner.nextInt()] = new List();
					for(int i = 0; i < 9; i++)
					{
						myScanner.next();
					}
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return array;
		}
		
		public static List[] readTransfers(List[] edgeWeightedDigraph)
		{
	    	FileReader EWD;
			try {
				EWD = new FileReader("transfers.txt");
				Scanner myScanner = new Scanner(EWD);
				myScanner.useDelimiter(",|\\n");
				for(int i = 0; i < 4; i++)
				{
					myScanner.next();
				}
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
		
		public static List[] readStopTimes(List[] edgeWeightedDigraph)
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
				for(int i = 0; i < 5; i++)
				{
					myScanner.next();
				}
				
	        	while(myScanner.hasNext())
				{
	        		lastTripID = tripID;
	        		stopFrom = stopTo;
	        		tripID = myScanner.nextInt();
					myScanner.next();
					myScanner.next();
					stopTo = myScanner.nextInt();
					for(int i = 0; i < 5; i++)
					{
						myScanner.next();
					}
	        		if(lastTripID == tripID)
	        		{
	        			edgeWeightedDigraph[stopFrom].addToList(stopFrom, stopTo, 1);
	        		}
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return edgeWeightedDigraph;
		}
}
