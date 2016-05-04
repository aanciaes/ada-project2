import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import project.SolveProblem;
import project.Whitechapel;
/**
 * Main Class
 * @author Miguel Anciaes
 * n43367
 * m.anciaes@campus.fct.unl.pt
 *
 */
public class Main {

	private static final String NOSOLUTION = "NO SOLUTION";

	public static void main(String[] args) throws IOException {
		//class to read input
		BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

		//getGraph reads all inputs and creates a class to solve to problem
		SolveProblem s = getGraph(reader);
		
		//Solves the problem and return a list with possible hideouts
		Set<Integer> set = s.getPossibleHideouts();
		if(set.isEmpty())
			System.out.println(NOSOLUTION); //If list is empty, theres no solution to the problem
		else{
			String anwser = "";
			for(Integer integer : set)	
				anwser += integer + " ";
			System.out.println(anwser.trim()); //Removes any leading or trailing whitespace
		}
	}

	/**
	 * Reads all inputs and returns a class to solve the problem based on the graph and clues given
	 * @param reader The class that allows reading inputs form console
	 * @return The class to solve the problem
	 * @throws IOException
	 */
	public static SolveProblem getGraph (BufferedReader reader) throws IOException{
		int nNodes=Integer.parseInt(reader.readLine());

		//Class that represents the graph
		Whitechapel w = new Whitechapel(nNodes);

		//Processing reading the nodes
		//reading all lines containing nodes and their're adjacent's
		for(int i=0;i<nNodes;i++){
			String [] line = reader.readLine().split("\\s+");
 
			List<Integer> cNodes = new ArrayList<Integer>(line.length);; //List with all adjacent nodes
			for(int node=0; node<line.length-1;node++){
				cNodes.add(Integer.parseInt(line[node]));
			}
			w.addEdge(i, cNodes); //adds a number edge's to the graph
		}
		//Processing reading of the clues
		int nClues=Integer.parseInt(reader.readLine());
		List<int[]> clues = new ArrayList<int[]>(nClues);
		for(int i=0;i<nClues;i++){
			String [] line = reader.readLine().split("\\s+");
			int [] aClues = new int [2];	//Clues are represented by two integers
			for(int x = 0; x<line.length;x++)
				aClues[x]=Integer.parseInt(line[x]);
			clues.add(aClues);
		}
		return new SolveProblem(w, clues);
	}
}
