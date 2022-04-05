

public class FinalAssignment {

	public static void main(String[] args) {
		/*EdgeList[] edgeWeightedDigraph = FinalAssignmentFileReader.readStops();
		edgeWeightedDigraph = FinalAssignmentFileReader.readStopTimes(edgeWeightedDigraph);
		edgeWeightedDigraph = FinalAssignmentFileReader.readTransfers(edgeWeightedDigraph);
		TST tst = FinalAssignmentFileReader.readStopNames();
		StopInfoNode[] stopsInfo = FinalAssignmentFileReader.readStopInfo(edgeWeightedDigraph.length);
		Queue queue = tst.keysWithPrefix("HASTINGS");
		
		System.out.println("Results of search: ");
		while(queue.start != null)
		{
			String stopName = queue.dequeue().val;
			int stopNumber = (int) tst.get(stopName);
			System.out.println("Stop Name: " + stopName + ", Stop Number: " + stopNumber);
			System.out.println("Stop Information: ");
			System.out.println("" + stopsInfo[stopNumber].toString() + "\n");
		}
		
		//StopList[] stopList = FinalAssignmentFileReader.readStopTimes();
		//System.out.println("" + stopList[21360].toString());*/
		
		FinalAssignmentFileReader.readStopTimes();
	}
	
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
    		int currentIntersection = queue.removeMin().number;
    		if(!visited[currentIntersection])
    		{
    			visited[currentIntersection] = true;
    			EdgeList streetsFromIntersection = edgeWeightedDigraph[currentIntersection];
    			EdgeList.DirectedEdge currentStreet = streetsFromIntersection.start;
        		while(currentStreet != null)
        		{
        			relax(distTo, edgeTo, currentStreet);
        			queue.addStop(currentStreet.stopTo, distTo[currentStreet.stopTo]);
        			currentStreet = currentStreet.next;
        		}
    		}
    	}
    	return distTo;
    }
    
    public static void relax(double[] distTo, int[] edgeTo, EdgeList.DirectedEdge edge)
    {
    	if(distTo[edge.stopTo] > (distTo[edge.stopFrom] + (edge.weight)))
    	{
    		distTo[edge.stopTo] = distTo[edge.stopFrom] + (edge.weight);
    		edgeTo[edge.stopTo] = edge.stopFrom;
    	}
    }

}
