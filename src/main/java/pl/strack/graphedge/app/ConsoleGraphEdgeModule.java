package pl.strack.graphedge.app;


public class ConsoleGraphEdgeModule extends GraphEdgeModule {
	@Override
	protected void configure() {
		bind(GraphEdgeRunner.class).to(ConsoleRunner.class);
	}
}
