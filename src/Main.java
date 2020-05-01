import java.io.IOException;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		Graph original = new Graph(Paths.get("resources/graphs/rmat.metis"));
		Graph spanner = new Graph(Paths.get("resources/graphs/rmat.metis"));

		double avgStretch = StretchFactor.avgStretch(original, spanner, 100);
		System.out.println(avgStretch);
	}
}
