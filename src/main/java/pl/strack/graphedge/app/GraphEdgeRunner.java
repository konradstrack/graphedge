package pl.strack.graphedge.app;

import java.io.FileNotFoundException;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;

public class GraphEdgeRunner {

	public void run() {
		FileGraphBuilder builder = new FileGraphBuilder();
		try {
			Graph graph = builder.build("src/main/resources/graphs/simple1.g");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
