import java.util.Random;

public class StretchFactor {

	/**
	 * Requires both graphs to have the same vertices
	 */
	public static double avgStretch(Graph original, Graph spanner, int sampleSize) {
		Random rand = new Random();
		final int numberOfVertices = original.getNumberOfVertices();
		BFS bfs = new BFS(numberOfVertices);
		double stretchFactorSum = 0.0;

		for (int i = 0; i < sampleSize; i++) {
			// Sample random vertices
			int sourceVertexId = rand.nextInt(numberOfVertices);
			int destVertexId = rand.nextInt(numberOfVertices);

			Vertex originalSource = original.getVertices().get(sourceVertexId);
			Vertex originalDest = original.getVertices().get(destVertexId);
			Vertex spannerSource = spanner.getVertices().get(sourceVertexId);
			Vertex spannerDest = spanner.getVertices().get(destVertexId);

			// compute distance
			int originalDistance = bfs.bfsPair(originalSource, originalDest);
			int spannerDistance = bfs.bfsPair(spannerSource, spannerDest);

			stretchFactorSum += spannerDistance / (double) originalDistance;
		}

		return stretchFactorSum / sampleSize;
	}
}
