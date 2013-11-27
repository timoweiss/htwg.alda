package graph;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UndirectedGraph<Integer> graph1 = new AdjacencyListUndirectedGraph<>();
		DirectedGraph<Integer> graph2 = new AdjacencyListDirectedGraph<>();
		
		for(int i = 0; i <= 100; i++) {
			graph1.addVertex(i);
			graph2.addVertex(i);
		}
		for(int i = 0; i <= 78; i++) {
			int j = (int) (Math.random() * 78);
			if(j != i && j%77 != i && j%33 != i) {
				
				graph1.addEdge(i, j%33);
				
				graph2.addEdge(i, j%77);
				graph2.addEdge(i, j%33);
				graph2.addEdge(i, j);
				
			}	
		}

		System.out.println("Undirected");
		System.out.println("breadthFirstSearch: \t" + GraphTraversion.breadthFirstSearch(graph1, 2));
		System.out.println("depthFirstSearch: \t" + GraphTraversion.depthFirstSearch(graph1, 1));
		System.out.println();
		
		
		System.out.println("Directed");
		System.out.println("breadthFirstSearch: \t" + GraphTraversion.breadthFirstSearch(graph2, 1));

		System.out.println("depthFirstSearch: \t" + GraphTraversion.depthFirstSearch(graph2, 2));
		System.out.println();
		
		
		
		
		//Steffano
		AdjacencyListDirectedGraph<Integer> dirGraph = new AdjacencyListDirectedGraph<Integer>();
        AdjacencyListUndirectedGraph<Integer> undirGraph = new AdjacencyListUndirectedGraph<Integer>();

        for (int i = 0; i < 15; i++) {
                dirGraph.addVertex(i);
                undirGraph.addVertex(i);
        }
        
        for (int i = 0; i< 15; i++) {
                int j = (int) (Math.random() * 15);
                if (i != j || i!= j%5 || i!= j%3) {
                        dirGraph.addEdge(i, j);
                        dirGraph.addEdge(i, j%5);
                        dirGraph.addEdge(i, j%3);
                }
        }
        
        for (int i = 0; i< 10;i++) {
                int j = (int) (Math.random() * 10);
                if (i != j) {
                        undirGraph.addEdge(i, j);
                }
        }
        
        System.out.println("Breadth Search:");
        System.out.println("Direct Graph:");
        System.out.println(GraphTraversion.breadthFirstSearch(dirGraph, 0));
        System.out.println("Undirect Graph:");
        System.out.println(GraphTraversion.breadthFirstSearch(undirGraph, 0));
        
        System.out.println("Depth Search:");
        System.out.println("Direct Graph:");
        System.out.println(GraphTraversion.depthFirstSearch(dirGraph, 0));
        System.out.println("Undirect Graph:");
        System.out.println(GraphTraversion.depthFirstSearch(undirGraph, 0));
        
        System.out.println("Topological Sort:");
        System.out.println(GraphTraversion.topologicalSort(dirGraph));

		
	}

}
