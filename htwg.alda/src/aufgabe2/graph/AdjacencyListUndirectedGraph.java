package aufgabe2.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

	private HashMap<V, HashMap<V, Double>> adjacencyList;
	private LinkedList<Edge<V>> edgeList = new LinkedList<Edge<V>>();

	public AdjacencyListUndirectedGraph() {
		adjacencyList = new HashMap<V, HashMap<V, Double>>();
	}

	// returns true falls knoten nicht vorhanden und fügt knoten ein
	@Override
	public boolean addVertex(V v) {
		if (!containsVertex(v)) {
			adjacencyList.put(v, new HashMap<V, Double>());
			return true;
		}
		return false;
	}

	@Override
	public boolean addEdge(V v, V w) {
		return addEdge(v, w, 1.0);
	}

	@Override
	public boolean addEdge(V v, V w, double weight) {
		if (!containsVertex(v) || !containsVertex(w) || (v == w)) {
			throw new IllegalArgumentException();
		} else if (containsEdge(v, w) || containsEdge(w, v)) {
			return false;
		}

		adjacencyList.get(v).put(w, weight);
		adjacencyList.get(w).put(v, weight);
		edgeList.add(new Edge<V>(v, w, weight));
		edgeList.add(new Edge<V>(w, v, weight));
		return true;
	}

	// returns true falls Knoten vorhanden ist
	@Override
	public boolean containsVertex(V v) {
		return adjacencyList.containsKey(v);
	}

	@Override
	public boolean containsEdge(V v, V w) {

		if (!containsVertex(v) || (!containsVertex(w))) {
			throw new IllegalArgumentException();
		}

		return (adjacencyList.get(v).containsKey(w));

	}

	@Override
	public double getWeight(V v, V w) {
		if (!containsVertex(v) || (!containsVertex(w))) {
			throw new IllegalArgumentException();
		}

		if (this.containsEdge(v, w)) {
			return adjacencyList.get(v).get(w);
		} else {
			return 0;
		}
	}

	@Override
	public int getNumberOfVertexes() {
		return adjacencyList.size();
	}

	@Override
	public int getNumberOfEdges() {
		return edgeList.size()/2;
	}

	@Override
	public List<V> getVertexList() {
		LinkedList<V> vertexList = new LinkedList<V>();
		Set<V> keys = adjacencyList.keySet();
		for (V x : keys) {
			vertexList.add(x);
		}
		return vertexList;
	}

	@Override
	public List<Edge<V>> getEdgeList() {
		return edgeList;
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		LinkedList<V> adjVertexList = new LinkedList<V>();
		Set<V> keys = adjacencyList.get(v).keySet();
		for (V x : keys) {
			adjVertexList.add(x);
		}
		return adjVertexList;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		
		LinkedList<Edge<V>> incidentEdgeList = new LinkedList<Edge<V>>();
		for (Edge<V> x : edgeList) {
			if (x.getSource().equals(v)) {
				incidentEdgeList.add(x);
			}
		}
		return incidentEdgeList;
	}

	@Override
	public int getDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		
		return getIncidentEdgeList(v).size();
	}

}
