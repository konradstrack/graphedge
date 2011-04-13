package pl.strack.graphedge.painter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

import com.google.inject.Inject;

public class GraphEdgePainter {

	private final GraphClassifier classifier;

	private enum VertexStateMark {
		WHITE, GREY, BLACK
	}

	@Inject
	public GraphEdgePainter(GraphClassifier classifier) {
		this.classifier = classifier;
	}

	private Integer getMaxDegreeVertex(Graph graph) {

		Set<Integer> vertices = graph.vertexSet();
		Integer vertex = vertices.iterator().next();
		for (Integer v : vertices) {
			if (graph.degreeOf(v) > graph.degreeOf(vertex)) {
				vertex = v;
			}
		}

		return vertex;
	}

	private boolean paintSimpleGraph(Graph graph, List<Edge> edges, int maxVertexDegree, int index) {

		if (edges.size() == index) {
			return checkColoring(graph);
		}

		Edge edge = edges.get(index);
		for (int col = 1; col <= maxVertexDegree; ++col) {

			edge.setColor(col);
			if (paintSimpleGraph(graph, edges, maxVertexDegree, index + 1)) {
				return true;
			}

		}

		return false;

	}

	private int paintSimpleGraph(Graph graph) throws GraphColoringNotFoundException {

		List<Edge> edges = new ArrayList<Edge>(graph.edgeSet());
		int maxVertexDegree = graph.degreeOf(getMaxDegreeVertex(graph));
		int initialIndex = 0;

		if (paintSimpleGraph(graph, edges, maxVertexDegree, initialIndex)) {
			return maxVertexDegree;
		}
		if (paintSimpleGraph(graph, edges, maxVertexDegree + 1, initialIndex)) {
			return maxVertexDegree + 1;
		}

		throw new GraphColoringNotFoundException();

	}

	private int paintBipartiteGraph(Graph graph) {
		return 0;
	}

	private void paintTree(Graph graph, Integer vertex, VertexStateMark[] vertexState,
			int parentEdgeColor) {

		vertexState[vertex.intValue() - 1] = VertexStateMark.GREY;

		Set<Edge> edges = graph.edgesOf(vertex);
		Integer nextVertex;
		int lastColor = 1;
		for (Edge e : edges) {

			if (graph.getEdgeSource(e) == vertex) {
				nextVertex = graph.getEdgeTarget(e);
			} else {
				nextVertex = graph.getEdgeSource(e);
			}

			if (vertexState[nextVertex.intValue() - 1] == VertexStateMark.WHITE) {

				if (lastColor == parentEdgeColor) {
					e.setColor(++lastColor);
				} else {
					e.setColor(lastColor);
				}

				paintTree(graph, nextVertex, vertexState, lastColor);
				++lastColor;

			}

		}

		vertexState[vertex.intValue() - 1] = VertexStateMark.BLACK;

	}

	private int paintTree(Graph graph) {

		int verticesNumber = graph.vertexSet().size();
		VertexStateMark[] vertexState = new VertexStateMark[verticesNumber];
		for (int i = 0; i < verticesNumber; ++i) {
			vertexState[i] = VertexStateMark.WHITE;
		}

		Integer vertex = graph.vertexSet().iterator().next();
		paintTree(graph, vertex, vertexState, 0);

		// tree is a bipartite graph, so: chromatic index == max vertex degree
		return graph.degreeOf(getMaxDegreeVertex(graph));

	}

	public int paintGraph(Graph graph) throws GraphColoringNotFoundException {
		int colors;
		GraphType type = classifier.determineGraphType(graph);

		switch (type) {
		case TREE:
			colors = paintTree(graph);
			break;
		case BIPARTITE:
			colors = paintSimpleGraph(graph);
			break;
		case SIMPLE:
		default:
			colors = paintSimpleGraph(graph);
			break;
		}

		return colors;
	}

	public boolean checkColoring(Graph graph) {

		boolean[] isColorUsed = new boolean[graph.degreeOf(getMaxDegreeVertex(graph)) + 1];

		for (Integer v : graph.vertexSet()) {

			for (int i = 0; i < isColorUsed.length; ++i) {
				isColorUsed[i] = false;
			}

			for (Edge e : graph.edgesOf(v)) {

				if (isColorUsed[e.getColor() - 1]) {
					return false;
				} else {
					isColorUsed[e.getColor() - 1] = true;
				}

			}

		}

		return true;
	}

}
