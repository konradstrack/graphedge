package pl.strack.graphedge.painter;

import org.jgraph.JGraph;

import pl.strack.graphedge.classifier.GraphType;

public class ColoredGraphData {

	private JGraph jgraph;
	private final int numberOfColors;
	private final int numberOfVertices;
	private final int numberOfEdges;
	private final long coloringTime;
	private final long classificationTime;
	private final GraphType type;

	public ColoredGraphData(JGraph jgraph, int numberOfColors, int numberOfVertices,
			int numberOfEdges, GraphType type, long coloringTime, long classificationTime) {
		this.jgraph = jgraph;
		this.numberOfColors = numberOfColors;
		this.numberOfVertices = numberOfVertices;
		this.numberOfEdges = numberOfEdges;
		this.coloringTime = coloringTime;
		this.classificationTime = classificationTime;
		this.type = type;
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
	
	public GraphType getType() {
		return type;
	}

	public long getColoringTime() {
		return coloringTime;
	}

	public long getClassificationTime() {
		return classificationTime;
	}

}
