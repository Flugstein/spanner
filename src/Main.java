import java.io.IOException;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) throws IOException {
		Graph original = new Graph(Path.of("resources/graphs/pokec_10000.metis"));
		Graph spanner = new Graph(Path.of("resources/graphs/pokec_10000.metis"));

		double avgStretch = StretchFactor.avgStretch(original, spanner, 100);
		System.out.println(avgStretch);
	}
}
