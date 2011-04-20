package pl.strack.graphedge.core;

import java.util.Set;

import org.jgrapht.graph.SimpleGraph;

public class Graph extends SimpleGraph<Integer, Edge> {

	private static final long serialVersionUID = 5760792906022005614L;

	public Graph(Class<? extends Edge> edgeClass) {
		super(edgeClass);
	}

	public Integer getMaxDegreeVertex() {

		Set<Integer> vertices = vertexSet();
		Integer vertex = vertices.iterator().next();
		for (Integer v : vertices) {
			if (degreeOf(v) > degreeOf(vertex)) {
				vertex = v;
			}
		}

		return vertex;
	}

	public boolean checkEdgeColoring() {

		boolean[] isColorUsed = new boolean[degreeOf(getMaxDegreeVertex()) + 1];

		for (Integer v : vertexSet()) {

			for (int i = 0; i < isColorUsed.length; ++i) {
				isColorUsed[i] = false;
			}

			for (Edge e : edgesOf(v)) {

				if (isColorUsed[e.getColor() - 1]) {
					return false;
				} else {
					isColorUsed[e.getColor() - 1] = true;
				}

			}

		}

		return true;
	}

}
