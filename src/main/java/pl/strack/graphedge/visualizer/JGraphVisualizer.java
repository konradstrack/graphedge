package pl.strack.graphedge.visualizer;

import javax.swing.JComponent;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

public class JGraphVisualizer {

	public JComponent createVisualization(Graph graph) {
		JGraphModelAdapter<Integer, Edge> modelAdapter = new JGraphModelAdapter<Integer, Edge>(
				graph);
		JGraph jgraph = new JGraph(modelAdapter);

		return jgraph;
	}

}
