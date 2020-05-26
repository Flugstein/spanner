package algo;

import algo.BFS;
import graph.Graph;
import graph.Vertex;

import java.util.Random;

public class StretchFactor {

	/**
	 * Requires both graphs to have vertices with the same ids
	 */
	public static double avgStretch(Graph original, Graph spanner, int sampleSize) {
		Random rand = new Random();
		final int nVertices = original.getnVertices();
		BFS bfs = new BFS(nVertices);
		double stretchFactorSum = 0.0;
		int countedSamples = 0;
		int disconnectedVertices = 0;

		for (int i = 0; i < sampleSize; i++) {
			// Sample random vertices
			int sourceVertexId = rand.nextInt(nVertices);
			int destVertexId = rand.nextInt(nVertices);

			Vertex originalSource = original.getVertex(sourceVertexId);
			Vertex originalDest = original.getVertex(destVertexId);
			Vertex spannerSource = spanner.getVertex(sourceVertexId);
			Vertex spannerDest = spanner.getVertex(destVertexId);

			// compute distance
			int originalDist = bfs.bfsPair(originalSource, originalDest);
			int spannerDist = bfs.bfsPair(spannerSource, spannerDest);

			if (originalDist != -1 && spannerDist == -1)
				disconnectedVertices++;

			if (originalDist != -1 && spannerDist != -1 && originalDist != 0) {
				stretchFactorSum += spannerDist / (double) originalDist;
				countedSamples++;
			}

		}

		System.out.println("Vertices that got disconnected in the spanner: " + disconnectedVertices + " / " + sampleSize);
		System.out.println("Vertices not counted because of infinite distance: " + (sampleSize - countedSamples) + " / " + sampleSize);

		return stretchFactorSum / countedSamples;
	}
}
