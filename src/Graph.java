import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	private final String name;
	private long readTime;
	private int numberOfVertices = 0;
	private int numberOfEdges = 0;

	public Graph(Path filePath) throws IOException {
		this(filePath.getFileName().toString(), new FileInputStream(filePath.toFile()));
	}

	/**
	 * @param is InputStream to graph file (in .metis format with edges in both directions and sorted adjacency lists)
	 */
	public Graph(String name, InputStream is) throws IOException {
		if (!name.substring(name.lastIndexOf('.')).equals(".metis"))
			throw new RuntimeException("Input file " + name + " is not in .metis format!");
		this.name = name;
		readGraph(is);
	}

	private void readGraph(InputStream is) throws IOException {
		long startTime = System.currentTimeMillis();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			numberOfVertices = Integer.parseInt(br.readLine().split(" ")[0]);
			vertices = new ArrayList<>(numberOfVertices);
			for (int i = 0; i < numberOfVertices; i++)
				vertices.add(new Vertex(i));

			// add vertices and edges
			int id = 0;
			while (br.ready()) {
				Vertex sourceVertex = vertices.get(id);
				String[] adjVertices = br.readLine().split(" ");
				sourceVertex.initAdj(adjVertices.length);
				for (String adjVertex : adjVertices) {
					int sinkId = Integer.parseInt(adjVertex) - 1;  // using ids starting from 0
					Vertex sinkVertex = vertices.get(sinkId);
					sourceVertex.addAdj(sinkVertex);
					numberOfEdges++;
				}
				id++;
			}

			numberOfEdges = numberOfEdges / 2;  // count undirected
		}
		readTime = System.currentTimeMillis() - startTime;
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public String getName() {
		return name;
	}

	public long getReadTime() {
		return readTime;
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}
}
