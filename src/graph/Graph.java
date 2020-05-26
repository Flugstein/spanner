package graph;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices = new ArrayList<>();
	protected int nVertices = 0;
	protected int nEdges = 0;

	public Graph() {}

	public Graph(Path filePath) throws IOException {
		this(new FileInputStream(filePath.toFile()));
	}

	public Graph(InputStream is) throws IOException {
		long startTime = System.currentTimeMillis();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			nVertices = Integer.parseInt(br.readLine().split(" ")[0]);
			vertices = new ArrayList<>(nVertices);
			for (int i = 0; i < nVertices; i++)
				vertices.add(new Vertex(this, i));

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
					nEdges++;  // count like directed edges (every edge gets counted twice)
				}
				id++;
			}
		}

		System.out.println("Graph read in " + ((System.currentTimeMillis() - startTime) / 1000) + "s");
	}

	public void writeToFile(Path outPath) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath.toFile())))) {
			bw.write(nVertices + " " + nEdges);
			bw.newLine();
			for (Vertex v : vertices) {
				ArrayList<Vertex> adjVertices = v.getAdj();
				if (adjVertices.size() > 0) {
					bw.write(adjVertices.get(0).getId().toString());
					for (int i = 1; i < adjVertices.size(); i++)
						bw.write(" " + adjVertices.get(i).getId().toString());
					bw.newLine();
				}
			}
		}
	}

	public Graph createEdgelessCopy() {
		Graph new_g = new Graph();

		ArrayList<Vertex> new_vertices = new ArrayList<>(nVertices);
		for (Vertex v : vertices)
			new_vertices.add(new Vertex(new_g, v.getId()));

		new_g.vertices = new_vertices;
		new_g.nVertices = new_vertices.size();
		new_g.nEdges = 0;

		return new_g;
	}

	public Vertex getVertex(int id) {
		return vertices.get(id);
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public int getnVertices() {
		return nVertices;
	}

	public int getnEdges() {
		return nEdges;
	}
}
