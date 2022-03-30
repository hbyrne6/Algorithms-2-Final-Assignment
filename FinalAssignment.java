

public class FinalAssignment {

	public static void main(String[] args) {
		List[] edgeWeightedDigraph = FinalAssignmentFileReader.readStops();
		edgeWeightedDigraph = FinalAssignmentFileReader.readTransfers(edgeWeightedDigraph);
		edgeWeightedDigraph = FinalAssignmentFileReader.readStopTimes(edgeWeightedDigraph);
		System.out.println("" + edgeWeightedDigraph[646].toString());
		System.out.println("" + edgeWeightedDigraph[647].toString());
		System.out.println("" + edgeWeightedDigraph[381].toString());
		/*for(int i = 0; i < edgeWeightedDigraph.length; i++)
		{
			if(edgeWeightedDigraph[i] != null)
			{
				System.out.println("" + edgeWeightedDigraph[i].toString());
			}
		}*/
		int[] edgeTo = new int[edgeWeightedDigraph.length];
		double[] distTo = dijkstra(edgeWeightedDigraph, edgeTo, 646);
		System.out.println("The cost from 646 to 1269 is " + distTo[1269] + ".");
		int index = 1269;
		System.out.print("The stops en route are: " + index + ", ");
		while(edgeTo[index] != -1)
		{
			System.out.print("" + edgeTo[index]);
			index = edgeTo[index];
			if(edgeTo[index] == -1)
			{
				System.out.println(".");
			}
			else
			{
				System.out.print(", ");
			}
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
