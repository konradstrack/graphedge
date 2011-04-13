package pl.strack.graphedge.app;

import java.awt.Container;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jgraph.JGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.GraphBuilder;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.visualizer.JGraphVisualizer;

public class GraphEvaluator extends SwingWorker<JGraph, Object> {

	private static Logger log = LoggerFactory.getLogger(GraphEvaluator.class);

	private final GraphBuilder builder;
	private final Container container;

	public GraphEvaluator(GraphBuilder builder, Container container) {
		this.builder = builder;
		this.container = container;
	}

	@Override
	protected JGraph doInBackground() throws Exception {
		Graph graph = builder.build();

		JGraphVisualizer visualizer = new JGraphVisualizer(graph, container.getSize());
		log.debug("Container size: {}, {}", container.getSize().height, container.getSize().width);

		JGraph jgraph = (JGraph) visualizer.createVisualization();
		return jgraph;
	}

	@Override
	protected void done() {
		super.done();
		try {
			JGraph jgraph = get();
			jgraph.setPreferredSize(container.getSize());
			container.removeAll();
			container.add(jgraph);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		container.repaint();
		container.validate();
	}

}
