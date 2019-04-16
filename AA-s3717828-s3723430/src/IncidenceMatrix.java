import java.io.*;
import java.util.*;

/**
 * Incident matrix implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class IncidenceMatrix extends AbstractAssocGraph {

	private int numOfVertex = 0;
	private int numOfEdges = 0;
	private Map<String,Integer> vertexK = new HashMap<String,Integer>(); //vertex, row
	private Map<String,Integer> edgeK = new HashMap<String,Integer>();	//edge, col
	private int matrix[][] = new int[numOfVertex][numOfEdges];

	/**
	 * Constructs empty graph.
	 */
	public IncidenceMatrix() {

	} // end of IncidentMatrix()

	public void addVertex(String vertLabel) {
		
		vertexK.put(vertLabel, numOfVertex);
		numOfVertex++;
	
	} // end of addVertex()

	public void addEdge(String srcLabel, String tarLabel, int weight) 
	{

		if(numOfVertex <= 0 && weight <= 0)
		{
			System.err.println("Warning: There are no vertices, or the weight is inapplicable.");
		}
		else if(numOfVertex > 0 && weight > 0) 
		{
			boolean check = false;
			String addingEdge = srcLabel + tarLabel;

			if(vertexK.containsKey(srcLabel))
			{
				if(vertexK.containsKey(tarLabel))
				{
					check = true;
				}
			}

			/*
			 * After we get the positions of the vertices,
			 * we add the edge to map list, and then
			 *  we go ahead and add
			 * the weight to the matrix
			 */
			if(check == true)
			{
				edgeK.put(addingEdge, numOfEdges);
				numOfEdges++;
				
				matrix = new int[numOfVertex][numOfEdges];

				matrix[vertexK.get(srcLabel)][edgeK.get(addingEdge)] = weight;
				matrix[vertexK.get(tarLabel)][edgeK.get(addingEdge)] = -weight;

			}
			else
			{
				System.err.println("Warning: The vertex does not exist.");
			}
		}
	} // end of addEdge()

	public int getEdgeWeight(String srcLabel, String tarLabel) 
	{
		/*
		 * 
		 * CHECK IF source and target label exist
		 * then access the source and target label via the matrix
		 * 
		 */
		boolean check = false;
		String checkEdge = srcLabel + tarLabel;

			if(vertexK.containsKey(srcLabel))
			{
				if(vertexK.containsKey(tarLabel))
				{
					check = true;
				}
			}
			
			if (check == true)
			{
				if (edgeK.containsKey(checkEdge))
				{
					return matrix[vertexK.get(srcLabel)][edgeK.get(checkEdge)];
				}
			}

		// update return value
		return EDGE_NOT_EXIST;
	} // end of existEdge()

	public void updateWeightEdge(String srcLabel, String tarLabel, int weight) 
	{
		/*
		 * 
		 * CHECK IF source and target label exist
		 * then access the source and target label and update weight
		 * 
		 */
		boolean check = false;
		String checkEdge = srcLabel + tarLabel;

			if(vertexK.containsKey(srcLabel))
			{
				if(vertexK.containsKey(tarLabel))
				{
					check = true;
				}
			}
		
			if(check == true)
			{
				if (edgeK.containsKey(checkEdge) && weight >= 0)
				{
					matrix[vertexK.get(srcLabel)][edgeK.get(checkEdge)] = weight;
					matrix[vertexK.get(tarLabel)][edgeK.get(checkEdge)] = -weight;
				}
				else
				{
					System.err.println("Warning: Weight is less than or equal to 0.");
				}
			}
		
	} // end of updateWeightEdge()

	public void removeVertex(String vertLabel) 
	{
		for(Map.Entry<String, Integer> e: edgeK.entrySet())
		{
			String edger = e.getKey();
			String pointA = edger.substring(0, 1);
			String pointB = edger.substring(1, 2);
			
			if(pointA.equalsIgnoreCase(vertLabel) || pointB.equalsIgnoreCase(vertLabel))
			{
				matrix[vertexK.get(vertLabel)][edgeK.get(edger)] = 0;
			}
		}
		/*
		 *  1 -- REMOVE EDGES RELATED TO VERTEX FIRST FROM THE MAP!,
		 * 	2 -- THEN REMOVE VERTEX FROM THE MAP!
		 * 	3 -- REPAINT THE MATRIX FROM THE MATRIX!
		 */

		/* creating a string array */
		String[] arry = new String[numOfEdges];
		int count = 0;
		/* 1 -- REMOVING THE EDGES FROM THE MAP! */
		for(Map.Entry<String, Integer> e: edgeK.entrySet())
		{
			String edger = e.getKey();
			String pointA = edger.substring(0, 1);
			String pointB = edger.substring(1, 2);
			//IF source or target contains the vertex, add to string array, iterate count
			if(pointA.equalsIgnoreCase(vertLabel) || pointB.equalsIgnoreCase(vertLabel))
			{
				arry[count] = edger;
				count++;
			}
		}

		for(int i = 0; i < count; i++)
		{
			if(arry[i] != null)
			{
				edgeK.remove(arry[i]);
				/*
				 * reset numOfEdges
				 * create temporary Map for new order of edges.
				 * add back remaining edges from edgeK but to their new number using numOfEdges.
				 * make edgeK = temporary map
				 */
			}
		}
		numOfEdges = 0;
		for (Map.Entry<String, Integer> e: edgeK.entrySet())
		{
			e.setValue(numOfEdges);
			numOfEdges++;
		}
	/* 2 -- REMOVING THE VERTEX FROM THE MAP! */
		/*
		 * reset numOfVertex
		 * create temporary map for new order of vertices 
		 * add back remaining edges from vertexK to their new number using numOfVertex
		 * make vertexK = temporary map
		 */
		vertexK.remove(vertLabel);
		numOfVertex = 0;
		for (Map.Entry<String, Integer> e: vertexK.entrySet())
		{
			e.setValue(numOfVertex);
			numOfVertex++;
		}
		
		/* 3 -- Repaint the matrix */
		matrix = new int[numOfVertex][numOfEdges];
		
		
		
		
		
		
	} // end of removeVertex()

	public List<MyPair> inNearestNeighbours(int k, String vertLabel) 
	{
		List<MyPair> neighbours = new ArrayList<MyPair>();
		List<MyPair> temp = new ArrayList<MyPair>();
		int count = 0;
		
		if(k == -1)
		{
			/*	AIM: to get the target labels
			 * get the substrings
			 * if source = a, we get the target
			 */

			for(Map.Entry<String, Integer> e: edgeK.entrySet())
			{
				String getEdge = e.getKey();
				String pointA = getEdge.substring(0, 1);
				String pointB = getEdge.substring(1, 2);
				
				if(pointA.equalsIgnoreCase(vertLabel))
				{
					neighbours.add(new MyPair(pointB, matrix[vertexK.get(pointA)][edgeK.get(getEdge)]));
				}
			}
		}
		else if (k > 0)
		{
			for(Map.Entry<String, Integer> e: edgeK.entrySet())
			{
				String getEdge = e.getKey();
				String pointA = getEdge.substring(0, 1);
				String pointB = getEdge.substring(1, 2);
				
				if(pointA.equalsIgnoreCase(vertLabel))
				{
					temp.add(new MyPair(pointB, matrix[vertexK.get(pointA)][edgeK.get(getEdge)]));
					count++;
				}
			}
			
			for (int i = 0; i < count; i++)
			{
				for (int j = 0; j < count - 1; j++)
				{
					if (temp.get(j).getValue() < temp.get(j+1).getValue() && temp.get(j) != null && temp.get(j+1).getValue() != null)
					{
						MyPair temporary = temp.get(j+1);
						temp.set(j + 1, temp.get(j));
						temp.set(j, temporary);
					}
				}
			}
			if (k <= count)
			{
				for (int i = 0; i + 1 <= k; i++)
				{
					if (temp.get(i) != null)
					{
						neighbours.add(temp.get(i));
					}
				}
			}
			else
			{
				System.err.println("Warning: int k is more than amount of neighbours");
			}
		} //end of k > 0 method

		return neighbours;
	} // end of inNearestNeighbours()

	public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
		List<MyPair> neighbours = new ArrayList<MyPair>();
		List<MyPair> temp = new ArrayList<MyPair>();
		int count = 0;
		
		if(k == -1)
		{
			/*	AIM: to get the target labels
			 * get the substrings
			 * if target = a, we get the source
			 * hehe xd
			 */

			for(Map.Entry<String, Integer> e: edgeK.entrySet())
			{
				String getEdge = e.getKey();
				String pointA = getEdge.substring(0, 1);
				String pointB = getEdge.substring(1, 2);
				
				if(pointB.equalsIgnoreCase(vertLabel))
				{
					neighbours.add(new MyPair(pointA, matrix[vertexK.get(pointA)][edgeK.get(getEdge)]));
				}
			}
		}
		else if (k > 0)
		{
			for(Map.Entry<String, Integer> e: edgeK.entrySet())
			{
				String getEdge = e.getKey();
				String pointB = getEdge.substring(0, 1);
				String pointA = getEdge.substring(1, 2);
				
				if(pointB.equalsIgnoreCase(vertLabel))
				{
					temp.add(new MyPair(pointA, matrix[vertexK.get(pointA)][edgeK.get(getEdge)]));
					count++;
				}
			}
			
			for (int i = 0; i < count; i++)
			{
				for (int j = 0; j < count - 1; j++)
				{
					if (temp.get(j).getValue() < temp.get(j+1).getValue() && temp.get(j) != null && temp.get(j+1).getValue() != null)
					{
						MyPair temporary = temp.get(j+1);
						temp.set(j + 1, temp.get(j));
						temp.set(j, temporary);
					}
				}
			}
			if (k <= count)
			{
				for (int i = 0; i + 1 <= k; i++)
				{
					if (temp.get(i) != null)
					{
						neighbours.add(temp.get(i));
					}
				}
			}
			else
			{
				System.err.println("Warning: int k is more than amount of neighbours");
			}
		} //end of k > 0 method

		return neighbours;
	} // end of outNearestNeighbours()

	public void printVertices(PrintWriter os) 
	{
		for(Map.Entry<String, Integer> vert: vertexK.entrySet())
		{
			String vtx = vert.getKey();
			os.printf("%1s", vtx + " ");
		}
		os.println();
	} // end of printVertices()

	public void printEdges(PrintWriter os) 
	{
		for(Map.Entry<String, Integer> edg: edgeK.entrySet())
		{
			String edgs = edg.getKey();
			String pointA = edgs.substring(0, 1);
			String pointB = edgs.substring(1, 2);
			int w = matrix[vertexK.get(pointA)][edgeK.get(edgs)];
			
			os.println(pointA + " " + pointB + " " + w);
		}
		
		os.println();
	} // end of printEdges()

} // end of class IncidenceMatrix
