package algo;

import graph.Vertex;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Breadth-first search
 */
public class BFS {

	private final int[] distances;
	private final Deque<Vertex> q;

	public BFS(int graphSize) {
		distances = new int[graphSize];
		q = new ArrayDeque<>(graphSize);
	}

	/**
	 * Distances from source to all vertices in the graph
	 * return array contains -1 if distance is infinite
	 */
	public int[] sssp(Vertex source) {
		Arrays.fill(distances, -1);
		q.clear();

		distances[source.getId()] = 0;
		q.add(source);

		while (!q.isEmpty()) {
			Vertex u = q.remove();
			for (Vertex v : u.getAdj()) {
				if (distances[v.getId()] == -1) {
					distances[v.getId()] = distances[u.getId()] + 1;
					q.add(v);
				}
			}
		}
		return distances;
	}

	/**
	 * Distance between source and dest
	 * returns -1 if distance in infinite
	 */
	public int bfsPair(Vertex source, Vertex dest) {
		Arrays.fill(distances, -1);
		q.clear();

		distances[source.getId()] = 0;
		q.add(source);

		while (!q.isEmpty()) {
			Vertex u = q.remove();
			for (Vertex v : u.getAdj()) {
				if (v.equals(dest))
					return distances[u.getId()] + 1;
				if (distances[v.getId()] == -1) {
					distances[v.getId()] = distances[u.getId()] + 1;
					q.add(v);
				}
			}
		}
		return -1;  // dest unreachable
	}
}
