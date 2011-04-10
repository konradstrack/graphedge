package pl.strack.graphedge.classifier;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

public class GraphClassifier {

	private enum VertexStateMark {
		WHITE, GREY, BLACK
	}

	private enum VertexDistanceMark {
		EVEN/* parzysty */, ODD/* nieparzysty */, NONE
	}

	private boolean isBipartite(Graph graph) {
		Set<Integer> vertices = graph.vertexSet();
		int verticesNumber = vertices.size();
		VertexStateMark[] vertexState = new VertexStateMark[verticesNumber];
		VertexDistanceMark[] vertexDistance = new VertexDistanceMark[verticesNumber];

		// set initial state of each vertex in the graph
		for (int i = 0; i < verticesNumber; ++i) {
			vertexState[i] = VertexStateMark.WHITE;
			vertexDistance[i] = VertexDistanceMark.NONE;
		}

		Iterator<Integer> verticesIt = vertices.iterator();
		Integer vertex1;
		Integer vertex2;
		Queue<Integer> verticesQueue = new LinkedList<Integer>();
		Set<Edge> edges;

		// loop over every vertex
		while (verticesIt.hasNext()) {

			vertex1 = verticesIt.next();
			if (vertexState[vertex1.intValue() - 1] != VertexStateMark.BLACK) {

				VertexDistanceMark distanceMark = VertexDistanceMark.EVEN;
				vertexState[vertex1.intValue() - 1] = VertexStateMark.GREY;
				vertexDistance[vertex1.intValue() - 1] = distanceMark;
				verticesQueue.add(vertex1);

				while (!verticesQueue.isEmpty()) {

					vertex1 = verticesQueue.remove();
					if (vertexDistance[vertex1.intValue() - 1] == VertexDistanceMark.EVEN) {
						distanceMark = VertexDistanceMark.ODD;
					} else {
						distanceMark = VertexDistanceMark.EVEN;
					}

					edges = graph.edgesOf(vertex1);
					for (Edge e : edges) {

						if (graph.getEdgeSource(e) == vertex1) {
							vertex2 = graph.getEdgeTarget(e);
						} else {
							vertex2 = graph.getEdgeSource(e);
						}

						// check if the vertex was computed
						if (vertexState[vertex2.intValue() - 1] == VertexStateMark.WHITE) {
							vertexDistance[vertex2.intValue() - 1] = distanceMark;
							vertexState[vertex2.intValue() - 1] = VertexStateMark.GREY;
							verticesQueue.add(vertex2);
						} else {
							if (vertexDistance[vertex2.intValue() - 1] == vertexDistance[vertex1
									.intValue() - 1]) {
								return false;
							}
						}

					}

					vertexState[vertex1.intValue() - 1] = VertexStateMark.BLACK;
				}
			}
		}

		return true;
	}

	private boolean isTree(Integer vertex, VertexStateMark[] vertexState, Graph graph) {

		int computedVertices = 0;
		vertexState[vertex.intValue() - 1] = VertexStateMark.GREY;

		Set<Edge> edges = graph.edgesOf(vertex);
		Integer nextVertex;
		for (Edge e : edges) {

			if (graph.getEdgeSource(e) == vertex) {
				nextVertex = graph.getEdgeTarget(e);
			} else {
				nextVertex = graph.getEdgeSource(e);
			}

			// check if the vertex was computed
			if (vertexState[nextVertex.intValue() - 1] == VertexStateMark.WHITE) {
				if (!isTree(nextVertex, vertexState, graph)) {
					return false;
				}
			} else {
				++computedVertices;
				if (computedVertices == 2) {
					return false;
				}
			}

		}

		vertexState[vertex.intValue() - 1] = VertexStateMark.BLACK;
		return true;

	}

	private boolean isTree(Graph graph) {

		int verticesNumber = graph.vertexSet().size();
		VertexStateMark[] vertexState = new VertexStateMark[verticesNumber];

		// set initial state of each vertex in the graph
		for (int i = 0; i < verticesNumber; ++i) {
			vertexState[i] = VertexStateMark.WHITE;
		}

		Integer vertex = graph.vertexSet().iterator().next();
		if (isTree(vertex, vertexState, graph)) {

			Set<Integer> vertices = graph.vertexSet();
			for (Integer v : vertices) {
				if (vertexState[v.intValue() - 1] == VertexStateMark.WHITE) {
					return false;
				}
			}

			return true;

		} else {
			return false;
		}
	}

	public GraphType determineGraphType(Graph graph) {

		if (isTree(graph)) {
			return GraphType.TREE;
		} else if (isBipartite(graph)) {
			return GraphType.BIPARTITE;
		} else {
			return GraphType.SIMPLE;
		}

	}

}
