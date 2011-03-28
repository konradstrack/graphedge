package pl.strack.graphedge.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;


public class FileGraphBuilder {
	
	public Graph build(String filename) throws FileNotFoundException {
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