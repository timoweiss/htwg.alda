package graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class GraphTraversion {

	// #1
	public static <V> List<V> depthFirstSearch(Graph<V> g, V s) {
		List<V> searchList = new LinkedList<V>();
		Set<V> visited = new TreeSet<V>();

		visitDeepR(g, s, visited, searchList);

		return searchList;
	}

	private static <V> void visitDeepR(Graph<V> g, V v, Set<V> visited,
			List<V> list) {
		visited.add(v);
		list.add(v);

		for (V vertex : g.getAdjacentVertexList(v)) {
			if (!visited.contains(vertex)) {
				visitDeepR(g, vertex, visited, list);
			}
		}
	}

	// #2
	public static <V> List<V> breadthFirstSearch(Graph<V> g, V s) {
		List<V> visited = new LinkedList<V>();
		Queue<V> queue = new LinkedList<V>();
		queue.add(s);

		while (!queue.isEmpty()) {
			s = queue.remove();
			if (visited.contains(s)) {
				continue;
			}
			visited.add(s);
			for (V x : g.getAdjacentVertexList(s)) {
				if (!visited.contains(x)) {
					queue.add(x);
				}
			}
		}

		return visited;

	}

	// #3
	public static <V> List<V> topologicalSort(DirectedGraph<V> g) {
		List<V> topologicalSortedList = new LinkedList<>();
		Map<V, Integer> inDegree = new HashMap<>();
		Queue<V> q = new ArrayDeque<>();
		for (V v : g.getVertexList()) {
			inDegree.put(v, g.getInDegree(v));
			if (inDegree.get(v) == 0) {
				q.add(v);
			}
		}
		while (!q.isEmpty()) {
			V v = q.remove();
			topologicalSortedList.add(v);
			for (V w : g.getSuccessorVertexList(v)) {
				inDegree.put(w, inDegree.get(w) - 1);

				if (inDegree.get(w) == 0) {
					q.add(w);
				}
			}
		}
		if (topologicalSortedList.size() != g.getNumberOfVertexes()) {
			return null;
		}
		return topologicalSortedList;
	}

}
