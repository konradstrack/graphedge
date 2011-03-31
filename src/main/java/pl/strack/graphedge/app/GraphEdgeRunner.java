package pl.strack.graphedge.app;

import java.io.FileNotFoundException;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;

import com.google.inject.Inject;

public class GraphEdgeRunner {
	
	private final FileGraphBuilder builder;
	
	@Inject
	public GraphEdgeRunner(FileGraphBuilder builder) {
		this.builder = builder;
	}

	public void run() {
		try {
			Graph graph = builder.build("src/main/resources/graphs/simple2.g");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
