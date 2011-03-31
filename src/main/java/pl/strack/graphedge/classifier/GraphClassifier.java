package pl.strack.graphedge.classifier;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

public class GraphClassifier {
	
	private enum VertexSearchMark {
		WHITE, GREY, BLACK 
	}
	
	private enum VertexDistanceMark {
		EVEN/*parzysty*/, ODD/*nieparzysty*/, NONE
	}
	
	private boolean isBipartite(Graph graph) {
		Set<Integer> vertices = graph.vertexSet();
		int verticesNumber = vertices.size();
		VertexSearchMark[] vertexState = new VertexSearchMark[verticesNumber];
		VertexDistanceMark[] vertexDistance = new VertexDistanceMark[verticesNumber];
		
		//set initial state of each vertex in the graph
		for(int i = 0; i < verticesNumber; ++i) {
			vertexState[i] = VertexSearchMark.WHITE;
			vertexDistance[i] = VertexDistanceMark.NONE;
		}
		
		Iterator<Integer> verticesIt = vertices.iterator();
		Integer vertex1 = verticesIt.next();
		Integer vertex2;
		Queue<Integer> verticesQueue = new LinkedList<Integer>();
		Set<Edge> edges;
		
		//initialize bfs
		VertexDistanceMark distanceMark = VertexDistanceMark.EVEN;
		vertexState[vertex1.intValue() - 1] = VertexSearchMark.GREY;
		vertexDistance[vertex1.intValue() - 1] = distanceMark;
		verticesQueue.offer(vertex1);
		
		//loop over every vertex
		while(verticesIt.hasNext()) {
			
			while(( vertex = verticesQueue.poll() )) {
				
				if( vertexDistance[vertex1.intValue() - 1] == VertexDistanceMark.EVEN ) {
					distanceMark = VertexDistanceMark.ODD;
				} else {
					distanceMark = VertexDistanceMark.EVEN;
				}
				
				edges = graph.edgesOf(vertex1);
				for(Edge e : edges) {
					
					if( graph.getEdgeSource(e) == vertex1)
						vertex2 = graph.getEdgeTarget(e);
					else
						vertex2 = graph.getEdgeSource(e);
					
					//check if the vertex was computed
					if(vertexState[vertex1.intValue() - 1] == VertexStateMark)
						
				}
				
				if(graph.getEdgeSource(edges) == vertex1) { 
					
				}
				if(graph.getEdgeDestination() == )
				for()
			}
			
		}
		
		
		
	}
	
	public GraphType determineGraphType(Graph graph) {
		return GraphType.SIMPLE; //TODO: real classification
	}
	
}
