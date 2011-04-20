package pl.strack.graphedge.painter;

import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

import com.google.inject.Inject;

public class GraphEdgePainter {

	private final GraphClassifier classifier;

	@Inject
	public GraphEdgePainter(GraphClassifier classifier) {
		this.classifier = classifier;
	}

	public int paintGraph(Graph graph) throws GraphColoringNotFoundException {
		int colors;
		GraphType type = classifier.determineGraphType(graph);

		switch (type) {
		case TREE:
			colors = (new TreePainter()).paint(graph);
			break;
		case BIPARTITE:
			colors = (new SimplePainter()).paint(graph);
			break;
		case SIMPLE:
		default:
			colors = (new SimplePainter()).paint(graph);
			break;
		}

		return colors;
	}

}
