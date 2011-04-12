package pl.strack.graphedge.builder;

import org.jgrapht.VertexFactory;
import org.jgrapht.generate.RandomGraphGenerator;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

public class RandomGraphBuilder {

	public Graph build(int vertexes, int edges) {
		RandomGraphGenerator<Integer, Edge> generator = new RandomGraphGenerator<Integer, Edge>(
				vertexes, edges);

		Graph graph = new Graph(Edge.class);
		generator.generateGraph(graph, new VertexFactory<Integer>() {
			int i = 0;

			public Integer createVertex() {
				return ++i;
			}
		}, null);
		return graph;
	}
	
}
