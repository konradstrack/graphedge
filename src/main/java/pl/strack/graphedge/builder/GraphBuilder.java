package pl.strack.graphedge.builder;

import pl.strack.graphedge.core.Graph;

/**
 * Common interface of graph builders.
 */
public interface GraphBuilder {

	/**
	 * Performs building of a graph.
	 * 
	 * @return a build graph
	 * @throws Exception
	 */
	Graph build() throws Exception;

}
