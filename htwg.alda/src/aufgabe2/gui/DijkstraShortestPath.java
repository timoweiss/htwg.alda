package aufgabe2.gui;

import java.security.KeyStore.Entry;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import aufgabe2.graph.Graph;

public class DijkstraShortestPath<V> {

	private Map<V, Double> distance;
	private Map<V, V> prevVertexes;
	private Graph<V> graph;

	boolean searchShortestPath = false;
	V beginning;
	V end;

	public DijkstraShortestPath(Graph<V> g) {
		this.graph = g;
		this.prevVertexes = new TreeMap<V, V>();
		this.distance = new TreeMap<V, Double>();
	}

	public boolean searchShortestPath(V s, V g) {
//		this.beginning = s;
//		this.end = g;
//
//		// Alle vertexe addden
//
//		for (V x : graph.getVertexList()) {
//			distance.put(x, Double.MAX_VALUE);
//		}
//
//		// Startknoten setzen
//		distance.put(s, 0.0);
//
//		V tmp = null;
//		double distance_max = Double.MAX_VALUE;
//
//		for (java.util.Map.Entry<V, Double> k : distance.entrySet()) {
//			// if(k.getValue().Candidate) {
//
//			if (k.getValue() < distance_max) {
//				tmp = k.getKey();
//				distance_max = k.getValue();
//			}
//			// }
//		}
		return false;

	}

	public List<V> getShortestPath() {
		return null;
	}

	public double getDistance() {
		return 0;
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
		return false;
	}

	public List<V> getShortestPathTo(V g) {
		return null;
	}

	public double getDistanceTo(V g) {
		return 0;
	}

	

	
}