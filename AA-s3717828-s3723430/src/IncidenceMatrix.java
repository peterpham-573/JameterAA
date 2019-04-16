import java.io.*;
import java.util.*;
import com.sun.javafx.geom.Edge;

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
	private Map<String,Integer> VertexK = new HashMap<String,Integer>();
	private Map<String,Integer> EdgeK = new HashMap<String,Integer>();
	private Edge matrix[][];

	/**
	 * Contructs empty graph.
	 */
	public IncidenceMatrix() {

	} // end of IncidentMatrix()

	public void addVertex(String vertLabel) {
		
		VertexK.put(vertLabel, numOfVertex);
		EdgeK.put(vertLabel, numOfVertex);
		
		if(numOfVertex != 0){

		
		}
		
		
	} // end of addVertex()

	public void addEdge(String srcLabel, String tarLabel, int weight) {
		
		
		
	} // end of addEdge()

	public int getEdgeWeight(String srcLabel, String tarLabel) {
		// Implement me!

		// update return value
		return EDGE_NOT_EXIST;
	} // end of existEdge()

	public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
		// Implement me!
	} // end of updateWeightEdge()

	public void removeVertex(String vertLabel) {
		// Implement me!
	} // end of removeVertex()

	public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
		List<MyPair> neighbours = new ArrayList<MyPair>();

		// Implement me!

		return neighbours;
	} // end of inNearestNeighbours()

	public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
		List<MyPair> neighbours = new ArrayList<MyPair>();

		// Implement me!

		return neighbours;
	} // end of outNearestNeighbours()

	public void printVertices(PrintWriter os) {
		// Implement me!
	} // end of printVertices()

	public void printEdges(PrintWriter os) {
		// Implement me!
	} // end of printEdges()

} // end of class IncidenceMatrix
