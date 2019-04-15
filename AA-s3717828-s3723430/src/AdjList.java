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
    		System.err.println("Warning: Vertex already exists.");
    	}
    	else
    	{
    		Node addN = new Node(vertLabel);
    		nodes[nodeCount] = addN;
    		nodeCount++;
    		System.out.println("Vertex added.");
    	}
    	
    } // end of addVertex()

    public void addEdge(String srcLabel, String tarLabel, int weight) 
    {
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
	    			check = true;
	    			break;
	    		}
	    		else if(edges[i] != null)
	    		{
		    		if(srcLabel.equalsIgnoreCase(edges[i].getSource()))
		    		{
		    			if(tarLabel.equalsIgnoreCase(edges[i].getTarget()))
		    			{
		    				check = false;
		    	    		System.err.println("Warning: edge already exists in the graph.");
		    	    		break;
		    			}
		    			else
		    			{
		    				check = true;
		    			}
		    		}
	    		}
	    	}
    	}
    	else
    	{
    		System.err.println("Warning: one of the vertices do not exist.");
    	}
    	//adding the edge now
    	if (check == true)
    	{
    		System.out.println("Edge added.");
    		Edge e = new Edge(srcLabel, tarLabel, weight);
    		edges[edgeCount] = e;
    		edgeCount++;
    	}
    } // end of addEdge()

    public int getEdgeWeight(String srcLabel, String tarLabel) 
    {
    	int get = -1;
    	boolean exists = false;
    	
    	for (int i = 0; i < edgeCount + 1; i++)
    	{
    		if (edges[i] != null)
    		{
    			if (srcLabel.equalsIgnoreCase(edges[i].getSource()))
    			{
    				if (tarLabel.equalsIgnoreCase(edges[i].getTarget()))
    				{
    					get = i;
    					exists = true;
    					break;
    				}
    			}
    		}
    		
    	}
    	
    	if (exists == true)
    	{
    		return edges[get].getWeight();
    	}
    	else
    	{
		    return EDGE_NOT_EXIST;
    	}
    } // end of existEdge()

    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) 
    {
    	boolean check = false;
    	//find the edge
        for (int i = 0; i < edgeCount + 1; i++)
        {
        	if(srcLabel.equalsIgnoreCase(edges[i].getSource()))
        	{
        		if (edges[i] != null)
        		{
	            	if(tarLabel.equalsIgnoreCase(edges[i].getTarget()))
	            	{
	            		edges[i].setWeight(weight);
	            		check = true;
	            		break;
	            	}
        		}
        	}
        }
        if (check == false)
        {
        	System.err.println("Warning: one of the vertices do not exist");
        }
    } // end of updateWeightEdge()

    public void removeVertex(String vertLabel) 
    {
    	boolean check = false;
    	/*REMOVEING ALL EDGES RELATED TO VERTEX*/
    	for (int i = 0; i < edgeCount + 1; i++)
    	{
    		if (edges[i] != null)
    		{
    			if (vertLabel.equalsIgnoreCase(edges[i].getSource()) && edges[i] != null)
    			{
    				edges[i] = null;
    				edgeCount--;
    			}
    			else if (vertLabel.equalsIgnoreCase(edges[i].getTarget()) && edges[i] != null)
    			{
    				edges[i] = null;
    				edgeCount--;
    			}
    		}
    	}
    	
      	tempE = new Edge[100];
    	int tempECount = 0;
    	for (int i = 0; i < edgeCount + 1; i++)
    	{
    		if (edges[i] != null)
    		{
    			tempE[tempECount] = edges[i];
    			tempECount++;
    		}
    	}
    	edges = new Edge[100];
    	edges = tempE; 	
    	
    	/*REMOVING THE VERTEX*/
        for(int i = 0; i < nodeCount; i++)
        {
        	if(vertLabel.equalsIgnoreCase(nodes[i].getVertex()))
        	{
        		nodes[i] = null;
        		check = true;
        	}
        }
    	
        //makes a new temporary list and copies to new list
    	if (check == false)
        {
           	System.err.println("Warning: Vertex does not exist in this list.");
        }
    	else
    	{
    		temp = new Node[100];
        	int tempCount = 0;
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
    	}
    } // end of removeVertex()

    public List<MyPair> inNearestNeighbours(int k, String vertLabel) 
    {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        Edge[] newTempE = new Edge[100];
        int tempWeightCount = 0;

        if (k == -1)
        {
	        // WHERE the vertex is the target label, meaning we find the src labels!
	        for (int i = 0; i < edgeCount + 1; i++)
	        {
	        	if (edges[i] != null)
	        	{
	        		if (vertLabel.equalsIgnoreCase(edges[i].getTarget()))
	        		{
	        			neighbours.add(new MyPair(edges[i].getSource(), edges[i].getWeight()));
	        		}
	        	}
	        }
        } //end of k = -1
        //ORDERING the int
        if (k > 0)
        {
        	boolean vertCheck = false;
        	//Adds all edges to temp list
        	for (int i = 0; i < edgeCount + 1; i++)
        	{
        		if (edges[i] != null && edges[i].getTarget().equalsIgnoreCase(vertLabel))
        		{
        			System.out.println("edges added");
        			newTempE[i] = edges[i];
        			System.out.println(newTempE[i].getSource());
        			tempWeightCount++;
        			vertCheck = true;
        		}
        	}
        	if (vertCheck == false)
        	{
        		System.err.println("Warning: vertex does not exist.");
        	}
	        	for (int i = 0; i < tempWeightCount; i++) 
	        	{
	        		System.out.println(i + " " + tempWeightCount);
	                for (int j = 0; j < tempWeightCount - 1; j++) 
	                {
	                	System.out.println(j + " " + tempWeightCount);
	                    // check if we need to swap
	                	System.out.println("iteration " + j + " " + newTempE[j].getWeight() + "<" + newTempE[j+1].getWeight());
	                    if (newTempE[j].getWeight() < newTempE[j+1].getWeight() && newTempE[j] != null && newTempE[j+1] != null)
	                    {
	                        Edge temporary = newTempE[j+1];
	                        newTempE[j+1] = newTempE[j];
	                        newTempE[j] = temporary;
	                    }
	                }
	            }
	        	for (int i = 0; i < k; i++)
	        	{
	        		if (newTempE[i] != null)
	        		{
	        			neighbours.add(new MyPair(newTempE[i].getSource(), newTempE[i].getWeight()));
	        		}
	        	}
        } // end of k > 0
        newTempE = null;
        return neighbours;
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) 
    {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        Edge[] newTempEd = new Edge[100];
        int tempWeightCount = 0;

        if (k == -1)
        {
	        // WHERE the vertex is the source label, meaning we find the tar labels!
	        for (int i = 0; i < edgeCount + 1; i++)
	        {
	        	if (edges[i] != null)
	        	{
	        		if (vertLabel.equalsIgnoreCase(edges[i].getSource()))
	        		{
	        			neighbours.add(new MyPair(edges[i].getTarget(), edges[i].getWeight()));
	        		}
	        	}
	        }
        } //end of k = -1
        //ORDERING the int
        if (k > 0)
        {
        	boolean vertCheck = false;
        	//Adds all edges to temp list
        	for (int i = 0; i < edgeCount + 1; i++)
        	{
        		if (edges[i] != null && edges[i].getSource().equalsIgnoreCase(vertLabel))
        		{
        			System.out.println("edges added");
        			newTempEd[i] = edges[i];
        			System.out.println(newTempEd[i].getSource());
        			tempWeightCount++;
        			vertCheck = true;
        		}
        	}
        	if (vertCheck == false)
        	{
        		System.err.println("Warning: vertex does not exist.");
        	}
	        	for (int i = 0; i < tempWeightCount; i++) 
	        	{
	        		System.out.println(i + " " + tempWeightCount);
	                for (int j = 0; j < tempWeightCount - 1; j++) 
	                {
	                	System.out.println(j + " " + tempWeightCount);
	                    // check if we need to swap
	                	System.out.println("iteration " + j + " " + newTempEd[j].getWeight() + "<" + newTempEd[j+1].getWeight());
	                    if (newTempEd[j].getWeight() < newTempEd[j+1].getWeight() && newTempEd[j] != null && newTempEd[j+1] != null)
	                    {
	                        Edge temporary = newTempEd[j+1];
	                        newTempEd[j+1] = newTempEd[j];
	                        newTempEd[j] = temporary;
	                    }
	                }
	            }
	        	for (int i = 0; i < k; i++)
	        	{
	        		if (newTempEd[i] != null)
	        		{
	        			neighbours.add(new MyPair(newTempEd[i].getTarget(), newTempEd[i].getWeight()));
	        		}
	        	}
        } // end of k > 0
        	newTempEd = null;
	        return neighbours;
    } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os) 
    {
        // Implementing printing all vertices { PV } -- Order does not matter
    	for (int i = 0; i < nodeCount; i++)
    	{
    		os.printf("%1s", nodes[i].getVertex() + "  ");
    	}
    	System.out.println();
    } // end of printVertices()


    public void printEdges(PrintWriter os) 
    {
        // Implementing printing all edges { PE } -- Order does not matter -- each edge is on a new line
    	
    	for (int i = 0; i < edgeCount; i++)
    	{
    		os.println(edges[i].getSource() + " " + edges[i].getTarget() + " " + edges[i].getWeight());
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
