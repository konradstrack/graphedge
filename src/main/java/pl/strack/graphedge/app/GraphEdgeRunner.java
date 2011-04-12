package pl.strack.graphedge.app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.builder.RandomGraphBuilder;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.visualizer.JGraphVisualizer;

import com.google.inject.Inject;

public class GraphEdgeRunner {

	private static Logger log = LoggerFactory.getLogger(GraphEdgeRunner.class);

	private final FileGraphBuilder fileBuilder;
	private final RandomGraphBuilder randomBuilder;

	@Inject
	public GraphEdgeRunner(FileGraphBuilder fileBuilder, RandomGraphBuilder randomBuilder) {
		this.fileBuilder = fileBuilder;
		this.randomBuilder = randomBuilder;
	}

	public void run() {

		Graph graph = null;
		// try {
		// graph = builder.build("src/main/resources/graphs/simple2.g");
		// log.debug("Opening graph file.");
		// } catch (FileNotFoundException e) {
		// log.error(e.getMessage());
		// }

		graph = randomBuilder.build(10, 40);

		display(graph);
	}

	private void display(Graph graph) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("GraphEdge");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setPreferredSize(screenSize);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

		Container contentPane = frame.getContentPane();

		contentPane.setLayout(new BorderLayout());

		JGraphVisualizer visualizer = new JGraphVisualizer(graph, screenSize);
		JComponent jgraph = visualizer.createVisualization();
		contentPane.add(jgraph, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);

	}
}
