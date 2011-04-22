package pl.strack.graphedge.app;

public class SwingGraphEdgeModule extends GraphEdgeModule {
	@Override
	protected void configure() {
		bind(GraphEdgeRunner.class).to(SwingRunner.class);
	}
}
