package aufgabe2.graph;

import java.security.KeyStore.Entry;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DijkstraShortestPath<V> {

	private Map<V, Double> distance;
	private Map<V, V> prevVertexes;
	private Graph<V> graph;
	
	private V endknoten = null;

	boolean searchShortestPath = false;
	boolean searchAllShortestPaths = false;
	
	
	public DijkstraShortestPath(Graph<V> g) {
		this.graph = g;
		this.prevVertexes = new TreeMap<V, V>();
		this.distance = new TreeMap<V, Double>();
	}

	public boolean searchShortestPath(V s, V g) {
		if (searchAllShortestPaths(g) && distance.get(s) < Double.MAX_VALUE) {
			endknoten = s;
			
			searchShortestPath = true;
			return true;
		} else {
			return false;
		}
		
	}

	public List<V> getShortestPath() throws Exception {
		if (searchShortestPath) {
			return getShortestPathTo(endknoten);
		} else {
			throw new Exception("getShortestPathTo() nicht ausgeführt");
		}
	}

	public double getDistance() throws Exception {
		if (searchShortestPath) {
			return getDistanceTo(endknoten);
		} else {
			throw new Exception("getShortestPathTo() nicht ausgeführt");
		}
	}

	public boolean searchAllShortestPaths(V s) {

		LinkedList<V> candidates = new LinkedList<>();

		for (V vertex : graph.getVertexList()) {
			distance.put(vertex, Double.POSITIVE_INFINITY);
			prevVertexes.put(vertex, null);
		}

		distance.put(s, 0.0);
		candidates.add(s);

		while (!candidates.isEmpty()) {

			V v = candidates.get(0);

			for (V x : candidates) {
				if (distance.get(x) < distance.get(v)) {
					v = x;
				}
			}
			candidates.remove(v);

			for (V w : graph.getAdjacentVertexList(v)) {
				if (distance.get(w) == Double.POSITIVE_INFINITY) {
					candidates.add(w);
				}

				if ((distance.get(v) + graph.getWeight(v, w)) < distance.get(w)) {
					prevVertexes.put(w, v);
					distance.put(w, (distance.get(v) + graph.getWeight(v, w)));
				}

			}
			
		}
		
		for (V x: distance.keySet()) {
			if (distance.get(x) == Double.MAX_VALUE) {
				return false;
			}
		}
		
		searchAllShortestPaths = true;
		return true;
	}

	public List<V> getShortestPathTo(V g) throws Exception {
		if (!searchAllShortestPaths) {
			throw new Exception("searchAllShortestPaths nicht ausgeführt!");
		}
		
		V vertex = g;
		LinkedList<V> liste = new LinkedList<>();
		liste.add(vertex);
		
		while (prevVertexes.get(vertex) != null) {
			
			liste.add(prevVertexes.get(vertex));
			vertex = prevVertexes.get(vertex);
			
		}
		return liste;
	}

	public double getDistanceTo(V g) throws Exception {
		if (!searchAllShortestPaths) {
			throw new Exception("searchAllShortestPaths nicht ausgeführt!");
		}
		
		return distance.get(g);
	}

}