package pl.strack.graphedge.painter;

import java.util.Set;

import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

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
			System.out.println(graph.degreeOf(v) + "\n");
			if(graph.degreeOf(v) > graph.degreeOf(vertex)) {
				vertex = v;
			}
		}

		return vertex;
	}

	private int paintSimpleGraph(Graph graph) {
		return 0;
	}

	private int paintBipartiteGraph(Graph graph) {
		return 0;
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
		return graph.degreeOf( getMaxDegreeVertex(graph) );
		
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
	
	public boolean checkColoring(Graph graph) {
		
		Set<Edge> edges = graph.edgeSet();
		Integer sourceVertex;
		Integer destVertex;
		int color;
		for(Edge e : edges) {
			
			color = e.getColor();
			sourceVertex = graph.getEdgeSource(e);
			destVertex = graph.getEdgeTarget(e);
			
			for(Edge se : graph.edgesOf(sourceVertex)) {
				if(se.getColor() == color) {
					return false;
				}
			}
			for(Edge de : graph.edgesOf(destVertex)) {
				if(de.getColor() == color){
					return false;
				}
			}
		}
		
		return true;
	}

}
