package graph;

import java.util.LinkedList;
import java.util.List;

public class GraphTraversion {
	
	public static <V> List<V> depthFirstSearch(Graph<V> g, V s) {
		List<V> visited = new LinkedList<V>();
		depthFirstSearchR(g, s, visited);
		return visited;
		
	}
	
	private static <V> void depthFirstSearchR(Graph<V> g, V s, List<V> v) {
		v.add(s);
		for(V x : g.getAdjacentVertexList(s)) {
			if(!g.containsVertex(x)) {
				depthFirstSearchR(g, x, v);
			}
		}
	}
	

}
