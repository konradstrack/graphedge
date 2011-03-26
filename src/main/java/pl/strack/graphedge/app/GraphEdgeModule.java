package pl.strack.graphedge.app;

import pl.strack.graphedge.visualizer.JGraphVisualizer;
import pl.strack.graphedge.visualizer.Visualizer;

import com.google.inject.AbstractModule;

public class GraphEdgeModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Visualizer.class).to(JGraphVisualizer.class);
	}
}
