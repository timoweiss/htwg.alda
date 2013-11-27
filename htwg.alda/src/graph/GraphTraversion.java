package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphTraversion {
	
	
	// #1
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
	
	// #2
	public static <V> List<V> breadthFirstSearch(Graph<V> g, V s) {
		List<V> visited = new LinkedList<V>();
		Queue<V> queue = new LinkedList<V>();
		queue.add(s);
		
		while(!queue.isEmpty()) {
			s = queue.remove();
			if(visited.contains(s)) {
				continue;
			}
			visited.add(s);
			for(V x : g.getAdjacentVertexList(s)) {
				if(!visited.contains(x)) {
					queue.add(x);
				}
			}
		}
		
		return visited;
		
	}
	
	// #3
	
	
	

}
