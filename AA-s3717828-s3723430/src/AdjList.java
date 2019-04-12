import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class AdjList extends AbstractAssocGraph
{
	private int nodeCount = 0;
	private int tempCount;
	private int edgeCount = 0;
	private Node[] nodes = new Node[100];
	private Node[] temp;
	private Edge[] edges = new Edge[100];

    /**
	 * Contructs empty graph.
	 */
    public AdjList() 
    {
    	//empty    	
    } // end of AdjList()


    public void addVertex(String vertLabel) 
    {   	
    	String check = "not";
    	
    	//check if vertex exists -- same meaning that the vertex already exists!
    	for(int i = 0; i < nodeCount; i++)
    	{
    		if(vertLabel.equalsIgnoreCase(nodes[i].getVertex()))
    		{
    			check = "same";
    		}
    	}
    	
    	if (check.equalsIgnoreCase("same"))
    	{
    		System.err.println("Error: Vertex already exists.");
    	}
    	else
    	{
    		Node addN = new Node(vertLabel);
    		nodes[nodeCount] = addN;
    		nodeCount++;
    	}
    	
    } // end of addVertex()

    public void addEdge(String srcLabel, String tarLabel, int weight) 
    {
    	String srcCheck = "not";
    	String tarCheck = "not";
    	String check = "not";
    	
    	//check if vertex exists for source and destination
    	for(int i = 0; i < nodeCount - 1; i++)
    	{
    		if (srcLabel.equalsIgnoreCase(nodes[i].getVertex()))
    		{
    			srcCheck = "found";
    		}
    		
    		if (tarLabel.equalsIgnoreCase(nodes[i].getVertex()))
    		{
    			tarCheck = "found";
    		}
    	}
    	
        // check if edges exist -- same meaning that edge already exists!
    	if (srcCheck.equalsIgnoreCase("found") || tarCheck.equalsIgnoreCase("found"))
    	{
	    	for(int i = 0; i < edgeCount; i++)
	    	{
	    		if(srcLabel.equalsIgnoreCase(edges[i].getSource()))
	    		{
	    			if(tarLabel.equalsIgnoreCase(edges[i].getTarget()))
	    			{
	    				check = "same";
	    			}
	    		}
	    	}
    	}
    	else
    	{
    		System.err.println("Error: One of the vertices do not exist.");
    	}
    	
    	//adding the edge now
    	if (check.equalsIgnoreCase("same"))
    	{
    		System.err.println("Error: edge already exists in the graph.");
    	}
    	else
    	{
    		Edge e = new Edge(srcLabel, tarLabel, weight);
    		edges[edgeCount] = e;
    		edgeCount++;
    	}
    } // end of addEdge()


    public int getEdgeWeight(String srcLabel, String tarLabel) 
    {
		    // Implement me!

		    // update return value
		    return EDGE_NOT_EXIST;
    } // end of existEdge()


    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) 
    {
    	boolean check = false;
    	//find the edge
        for (int i = 0; i < edgeCount - 1; i++)
        {
        	if(srcLabel.equalsIgnoreCase(edges[i].getSource()))
        	{
            	if(tarLabel.equalsIgnoreCase(edges[i].getTarget()))
            	{
            		edges[i].setWeight(weight);
            		check = true;
            	}
        	}
        }
        if (check == false)
        {
        	System.err.println("Error: one of the vertices do not exist");
        }
    } // end of updateWeightEdge()


    public void removeVertex(String vertLabel) 
    {
        for(int i = 0; i < nodeCount; i++)
        {
        	if(vertLabel.equalsIgnoreCase(nodes[i].getVertex()))
        	{
        		nodes[i] = null;
        	}
        }
    	
        //makes a new temporary list, adds all good nodes to temp list, then copy temp list back to node list
    	temp = new Node[100];
    	tempCount = 0;
    	for (int i = 0; i < nodeCount; i++)
    	{
    		if(nodes[i] != null)
    		{
    			temp[tempCount] = nodes[i];
    			tempCount++;
    		}
    	}
    	nodes = new Node[100];
    	nodes = temp;
    	nodeCount--;
    } // end of removeVertex()


    public List<MyPair> inNearestNeighbours(int k, String vertLabel) 
    {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!
        
        
        
        
        
        
        

        return neighbours;
    } // end of inNearestNeighbours()


    public List<MyPair> outNearestNeighbours(int k, String vertLabel) 
    {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        
        
        
        return neighbours;
    } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os) 
    {
        // Implementing printing all vertices { PV } -- Order does not matter
    	for (int i = 0; i < nodeCount; i++)
    	{
    		os.append(nodes[i] + " ");
    	}
    	
    } // end of printVertices()


    public void printEdges(PrintWriter os) 
    {
        // Implementing printing all edges { PE } -- Order does not matter -- each edge is on a new line
    	
    	for (int i = 0; i < edgeCount; i++)
    	{
    		os.append(edges[i].getSource() + " " + edges[i].getTarget() + " " + edges[i].getWeight() + "\n");
    	}   	
    } // end of printEdges()

    //Created class of edge
    protected class Edge
    {
    	private String src;
    	private String dest;
    	private int weight;
    	
    	public Edge(String srcLabel, String targetLabel, int weight)
    	{
    		this.src = srcLabel;
    		this.dest = targetLabel;
    		this.weight = weight;
    	}
    	
    	public String getSource()
    	{
    		return src;
    	}
    	
    	public String getTarget()
    	{
    		return dest;
    	}
    	
    	public int getWeight()
    	{
    		return weight;
    	}
    	
    	public void setWeight(int weight)
    	{
    		this.weight = weight;
    	}
    }
    
    protected class Node 
    {
    	private String vertL;
    	
    	public Node(String vertLabel) 
    	{
    			this.vertL = vertLabel;
    	}

    	public String getVertex()
    	{
    		return vertL;
    	}
    }
} // end of class AdjList
