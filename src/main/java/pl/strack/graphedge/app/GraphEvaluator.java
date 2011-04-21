package pl.strack.graphedge.app;

import java.awt.Container;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.jgraph.JGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.GraphBuilder;
import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.ColoredGraphData;
import pl.strack.graphedge.painter.Painter;
import pl.strack.graphedge.painter.SimplePainter;
import pl.strack.graphedge.painter.TreePainter;
import pl.strack.graphedge.visualizer.JGraphVisualizer;

/**
 * A class for operations on graphs in SwingWorker thread.
 */
public class GraphEvaluator extends SwingWorker<ColoredGraphData, Object> {

	private static Logger log = LoggerFactory.getLogger(GraphEvaluator.class);

	private final GraphBuilder builder;
	private final Container container;

	public GraphEvaluator(GraphBuilder builder, Container container) {
		this.builder = builder;
		this.container = container;
	}

	@Override
	protected ColoredGraphData doInBackground() throws Exception {
		Graph graph = builder.build();

		GraphClassifier classifier = new GraphClassifier();

		long classifierTime = System.nanoTime();
		GraphType type = classifier.determineGraphType(graph);
		classifierTime = System.nanoTime() - classifierTime;

		Painter painter;

		switch (type) {
		case TREE:
			painter = new TreePainter();
			break;
		case BIPARTITE:
			painter = new SimplePainter();
			break;
		case SIMPLE:
		default:
			painter = new SimplePainter();
			break;
		}

		long painterTime = System.nanoTime();
		int colors = painter.paint(graph);
		painterTime = System.nanoTime() - painterTime;

		log.info("Creating visualization.");
		JGraphVisualizer visualizer = new JGraphVisualizer(graph, container.getSize());
		JGraph jgraph = (JGraph) visualizer.createVisualization();

		ColoredGraphData data = new ColoredGraphData(jgraph, colors, graph.vertexSet().size(),
				graph.edgeSet().size(), type, painterTime, classifierTime);
		return data;
	}

	@Override
	protected void done() {
		super.done();
		try {
			ColoredGraphData data = get();
			JGraph jgraph = data.getJgraph();
			jgraph.setPreferredSize(container.getSize());

			container.removeAll();
			container.add(jgraph);

			container.repaint();
			container.validate();

			final int factor = 1000000;

			JOptionPane.showMessageDialog(
					container,
					MessageFormat.format("Number of colors: {0}\nNumber of vertices: {1}"
							+ "\nNumber of edges: {2}" + "\nColoring time: {3} ms"
							+ "\nClassification time: {4} ms" + "\nGraph type: {5}",
							data.getNumberOfColors(), data.getNumberOfVertices(),
							data.getNumberOfEdges(), data.getColoringTime() / factor,
							data.getClassificationTime() / factor, data.getType()));
		} catch (InterruptedException e) {
			log.error("Graph evaluation interrupted.");
		} catch (ExecutionException e) {
			log.error("Error evaluating graph.");
			e.printStackTrace();
		}

	}

}
