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
		
		//Reads in transfers and adds them to the adjacency list
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
		
		//Reads in bus trips and adds them to the adjacency list
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
		
		//Reads in the stop names and adds them to a TST with their stop number
		//as their value
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
		
		//Reads in the trip information and adds them to an array
		//with the index representing their arrival time
		public static TripInfoList[] readTripTimes()
		{
			TripInfoList[] tripInfoList = new TripInfoList[24*60*60];
	    	FileReader fileReader;
			try {
				fileReader = new FileReader("stop_times.txt");
				Scanner myScanner = new Scanner(fileReader);
				myScanner.useDelimiter(",\\s|:|,|\\n");
				myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
	        		int arrivalTime = 0;
	        		TripInfoList.TripInfo newTripInfo = new TripInfoList.TripInfo();
	        		newTripInfo.tripID = myScanner.nextInt();
	        		arrivalTime = (60*60*myScanner.nextInt()) + (60*myScanner.nextInt()) + myScanner.nextInt();
	        		if(0 < arrivalTime && arrivalTime < (24*60*60))
	        		{
	        			newTripInfo.departureTime = myScanner.next() + ":" + myScanner.next() + ":" + myScanner.next();
		        		newTripInfo.stopID = myScanner.nextInt();
		        		newTripInfo.stopSequence = myScanner.nextInt();
		        		String possibleStopHeadsign = myScanner.next();
		        		newTripInfo.stopHeadsign = (possibleStopHeadsign.equalsIgnoreCase("")) ? "None" : possibleStopHeadsign;
		        		newTripInfo.pickupType = myScanner.nextInt();
		        		newTripInfo.dropOffType = myScanner.nextInt();
		        		newTripInfo.distance = (newTripInfo.stopSequence == 1) ? 0.0 : myScanner.nextDouble(); 
		        		if(tripInfoList[arrivalTime] == null)
		        		{
		        			tripInfoList[arrivalTime] = new TripInfoList(arrivalTime);
		        		}
		        		tripInfoList[arrivalTime].addTrip(newTripInfo);
	        		}
	        		else
	        		{
	        			myScanner.nextLine();
	        		}
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return tripInfoList;
		}
		
		//Reads in every stops info into a node, and storing the node in an
		//array index equal to its stop number
		public static StopInfoNode[] readStopInfo(int maxStopNumberPlusOne)
		{
			StopInfoNode[] array = new StopInfoNode[maxStopNumberPlusOne];
	    	FileReader fileReader;
			try {
				fileReader = new FileReader("stops.txt");
				Scanner myScanner = new Scanner(fileReader);
				myScanner.useDelimiter(",|\\n");
				myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
	        		int number = myScanner.nextInt();
	        		String code = myScanner.next();
	        		code = (code.equals(" ")) ? "None" : code;
	        		String name = myScanner.next();
	        		String description = myScanner.next();
	        		double latitude = myScanner.nextDouble();
	        		double longitude = myScanner.nextDouble();
	        		String zone = myScanner.next();
	        		String url = myScanner.next();
	        		url = (url.equals(" ")) ? "None" : url;
	        		String locationType = myScanner.next();
	        		String parentStation = myScanner.next();
	        		parentStation = (parentStation.equals("")) ? "None" : parentStation;
	        		
	        		array[number] = new StopInfoNode(number, code, name, description, latitude, longitude,
	        								zone, url, locationType, parentStation);
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return array;
		}
}
