package pl.strack.graphedge.app;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GraphEdge {

	private GraphEdge() {
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GraphEdgeModule());
		GraphEdgeRunner runner = injector.getInstance(GraphEdgeRunner.class);

		runner.run();
	}

}
