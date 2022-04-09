import java.util.Scanner;

public class FinalAssignment {
	//Reads all data, and handles the start of user input
	public static void main(String[] args) {
		//Load all data in to graphs, a TST and arrays.
		System.out.println("Loading...");
		EdgeList[] edgeWeightedDigraph = FinalAssignmentFileReader.readStops();
		System.out.print("||");
		edgeWeightedDigraph = FinalAssignmentFileReader.readStopTimes(edgeWeightedDigraph);
		System.out.print("|||");
		edgeWeightedDigraph = FinalAssignmentFileReader.readTransfers(edgeWeightedDigraph);
		System.out.print("||");
		TST tst = FinalAssignmentFileReader.readStopNames();
		System.out.print("|||");
		StopInfoNode[] stopsInfo = FinalAssignmentFileReader.readStopInfo(edgeWeightedDigraph.length);
		System.out.print("||");
		TripInfoList[] tripInfoList = FinalAssignmentFileReader.readTripTimes();
		System.out.println("|||||");
		System.out.println("Loaded");
		//Finished loading data
		
		//Find what functionality the user wants
		Scanner myScanner = new Scanner(System.in);
		while(true)
		{
			System.out.println("\nPlease input the following depending on what information you would"
					+ " like, \n'Shortest Route'\n'Search Stop by Name'\n'Search trip by time'\n'Quit':");
			String input = myScanner.nextLine();
			System.out.println("");
			//Find appropriate function to call considering input, if else used instead
			//of switch to deal with upper and lower case
			if(input.equalsIgnoreCase("Shortest Route"))
			{
				findShortestRoute(edgeWeightedDigraph);
			}
			else if(input.equalsIgnoreCase("Search Stop by Name"))
			{
				findStopName(tst, stopsInfo);
			}
			else if(input.equalsIgnoreCase("Search trip by time"))
			{
				findTripTime(tripInfoList);
			}
			else if(input.equalsIgnoreCase("Quit"))
			{
				break;
			}
			else
			{
				System.out.println("Please select one of the 4 options");
			}
		}
		myScanner.close();
		System.out.println("Thank you for using this program!");
	}
	
	//Runs dijkstra's algorithm, filling in a distTo and edgeTo array
	public static double[] dijkstra(EdgeList[] edgeWeightedDigraph, int[] edgeTo, int start)
    {
    	double[] distTo = new double[edgeWeightedDigraph.length];
    	for(int index = 0; index <  distTo.length; index++)
    	{
    		if(index != start)
    		{
    			distTo[index] = Integer.MAX_VALUE;
    		}
    		else
    		{
    			distTo[index] = 0;
    		}
    		edgeTo[index] = -1;
    	}
    	boolean[] visited = new boolean[edgeWeightedDigraph.length];
    	for(int index = 0; index <  visited.length; index++)
    	{
    		visited[index] = false;
    	}
    	PriorityQueue queue = new PriorityQueue();
    	queue.addStop(start, 0);
    	while(queue.start != null)
    	{
    		int currentStop = queue.removeMin().number;
    		if(!visited[currentStop])
    		{
    			visited[currentStop] = true;
    			EdgeList streetsFromIntersection = edgeWeightedDigraph[currentStop];
    			EdgeList.DirectedEdge currentEdge = streetsFromIntersection.start;
        		while(currentEdge != null)
        		{
        			relax(distTo, edgeTo, currentEdge);
        			queue.addStop(currentEdge.stopTo, distTo[currentEdge.stopTo]);
        			currentEdge = currentEdge.next;
        		}
    		}
    	}
    	return distTo;
    }
    
	//relax function for dijkstra
    public static void relax(double[] distTo, int[] edgeTo, EdgeList.DirectedEdge edge)
    {
    	if(distTo[edge.stopTo] > (distTo[edge.stopFrom] + (edge.weight)))
    	{
    		distTo[edge.stopTo] = distTo[edge.stopFrom] + (edge.weight);
    		edgeTo[edge.stopTo] = edge.stopFrom;
    	}
    }
    
    //Handles input if the user wants to find the shortest route
    public static void findShortestRoute(EdgeList[] edgeWeightedDigraph)
    {
    	Scanner myScanner = new Scanner(System.in);
		while(true)
		{
			System.out.println("What two stop numbers would you like to find the shortest route between?"
					+ " (Please enter the two stop numbers seperated by spaces)"
					+ "\n(Or type menu to return to the menu)");
			if(myScanner.hasNextInt())
			{
				System.out.println("");
				int stopOne = myScanner.nextInt();
				if(edgeWeightedDigraph.length - 1 < stopOne || stopOne < 0)
				{
					System.out.println("The first stop number inputted is not a valid stop, please input a real stop.");
					myScanner.next();
				}
				else if(edgeWeightedDigraph[stopOne] == null)
				{
					System.out.println("The first stop number inputted is not a valid stop, please input a real stop.");
					myScanner.next();
				}
				else if(myScanner.hasNextInt())
				{
					int stopTwo = myScanner.nextInt();
					if(edgeWeightedDigraph.length - 1 < stopTwo || stopTwo < 0)
					{
						System.out.println("The second stop number inputted is not a valid stop, please input a real stop.");
					}
					else if(edgeWeightedDigraph[stopTwo] == null)
					{
						System.out.println("The second stop number inputted is not a valid stop, please input a real stop.");
					}
					else
					{
						if(stopOne == stopTwo)
						{
							System.out.println("No traversal is needed, you are already at the stop.\n");
							myScanner.nextLine();
							continue;
						}
						int[] edgeTo = new int[edgeWeightedDigraph.length];
						double[] distTo = dijkstra(edgeWeightedDigraph, edgeTo, stopOne);
						if(edgeTo[stopTwo] == -1)
						{
							System.out.println("There exists no route between these two stops.\n");
							myScanner.nextLine();
							continue;
						}
						System.out.println("The cost from " + stopOne + " to " + stopTwo + " is " + distTo[stopTwo] + ".");
						int index = stopTwo;
						Stack stopNumbers = new Stack();
						while(edgeTo[index] != -1)
						{
							stopNumbers.push(index);
							index = edgeTo[index];
						}
						System.out.print("The stops en route are: " + stopOne + ", ");
						while(stopNumbers.start != null)
						{
							System.out.print("" + stopNumbers.pop().val);
							if(stopNumbers.start != null)
							{
								System.out.print(", ");
							}
						}
						System.out.println(".\n");
					}
				}
				else
				{
					System.out.println("The second stop number inputted is not a valid stop, please input a number.");
					myScanner.nextLine();
				}
			}
			else if(myScanner.next().equalsIgnoreCase("menu"))
			{
				break;
			}
			else
			{
				System.out.println("");
				System.out.println("The first stop number inputted is not a valid stop, please input a number.");
				myScanner.nextLine();
			}
			
		}
    }
    
    //Handles input if the user wants to find stop information using a
    //stop name
    public static void findStopName(TST tst, StopInfoNode[] stopsInfo)
    {
    	Scanner myScanner = new Scanner(System.in);
		while(true)
		{
			System.out.println("What stop are you looking for, please input its full name, or first couple letters: "
					+ "\n(Or type menu to return to the menu)");
			String input = myScanner.nextLine().toUpperCase();
			System.out.println("");
			if(input.equalsIgnoreCase("menu"))
			{
				break;
			}
			Queue queueOfResults = tst.keysWithPrefix(input);
			if(queueOfResults.start == null)
			{
				System.out.println("There are no stops with this name or starting letters.");
			}
			else
			{
				System.out.println("All stops starting with these letters: ");
				while(queueOfResults.start != null)
				{
					String stopName = queueOfResults.dequeue().val;
					int stopNumber = (int) tst.get(stopName);
					System.out.println("Stop Name: " + stopName + ", Stop Number: " + stopNumber);
					System.out.println("Stop Information: ");
					System.out.println("" + stopsInfo[stopNumber].toString() + "\n");
				}
			}
		}
    }
    
    //Handles input if the user wants to find trip information using an
    //arrival time
    public static void findTripTime(TripInfoList[] tripInfoList)
    {
    	Scanner myScanner = new Scanner(System.in);
    	myScanner.useDelimiter(":|\\n");
		while(true)
		{
			int hour = 0;
			int minute = 0;
			int second = 0;
			System.out.println("What arrival time are you looking for? (Give in format hh:mm:ss)"
					+ "\n(Or type menu to return to the menu)");
			if(myScanner.hasNextInt())
			{
				System.out.println("");
				hour = myScanner.nextInt();
				if(myScanner.hasNextInt())
				{
					minute = myScanner.nextInt();
					try
					{
						String nextInput = myScanner.next();
						if(nextInput.length() - 1 > 2)
						{
							System.out.println("Please input the desired time as hh:mm:ss");
							myScanner.nextLine();
							continue;
						}
						second = Integer.parseInt(nextInput.substring(0,nextInput.length() - 1));
					} catch(NumberFormatException e) {
						System.out.println("Please input the desired time as hh:mm:ss");
						myScanner.nextLine();
						continue;
					}
					if(23 < hour || hour < 0)
					{
						System.out.println("Hour must be between 0 and 23 inclusive");
						myScanner.nextLine();
						continue;
					}
					if(59 < minute || minute < 0)
					{
						System.out.println("Minutes must be between 0 and 59 inclusive");
						myScanner.nextLine();
						continue;
					}
					if(59 < second || second < 0)
					{
						System.out.println("Seconds must be between 0 and 59 inclusive");
						myScanner.nextLine();
						continue;
					}
					System.out.println("At arrival time " + ((hour < 10) ? "0" : "") + hour + ":" 
										+ ((minute < 10) ? "0" : "") + minute + ":" + 
									((second < 10) ? "0" : "") + second + ", these trips will occur:");
					int arrivalTime = (hour*60*60) + (minute*60) + second;
					if(tripInfoList[arrivalTime] == null)
					{
						System.out.println("No trips occur at this time.");
					}
					else
					{
						tripInfoList[arrivalTime].sort();
						System.out.print("" + tripInfoList[arrivalTime].toString());
					}			
				}
				else
				{
					System.out.println("Please input the desired time as hh:mm:ss");
					myScanner.nextLine();
				}
			}
			else if(myScanner.nextLine().equalsIgnoreCase("menu"))
			{
				break;
			}
			else
			{
				System.out.println("");
				System.out.println("Please input the desired time as hh:mm:ss");
			}
		}
		myScanner.reset();
    }

}
