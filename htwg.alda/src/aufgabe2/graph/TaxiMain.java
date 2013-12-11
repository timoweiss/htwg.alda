package aufgabe2.graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;

import aufgabe2.sim.SYSimulation;

public class TaxiMain {

	public static void main(String[] args) throws IOException {
		UndirectedGraph<Integer> undirectedG = new AdjacencyListUndirectedGraph<>();
		UndirectedGraph<Integer> gDij = new AdjacencyListUndirectedGraph<>();

		// Linux
		// File file = new
		// File("Z:\git\htwg.alda\htwg.alda\src\aufgabe2\graph\ScotlandYard.txt");

		// Windows
		File file = new File(
				"Z:/git/htwg.alda32323/htwg.alda/src/aufgabe2/graph/ScotlandYard.txt");

		readOnlyTaxi(file, undirectedG);
		readAll(file, gDij);
		
		SYSimulation sim = new SYSimulation();
		SYSimulation sim2 = new SYSimulation();
		SYSimulation sim3 = new SYSimulation();
		
		sim.startSequence("Depth");
		sim2.startSequence("Breadth");
		sim3.startSequence("Dijkstra");
		
		LinkedList<Integer> depthList = new LinkedList<>(GraphTraversion.depthFirstSearch(undirectedG, 1));
		LinkedList<Integer> breadthList = new LinkedList<>(GraphTraversion.breadthFirstSearch(undirectedG, 1));
		
		DijkstraShortestPath<Integer> dijkstraPath = new DijkstraShortestPath(gDij);
		dijkstraPath.searchShortestPath(1, 175);
		
		LinkedList<Integer> dijkstraPathList = new LinkedList<>(dijkstraPath.getShortestPath());
		
		
		
		for (int i = 0; i < depthList.size(); i++) {
			
			if (i + 1 != depthList.size()) {
				sim.drive(depthList.get(i), depthList.get(i + 1));
			}
		}
		
		for (int i = 0; i < breadthList.size(); i++) {
			if (i + 1 != breadthList.size()) {
				sim2.drive(breadthList.get(i), breadthList.get(i + 1));
			}
		}
		
		for (int i = 0; i < dijkstraPathList.size(); i++) {
			if (i + 1 != dijkstraPathList.size()) {
				sim3.drive(dijkstraPathList.get(i), dijkstraPathList.get(i + 1));
			}
		}
		sim.stopSequence();
		sim2.stopSequence();
		sim3.stopSequence();
		
		
	}

	public static void readOnlyTaxi(File f, Graph<Integer> g) {
		LineNumberReader in = null;
		try {

			in = new LineNumberReader(new FileReader(f));
			String line;

			while ((line = in.readLine()) != null) {
				String[] sf = line.split(" ");
				if (sf.length == 3) {

					if (sf[2].equalsIgnoreCase("taxi")) {

						g.addVertex(Integer.parseInt(sf[0]));
						g.addVertex(Integer.parseInt(sf[1]));

						g.addEdge(Integer.parseInt(sf[0]),
								Integer.parseInt(sf[1]));
					}
				}
			}
			in.close();
		} catch (IOException ex) {

		}
		return;
	}
	
	public static void readAll(File f, Graph<Integer> g) {
		LineNumberReader in = null;
		try {

			in = new LineNumberReader(new FileReader(f));
			String line;

			while ((line = in.readLine()) != null) {
				String[] sf = line.split(" ");
				if (sf.length == 3) {
					
					g.addVertex(Integer.parseInt(sf[0]));
					g.addVertex(Integer.parseInt(sf[1]));
					
					if (sf[2].equalsIgnoreCase("taxi")) {

						g.addEdge(Integer.parseInt(sf[0]),
								Integer.parseInt(sf[1]), 3.0);
					
					} else if (sf[2].equalsIgnoreCase("ubahn")) {
						
						g.addEdge(Integer.parseInt(sf[0]),
								Integer.parseInt(sf[1]), 5.0);
						
					} else {
						g.addEdge(Integer.parseInt(sf[0]),
								Integer.parseInt(sf[1]), 2.0);
					}
				}
			}
			in.close();
		} catch (IOException ex) {

		}
		return;
	}

}
