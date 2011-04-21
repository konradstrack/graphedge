package pl.strack.graphedge.painter;

import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

/**
 * Common interface of graph edge painters.
 */
public interface Painter {

	/**
	 * @param graph
	 *            graph to be painted
	 * @return number of colors that was used
	 * @throws GraphColoringNotFoundException
	 */
	int paint(Graph graph) throws GraphColoringNotFoundException;

}
