package pl.strack.graphedge.painter;

import java.util.Set;

import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

public class GraphEdgePainter {

	private GraphClassifier classifier;

	private enum VertexStateMark {
		WHITE, GREY, BLACK
	}

	public GraphEdgePainter() {
		classifier = new GraphClassifier();
	}

	private Integer getMaxDegreeVertex(Graph graph) {

		Set<Integer> vertices = graph.vertexSet();
		Integer vertex = vertices.iterator().next();
		for (Integer v : vertices) {
			if (graph.edgesOf(v).size() > graph.edgesOf(vertex).size()) {
				vertex = v;
			}
		}

		return vertex;
	}

	private int paintSimpleGraph(Graph graph) {

	}

	private int paintBipartiteGraph(Graph graph) {

	}

	private void paintTree(Graph graph, Integer vertex, VertexStateMark[] vertexState, int parentEdgeColor) {

		vertexState[vertex.intValue() -1] = VertexStateMark.GREY;
		
		Set<Edge> edges = graph.edgesOf(vertex);
		Integer nextVertex;
		int lastColor = 1;
		for (Edge e : edges) {
			
			if (graph.getEdgeSource(e) == vertex) {
				nextVertex = graph.getEdgeTarget(e);
			} else {
				nextVertex = graph.getEdgeSource(e);
			}

			if(vertexState[nextVertex.intValue() - 1] == VertexStateMark.WHITE) {
				
				if(lastColor == parentEdgeColor) {
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
		for(int i = 0; i < verticesNumber; ++i) {
			vertexState[i] = VertexStateMark.WHITE;
		}
		
		Integer vertex = graph.vertexSet().iterator().next();
		paintTree(graph, vertex, vertexState, 0);
		
		//tree is a bipartite graph, so: chromatic index == max vertex degree
		return getMaxDegreeVertex(graph);
		
	}

	public int paintGraph(Graph graph) {
		int colors;
		GraphType type = classifier.determineGraphType(graph);

		switch (type) {
		case TREE:
			colors = paintTree(graph);
			break;
		case BIPARTITE:
			colors = paintBipartiteGraph(graph);
			break;
		case SIMPLE:
		default:
			colors = paintSimpleGraph(graph);
			break;
		}

		return colors;
	}

}
