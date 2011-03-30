package pl.strack.graphedge.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jgrapht.ext.IntegerNameProvider;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

import com.google.inject.Inject;


public class FileGraphBuilder {
	
	private final IntegerNameProvider<Integer> nameProvider;
	
	@Inject
	public FileGraphBuilder(IntegerNameProvider<Integer> nameProvider) {
		this.nameProvider = nameProvider;
	}
	
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
