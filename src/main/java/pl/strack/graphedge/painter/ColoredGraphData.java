package pl.strack.graphedge.painter;

import org.jgraph.JGraph;

public class ColoredGraphData {

	private JGraph jgraph;
	private final int numberOfColors;
	private final int numberOfVertices;
	private final int numberOfEdges;
	private final long coloringTime;

	public ColoredGraphData(JGraph jgraph, int numberOfColors, int numberOfVertices,
			int numberOfEdges, long coloringTime) {
		this.jgraph = jgraph;
		this.numberOfColors = numberOfColors;
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = numberOfEdges;
		this.coloringTime = coloringTime;
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
	
	public long getColoringTime() {
		return coloringTime;
	}

}
