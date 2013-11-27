package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;



public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V>{
	
	
	private HashMap<V, HashMap<V, Double>> prevAdjacencyList;
	private HashMap<V, HashMap<V, Double>> nextAdjacencyList;
	private LinkedList<Edge<V>> edgeList = new LinkedList<Edge<V>>();
	
	
	public AdjacencyListDirectedGraph() {
		prevAdjacencyList = new HashMap<V, HashMap<V, Double>>();
		nextAdjacencyList = new HashMap<V, HashMap<V, Double>>();
	}
	
	@Override
	public boolean addVertex(V v) {
		if(!containsVertex(v)) {
			nextAdjacencyList.put(v, new HashMap<V, Double>());
			prevAdjacencyList.put(v, new HashMap<V, Double>());
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
		if(!containsVertex(v) && !containsVertex(w) && v == w) {
			throw new IllegalArgumentException();
		} else if(containsEdge(v,w)) {
			return false;
		}
		nextAdjacencyList.get(v).put(w, weight);
		prevAdjacencyList.get(w).put(v, weight);
		edgeList.add(new Edge<V>(v,w,weight));
		return true;
	}
	
	
	@Override
	public boolean containsVertex(V v) {
		//TODO pruefung in beide richtungen???
		return nextAdjacencyList.containsKey(v);		
	}
	@Override
	public boolean containsEdge(V v, V w) {
		//TODO pruefung in beide richtungen???
		return nextAdjacencyList.get(v).containsKey(w);
	}
	@Override
	public double getWeight(V v, V w) {
		return nextAdjacencyList.get(v).get(w);
	}
	@Override
	public int getNumberOfVertexes() {
		return nextAdjacencyList.size();
	}
	@Override
	public int getNumberOfEdges() {
		return edgeList.size();
	}
	@Override
	public List<V> getVertexList() {
		LinkedList<V> vertexList = new LinkedList<V>();
		Set<V> keys = nextAdjacencyList.keySet();
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
		Set<V> keys = nextAdjacencyList.get(v).keySet();
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
	public int getInDegree(V v) {
		return prevAdjacencyList.get(v).size();
	}
	
	
	@Override
	public int getOutDegree(V v) {
		return nextAdjacencyList.get(v).size();
	}
	
	
	@Override
	public List<V> getPredecessorVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		LinkedList<V> prevVs = new LinkedList<V>();
		for(V x : prevAdjacencyList.get(v).keySet()) {
			prevVs.add(x);
		}
		return prevVs;
	}
	
	
	@Override
	public List<V> getSuccessorVertexList(V v) {
		return getAdjacentVertexList(v);
	}
	@Override
	public List<Edge<V>> getOutgoingEdgeList(V v) {
		return getIncidentEdgeList(v);
	}
	@Override
	public List<Edge<V>> getIncomingEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}
		
		LinkedList<Edge<V>> incomingEdgeList = new LinkedList<Edge<V>>();
		for (Edge<V> x : edgeList) {
			if (x.getTarget().equals(v)) {
				incomingEdgeList.add(x);
			}
		}
		return incomingEdgeList;
	}
	
	
	
}
