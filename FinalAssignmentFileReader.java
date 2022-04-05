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
		
		public static TripInfoList[] readStopTimes()
		{
			TripInfoList[] tripInfoList = new TripInfoList[24*60*60];
			for(int i = 0; i < tripInfoList.length;i++)
			{
				tripInfoList[i] = new TripInfoList();
			}
	    	FileReader fileReader;
			try {
				int time = 0;
				int stop = 0;
				int previousTripID = 0;
				int tripID = 0;
				double distance = 0;
				TripInfoList.TripInfo newTripInfo = null;
				fileReader = new FileReader("stop_times.txt");
				Scanner myScanner = new Scanner(fileReader);
				myScanner.useDelimiter(",\\s|:|,|\\n");
				myScanner.nextLine();
				previousTripID = tripID;
        		tripID = myScanner.nextInt();
        		int hour =  myScanner.nextInt();
        		int minute = myScanner.nextInt();
        		int second = myScanner.nextInt();
        		if(hour < 24 && hour > 0)
        		{
        			time = (hour * 60 * 60) + (minute * 60) + second;
        		}
        		else
        		{
        			time = -1;
        		}
        		
        		myScanner.nextInt();
        		myScanner.nextInt();
        		myScanner.nextInt();
        		stop = myScanner.nextInt();
        		
        		newTripInfo = new TripInfoList.TripInfo(tripID, time, stop);
        		myScanner.nextLine();
	        	while(myScanner.hasNext())
				{
        			while(myScanner.hasNext())
    				{
        				previousTripID = tripID;
    	        		tripID = myScanner.nextInt();
    	        		if(previousTripID != tripID)
    	        		{
    	        			break;
    	        		}
        				hour =  myScanner.nextInt();
    	        		minute = myScanner.nextInt();
    	        		second = myScanner.nextInt();
    	        		if(hour < 24 && hour > 0)
    	        		{
    	        			time = (hour * 60 * 60) + (minute * 60) + second;
    	        		}
    	        		else
    	        		{
    	        			time = -1;
    	        		}
    	        		
    	        		myScanner.nextInt();
    	        		myScanner.nextInt();
    	        		myScanner.nextInt();
    	        		stop = myScanner.nextInt();
    	        		
    	        		myScanner.next();
    	        		myScanner.next();
    	        		myScanner.next();
    	        		myScanner.next();
    	        		distance = myScanner.nextDouble();
    	        		
    	        		if(time != -1)
    	        		{
    	        			newTripInfo.endTime = time;
    	        			newTripInfo.totalDistance = distance;
    	        			newTripInfo.stops.addToList(stop);
    	        		}
    				}
        			for(int index = newTripInfo.startTime; index <= newTripInfo.endTime; index++)
        			{
        				tripInfoList[index].addTrip(newTripInfo);
        			}
	        		hour =  myScanner.nextInt();
	        		minute = myScanner.nextInt();
	        		second = myScanner.nextInt();
	        		if(hour < 24 && hour > 0)
	        		{
	        			time = (hour * 60 * 60) + (minute * 60) + second;
	        		}
	        		else
	        		{
	        			time = -1;
	        		}
	        		
	        		myScanner.nextInt();
	        		myScanner.nextInt();
	        		myScanner.nextInt();
	        		stop = myScanner.nextInt();
	        		
	        		newTripInfo = new TripInfoList.TripInfo(tripID, time, stop);
	        		myScanner.nextLine();
				}
				myScanner.close();
			} catch (FileNotFoundException e) {} catch (NullPointerException e) {}
	    	return tripInfoList;
		}
		
		public static StopInfoNode[] readStopInfo(int maxStopNumberPlusOne)
		{
			StopInfoNode[] array = new StopInfoNode[maxStopNumberPlusOne];
	    	FileReader fileReader;
			try {
				//stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station
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
