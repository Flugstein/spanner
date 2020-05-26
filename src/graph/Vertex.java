package graph;

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {

	private final Graph g;
	private final Integer id;
	private ArrayList<Vertex> adjList = new ArrayList<>();

	public Vertex(Graph g, int id) {
		this.g = g;
		this.id = id;
	}

	protected void initAdj(int size) {
		adjList = new ArrayList<>(size);
	}

	public boolean addAdj(Vertex u) {
		g.nEdges++;
		return adjList.add(u);
	}

	public boolean removeAdj(Vertex u) {
		return adjList.remove(u);
	}

	public ArrayList<Vertex> getAdj() {
		return adjList;
	}

	public boolean isAdj(Vertex v) {
		return adjList.contains(v);
	}

	public int getDegree() {
		return adjList.size();
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int compareTo(Vertex vertex) {
		return this.getId().compareTo(vertex.getId());
	}
}
