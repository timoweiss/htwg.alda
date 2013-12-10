package aufgabe2.graph;

import aufgabe2.gui.DijkstraShortestPath;

public class DijTest {
	public static void main(final String args[]) {
		UndirectedGraph<Integer> graph2 = new AdjacencyListUndirectedGraph<>();
		
		for(int i = 0; i <= 21; i++) {
			graph2.addVertex(i);
		}
		for(int i = 0; i <= 20; i++) {
			int j = (int) (Math.random() * 20);
			if(j != i && j%15 != i && j%7 != i) {
				
				
				graph2.addEdge(i, j%15);
				graph2.addEdge(i, j%7);
				graph2.addEdge(i, j);
				
			}	
		}
		
		DijkstraShortestPath<Integer> dij = new DijkstraShortestPath<Integer>(graph2);
		System.out.println(dij.searchAllShortestPaths(graph2.getVertexList().get(0)));
	}
}
