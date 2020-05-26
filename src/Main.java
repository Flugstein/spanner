import algo.StretchFactor;
import graph.Graph;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		// read original
		Graph original = new Graph(Paths.get("resources/graphs/rmat.metis"));

		// compute spanner
		int k = 3;
		Graph greedySpanner = GreedySpanner.getSpanner(original, k);
		greedySpanner.writeToFile(Paths.get("resources/graphs/rmat_greedy.metis"));

		// compute stretch factor of spanner
		double avgStretch = StretchFactor.avgStretch(original, greedySpanner, 1000);
		System.out.println("Average stretch factor: " + avgStretch);
	}
}
