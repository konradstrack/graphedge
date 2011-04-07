package pl.strack.graphedge.core;

import org.jgrapht.graph.SimpleGraph;

public class Graph extends SimpleGraph<Integer, Edge> {

	private static final long serialVersionUID = 5760792906022005614L;

	public Graph(Class<? extends Edge> edgeClass) {
		super(edgeClass);
	}

}
