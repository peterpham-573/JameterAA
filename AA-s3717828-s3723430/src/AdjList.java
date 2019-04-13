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
	private Edge[] tempE;

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
    	//String srcCheck = "not";
    	//String tarCheck = "not";
    	//String check = "not";
    	
    	boolean srcCheck = false;
    	boolean tarCheck = false;
    	boolean check = false;
    	
    	//check if vertex exists for source and destination
    	for(int i = 0; i < nodeCount; i++)
    	{
    		if (srcLabel.equalsIgnoreCase(nodes[i].getVertex()))
    		{
    			srcCheck = true;
    			
    		}
    		if (tarLabel.equalsIgnoreCase(nodes[i].getVertex()))
    		{
    			tarCheck = true;
    		}
    	}
        // check if edges exist -- same meaning that edge already exists!
    	if (srcCheck == true && tarCheck == true)
    	{
	    	for (int i = 0; i < edgeCount + 1; i++)
	    	{
	    		if (edges[i] == null)
	    		{
	    			System.out.println("edge was null, check is true");
	    			check = true;
	    		}
	    		else if(edges[i] != null)
	    		{
	    			System.out.println("edge was not null");
		    		if(srcLabel.equalsIgnoreCase(edges[i].getSource()))
		    		{
		    			if(tarLabel.equalsIgnoreCase(edges[i].getTarget()))
		    			{
		    				check = false;
		    	    		System.err.println("Error: edge already exists in the graph.");
		    	    		break;
		    			}
		    			else
		    			{
		    				System.out.println("check is true");
		    				check = true;
		    			}
		    		}
	    		}
	    	}
    	}
    	else
    	{
    		System.err.println("Error: one of the vertices do not exist.");
    	}
    	
    	System.out.println("attempting to add edge");
    	//adding the edge now
    	if (check == true)
    	{
    		System.out.println("adding edge passed");
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
        for (int i = 0; i < edgeCount + 1; i++)
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
    		os.println(nodes[i].getVertex());
    	}
    	
    } // end of printVertices()


    public void printEdges(PrintWriter os) 
    {
        // Implementing printing all edges { PE } -- Order does not matter -- each edge is on a new line
    	
    	for (int i = 0; i < edgeCount; i++)
    	{
    		os.println(edges[i].getSource() + " " + edges[i].getTarget() + " " + edges[i].getWeight() + "\n");
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
