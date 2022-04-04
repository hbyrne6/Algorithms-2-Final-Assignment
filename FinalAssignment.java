

public class FinalAssignment {

	public static void main(String[] args) {
		List[] edgeWeightedDigraph = FinalAssignmentFileReader.readStops();
		edgeWeightedDigraph = FinalAssignmentFileReader.readStopTimes(edgeWeightedDigraph);
		edgeWeightedDigraph = FinalAssignmentFileReader.readTransfers(edgeWeightedDigraph);
		TST tst = FinalAssignmentFileReader.readStopNames();
		Queue queue = tst.keysWithPrefix("HASTINGS");
		
		System.out.println("Results of search: ");
		while(queue.start != null)
		{
			String stopName = queue.dequeue().val;
			int stopNumber = (int) tst.get(stopName);
			System.out.println("Stop Name: " + stopName + ", Stop Number: " + stopNumber);
			System.out.println("Stop Information: ");
			System.out.println("" + edgeWeightedDigraph[stopNumber].toString() + "\n");
		}
	}
	
	public static double[] dijkstra(List[] edgeWeightedDigraph, int[] edgeTo, int start)
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
        		List streetsFromIntersection = edgeWeightedDigraph[currentIntersection];
        		List.DirectedEdge currentStreet = streetsFromIntersection.start;
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
    
    public static void relax(double[] distTo, int[] edgeTo, List.DirectedEdge edge)
    {
    	if(distTo[edge.stopTo] > (distTo[edge.stopFrom] + (edge.weight)))
    	{
    		distTo[edge.stopTo] = distTo[edge.stopFrom] + (edge.weight);
    		edgeTo[edge.stopTo] = edge.stopFrom;
    	}
    }

}
