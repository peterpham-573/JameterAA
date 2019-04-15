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
        List<MyPair> tempList = new ArrayList<MyPair>();
        int tempWeightCount = 0;
        
        //vertex is the source and find targets
        if (k == -1)
        {
        	for (int i = 0; i < edgeCount; i++)
        	{
        		if (edges[i] != null && edges[i].getTarget().equalsIgnoreCase(vertLabel))
        		{
        			neighbours.add(new MyPair(vertLabel, edges[i].getWeight()));
        		}
        	}
        }
        else if (k > 0)
        {
        	for (int i = 0; i < edgeCount; i++)
        	{
        		if (edges[i] != null && edges[i].getTarget().equalsIgnoreCase(vertLabel))
        		{
        			tempList.add(new MyPair(vertLabel, edges[i].getWeight()));
        			tempWeightCount++;
        		}
        	}
        	
        	for (int i = 0; i < tempWeightCount; i++) 
        	{
                for (int j = 0; j < tempWeightCount - 1; j++) 
                {
                    if (tempList.get(j).getValue() < tempList.get(j+1).getValue() && tempList.get(j) != null && tempList.get(j+1) != null)
                    {
                        MyPair temporary = tempList.get(j+1);
                        tempList.set(j + 1, tempList.get(j));
                        tempList.set(j, temporary);
                    }
                }
            }
        	
        	for (int i = 0; i < k; i++)
        	{
        		if (tempList.get(i) != null)
        		{
        			neighbours.add(tempList.get(i));
        		}
        	}
        } //end of k > 0 condition
        return neighbours;
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) 
    {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        List<MyPair> tempList = new ArrayList<MyPair>();
        int tempWeightCount = 0;
        
        //vertex is the source and find targets
        if (k == -1)
        {
        	for (int i = 0; i < edgeCount; i++)
        	{
        		if (edges[i] != null && edges[i].getSource().equalsIgnoreCase(vertLabel))
        		{
        			neighbours.add(new MyPair(vertLabel, edges[i].getWeight()));
        		}
        	}
        }
        else if (k > 0)
        {
        	for (int i = 0; i < edgeCount; i++)
        	{
        		if (edges[i] != null && edges[i].getSource().equalsIgnoreCase(vertLabel))
        		{
        			tempList.add(new MyPair(vertLabel, edges[i].getWeight()));
        			tempWeightCount++;
        		}
        	}
        	
        	for (int i = 0; i < tempWeightCount; i++) 
        	{
                for (int j = 0; j < tempWeightCount - 1; j++) 
                {
                    if (tempList.get(j).getValue() < tempList.get(j+1).getValue() && tempList.get(j) != null && tempList.get(j+1) != null)
                    {
                        MyPair temporary = tempList.get(j+1);
                        tempList.set(j + 1, tempList.get(j));
                        tempList.set(j, temporary);
                    }
                }
            }
        	
        	for (int i = 0; i < k; i++)
        	{
        		if (tempList.get(i) != null)
        		{
        			neighbours.add(tempList.get(i));
        		}
        		
        	}
        } //end of k > 0 condition
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
