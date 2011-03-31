package pl.strack.graphedge.app;

import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
import javax.swing.JFrame;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.visualizer.JGraphVisualizer;

import com.google.inject.Inject;

public class GraphEdgeRunner {

	private final FileGraphBuilder builder;
	private final JGraphVisualizer visualizer;

	@Inject
	public GraphEdgeRunner(FileGraphBuilder builder, JGraphVisualizer visualizer) {
		this.builder = builder;
		this.visualizer = visualizer;
	}

	public void run() {

		Graph graph = null;
		try {
			graph = builder.build("src/main/resources/graphs/simple2.g");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		display(visualizer.createVisualization(graph));
	}

	private void display(JComponent graph) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("GraphEdge");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));

		frame.getContentPane().add(graph);

		frame.pack();
		frame.setVisible(true);

	}
}
