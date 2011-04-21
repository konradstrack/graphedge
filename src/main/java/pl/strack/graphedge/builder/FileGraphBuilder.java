package pl.strack.graphedge.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;


/**
 * Class for reading graphs from file.
 */
public class FileGraphBuilder implements GraphBuilder {
	
	private final String filename;
	
	public FileGraphBuilder(String filename) {
		this.filename = filename;
	}
	
	public Graph build() throws FileNotFoundException {
		Graph graph = new Graph(Edge.class);

		Scanner scanner = new Scanner(new File(filename));
		
		int vertices = scanner.nextInt();
		for (int i = 1; i <= vertices; ++i) {
			graph.addVertex(i);
		}
		
		for (int i = 1; i <= vertices; ++i) {
			int adjacent = scanner.nextInt();
			for (int j = 0; j < adjacent; ++j) {
				graph.addEdge(i, scanner.nextInt());
			}
		}
		
		return graph;
	}
	
}
