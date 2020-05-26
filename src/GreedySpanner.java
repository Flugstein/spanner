import algo.BFS;
import graph.Graph;
import graph.Vertex;

public class GreedySpanner {

	public static Graph getSpanner(Graph g, int k) {
		BFS bfs = new BFS(g.getnVertices());
		Graph s = g.createEdgelessCopy();

		int old_progress = 0;
		System.out.println("Computing greedy spanner with k=" + k + "...");

		for (Vertex v : g.getVertices()) {
			Vertex v_s = s.getVertex(v.getId());

			for (Vertex u : v.getAdj()) {
				Vertex u_s = s.getVertex(u.getId());

				int dist = bfs.bfsPair(v_s, u_s);
				if (dist > (2*k - 1) || dist == -1) {
					v_s.addAdj(u_s);
					u_s.addAdj(v_s);
				}
			}

			int progress = (v_s.getId() * 100) / s.getnVertices();
			if (progress != old_progress) {
				old_progress = progress;
				System.out.print("\r" + progress + "%");
			}
		}
		System.out.println();

		return s;
	}
}
