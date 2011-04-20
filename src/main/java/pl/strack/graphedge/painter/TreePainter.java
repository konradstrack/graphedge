package pl.strack.graphedge.painter;

import java.util.Set;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

public class TreePainter implements Painter {

	private enum VertexStateMark {
		WHITE, GREY, BLACK
	}

	public int paint(Graph graph) {

		int verticesNumber = graph.vertexSet().size();
		VertexStateMark[] vertexState = new VertexStateMark[verticesNumber];
		for (int i = 0; i < verticesNumber; ++i) {
			vertexState[i] = VertexStateMark.WHITE;
		}

		Integer vertex = graph.vertexSet().iterator().next();
		paintTree(graph, vertex, vertexState, 0);

		// tree is a bipartite graph, so: chromatic index == max vertex degree
		return graph.degreeOf(graph.getMaxDegreeVertex());

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

}
