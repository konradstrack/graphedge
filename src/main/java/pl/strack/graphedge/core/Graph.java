package pl.strack.graphedge.core;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;

public class Graph extends AbstractBaseGraph<String, Edge> {

	private static final long serialVersionUID = 5760792906022005614L;

	public Graph(EdgeFactory<String, Edge> ef, boolean allowMultipleEdges, boolean allowLoops) {
		super(ef, allowMultipleEdges, allowLoops);
	}

}
