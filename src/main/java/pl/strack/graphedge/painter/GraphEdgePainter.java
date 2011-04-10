package pl.strack.graphedge.painter;

import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Graph;

public class GraphEdgePainter {

	private GraphClassifier classifier;

	public GraphEdgePainter() {
		classifier = new GraphClassifier();
	}

	private void paintSimpleGraph(Graph graph) {

	}

	private void paintBipartiteGraph(Graph graph) {

	}

	private void paintTree(Graph graph) {

	}

	public void paintGraph(Graph graph) {
		GraphType type = classifier.determineGraphType(graph);

		switch (type) {
		case TREE:
			paintTree(graph);
			break;
		case BIPARTITE:
			paintBipartiteGraph(graph);
			break;
		case SIMPLE:
		default:
			paintSimpleGraph(graph);
			break;
		}
	}

}
