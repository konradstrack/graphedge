package pl.strack.graphedge.painter;

import org.jgraph.JGraph;

public class ColoredGraphData {

	private JGraph jgraph;
	private int numberOfColors;
	private int numberOfVertices;
	private int numberOfEdges;

	public ColoredGraphData(JGraph jgraph, int numberOfColors, int numberOfVertices,
			int numberOfEdges) {
		this.jgraph = jgraph;
		this.numberOfColors = numberOfColors;
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = numberOfEdges;
	}

	public JGraph getJgraph() {
		return jgraph;
	}

	public int getNumberOfColors() {
		return numberOfColors;
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}

}
