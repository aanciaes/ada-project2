package project;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class that given the inputs solves the problem
 * @author Miguel Anciaes
 * n43367
 * m.anciaes@campus.fct.unl.pt
 *
 */
public class SolveProblem {
	
	//Representation of the graph
	private Whitechapel graph;
	
	//Representation of the clues given in input
	private List<int []> clues;

	public SolveProblem (Whitechapel graph, List<int[]> clues) {
		this.graph = graph;
		this.clues = clues;
	}

	/**
	 * Solves the problem and gets the possible hideouts of Jack
	 * @return A Sorted Set of all possible hideouts
	 */
	public Set<Integer> getPossibleHideouts () {
		Set<Integer> set = null;

		//For every clue
		for(int i=0;i<clues.size();i++){
			if(set==null){
				//First time here creates the set and adds the first possible locations
				set=new TreeSet<Integer>();
				set.addAll(findJack(clues.get(i)));
			}else{
				//Retains all hideouts that exists in both sets and deletes the others 
				set.retainAll(findJack(clues.get(i)));
			}
		}
		//A sorted set containing all possible hideouts with all clues processed
		return set;
	}

	/**
	 * Calculates the possible hideouts with given clue
	 * @param clue A clue of here jack may be hidden
	 * @return The possible hideouts with given clue
	 */
	public Queue<Integer> findJack (int[] clue) {
		int startNode = clue[0];
		int degree = clue [1];
		int count=0;

		//Starting breadth first algorithm
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//Boolean arrays to accelerate searches
		boolean [] visited = new boolean [graph.nNodes()+1];
		boolean [] inQueue = new boolean [graph.nNodes()+1];
		
		queue.add(startNode);
		inQueue[startNode]=true;

		while(!queue.isEmpty() && count<degree){			
			int check = queue.size();
			for(int i=0;i<check;i++){
				Integer node = queue.remove();
				inQueue[node]=false;
				visited[node]=true;
				if(count<degree){
					addNoDuplicates(graph.getChilds(node), queue, inQueue, visited);
				}
			}
			count++;
		}
		return queue;
	}

	//Adds all elements of a list to the queue except duplicates and already visited nodes
	private void addNoDuplicates (List<Integer> list, Queue<Integer> queue, boolean [] inQueue, boolean [] visited){
		for(Integer integer : list){
			if(!inQueue[integer] && !visited[integer]){
				queue.add(integer);
				inQueue[integer]=true;
			}
		}
	}
}
