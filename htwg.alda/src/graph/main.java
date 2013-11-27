package graph;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UndirectedGraph<Integer> graph1 = new AdjacencyListUndirectedGraph<>();
		DirectedGraph<Integer> graph2 = new AdjacencyListDirectedGraph<>();
		
		for(int i = 0; i <= 10; i++) {
			graph1.addVertex(i);
			graph2.addVertex(i);
		}
		for(int i = 0; i <= 10; i++) {
			if(i != i%5) {
				graph1.addEdge(i, i%5, 1.0);
				graph2.addEdge(i, i%5, 1.0);
			}	
		}

		System.out.println("Undirected");
		System.out.println("breadthFirstSearch: \t" + GraphTraversion.breadthFirstSearch(graph1, 2));
		System.out.println("depthFirstSearch: \t" + GraphTraversion.depthFirstSearch(graph1, 2));
		System.out.println();
		
		
		System.out.println("Directed");
		System.out.println("breadthFirstSearch: \t" + GraphTraversion.breadthFirstSearch(graph2, 2));

		System.out.println("depthFirstSearch: \t" + GraphTraversion.depthFirstSearch(graph2, 2));
		System.out.println();
		
	}

}
