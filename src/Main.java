import algo.StretchFactor;
import graph.Graph;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		Graph original = new Graph(Paths.get("resources/graphs/rmat.metis"));
		int k = 3;
		Graph spanner = GreedySpanner.getSpanner(original, k);
		spanner.writeToFile(Paths.get("resources/graphs/rmat_greedy.metis"));

		double avgStretch = StretchFactor.avgStretch(original, spanner, 100);
		System.out.println("Average stretch factor: " + avgStretch);
	}
}
