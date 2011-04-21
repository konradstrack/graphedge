package pl.strack.graphedge.painter;

import java.util.ArrayList;
import java.util.List;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

/**
 * Implementation of <code>Painter</code> interface, responsible for brute force coloring any simple
 * graph.
 */
public class SimplePainter implements Painter {

	public int paint(Graph graph) throws GraphColoringNotFoundException {
		List<Edge> edges = new ArrayList<Edge>(graph.edgeSet());
		int maxVertexDegree = graph.degreeOf(graph.getMaxDegreeVertex());
		int initialIndex = 0;

		if (paintSimpleGraph(graph, edges, maxVertexDegree, initialIndex)) {
			return maxVertexDegree;
		}
		if (paintSimpleGraph(graph, edges, maxVertexDegree + 1, initialIndex)) {
			return maxVertexDegree + 1;
		}

		throw new GraphColoringNotFoundException();

	}

	/**
	 * Recursive function for brute force coloring of the graph.
	 * 
	 * @param graph
	 *            a graph to be painted
	 * @param edges
	 *            list of graph's edges
	 * @param maxVertexDegree
	 *            maximum degree of a vertex in graph
	 * @param index
	 *            current edge number
	 * @return true if coloring was successful, false otherwise
	 */
	private boolean paintSimpleGraph(Graph graph, List<Edge> edges, int maxVertexDegree, int index) {

		if (edges.size() == index) {
			return graph.checkEdgeColoring();
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

}
