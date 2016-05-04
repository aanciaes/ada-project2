package project;

import java.util.List;

/**
 * Class representing the Whitchapel district - the graph
 * It has an array of lists. The array position represents a node the list contains all the adjacent
 * nodes to himself 
 * @author Miguel Anciaes
 * n43367
 * m.anciaes@campus.fct.unl.pt
 *
 */
public class Whitechapel {

	private List<Integer> [] graph;

	@SuppressWarnings("unchecked")
	public Whitechapel(int nNodes) {
		graph = (List<Integer>[]) new List[nNodes];
	}
	
	/**
	 * Adds a set of edges. It adds all nodes connected to a given node
	 * @param node The node
	 * @param cNodes List of all nodes connected to that node
	 */
	public void addEdge (int node, List<Integer> cNodes) {
		graph[node] = cNodes;
	}
	
	/**
	 * Return all nodes that are connected to the given node
	 * @param node Node which all connected nodes are being returned
	 * @return A list containing all nodes connected to given node
	 */
	public List<Integer> getChilds (int node) {
		return graph[node-1];
	}
	
	/**
	 * Returns the size of the graph
	 * @return The number of nodes of the graph
	 */
	public int nNodes () {
		return graph.length;
	}
}
