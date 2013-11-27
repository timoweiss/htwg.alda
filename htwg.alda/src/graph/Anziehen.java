package graph;

public class Anziehen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DirectedGraph<String> derGraf = new AdjacencyListDirectedGraph<>();
		
		derGraf.addVertex("Strümpfe");
		derGraf.addVertex("Schuhe");
		derGraf.addVertex("Hose");
		derGraf.addVertex("Unterhose");
		derGraf.addVertex("Unterhemd");
		derGraf.addVertex("Hemd");
		derGraf.addVertex("Gürtel");
		derGraf.addVertex("Pullover");
		derGraf.addVertex("Mantel");
		derGraf.addVertex("Schal");
		derGraf.addVertex("Handschuhe");
		derGraf.addVertex("Mütze");
		
		//#1
		derGraf.addEdge("Unterhose", "Hose");
		//#2
		derGraf.addEdge("Unterhemd", "Hemd");
		//#3
		derGraf.addEdge("Strümpfe", "Schuhe");
		//#4
		derGraf.addEdge("Hose", "Gürtel");
		//#5
		derGraf.addEdge("Hemd", "Pullover");
		//#6
		derGraf.addEdge("Pullover", "Mantel");
		//#7
		derGraf.addEdge("Mantel", "Schal");
		//#8
		derGraf.addEdge("Mantel", "Mütze");
		//#9
		derGraf.addEdge("Mütze", "Handschuhe");
		
		// fixing
		//#10
		derGraf.addEdge("Unterhose", "Strümpfe");
		//#11
		derGraf.addEdge("Strümpfe", "Unterhemd");
		//#12
		derGraf.addEdge("Unterhemd", "Hose");
		
				
		System.out.println("directed");
		System.out.println("topologicalSort: \t" + GraphTraversion.topologicalSort(derGraf));
		//System.out.println("depthFirstSearch: \t" + GraphTraversion.depthFirstSearch(derGraf, 2));
		System.out.println();
		
		
		//# XX make it zyklisch ;)
		derGraf.addEdge("Hose", "Unterhemd");
		System.out.println("topologicalSort: \t" + GraphTraversion.topologicalSort(derGraf));
	}

}
