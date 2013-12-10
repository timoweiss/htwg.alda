package aufgabe2.gui;

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

	public DijkstraShortestPath(Graph<V> g) {
		this.graph = g;
		this.prevVertexes = new TreeMap<V, V>();
		this.distance = new TreeMap<V, Double>();
	}

	public boolean searchShortestPath(V s, V g) {
		return false;
	}

	public List<V> getShortestPath() {
		return null;
	}

	public double getDistance() {
		return 0;
	}

	public boolean searchAllShortestPaths(V s) {
		LinkedList<VertexDistance<V>> candidates = new LinkedList<>();

		for (V vertex : graph.getVertexList()) {
			distance.put(vertex, Double.POSITIVE_INFINITY);
			prevVertexes.put(vertex, null);
		}
		
		distance.put(s, 0.0);
		candidates.add(new VertexDistance<V>(s, distance.get(s)));
		Collections.sort(candidates, new VertexComparator());
		
		
		while (!candidates.isEmpty()) {

//			Double min = Double.POSITIVE_INFINITY;
//
//			for (VertexDistance<V> x : candidates) {
//
//				if (v == null) {
//					v = x.vertex;
//					System.out.println("v == null");
//				}
//
//				if (x.value < min) {
//					min = x.value;
//					v = x.vertex;
//					System.out.println("das andere");
//
//				}
//
//			}
//			
			V v = candidates.poll().vertex;
			// V v = candidates.poll().vertex;

			for (V w : graph.getAdjacentVertexList(v)) {
				if (distance.get(w) == Double.POSITIVE_INFINITY) {
					candidates.add(new VertexDistance<V>(w, distance.get(w)));
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

	private static class VertexDistance<V> {
		private V vertex;
		private Double value;

		private VertexDistance(V v, Double value) {
			this.vertex = v;
			this.value = value;
		}
	}

	private class VertexComparator implements Comparator<VertexDistance<V>> {
		@Override
		public int compare(VertexDistance<V> o1, VertexDistance<V> o2) {
			if (o1.value > o2.value) {
				return 1;
			} else if (o1.value < o2.value) {
				return -1;
			}
			return 0;
		}
	}
}