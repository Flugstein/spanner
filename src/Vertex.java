import java.util.ArrayList;
import java.util.HashMap;

public class Vertex implements Comparable<Vertex> {

	private final Integer id;
	private HashMap<Vertex, Boolean> adjMap;  // for faster isAdj()
	private ArrayList<Vertex> adjList;  // for faster BFS

	public Vertex(int id) {
		this.id = id;
	}

	protected void initAdj(int size) {
		adjMap = new HashMap<>(size);
		adjList = new ArrayList<>(size);
	}

	protected void addAdj(Vertex k) {
		adjMap.put(k, true);
		adjList.add(k);
	}

	public ArrayList<Vertex> getAdj() {
		return adjList;
	}

	public boolean isAdj(Vertex v) {
		return adjMap.containsKey(v);  // O(1)
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
