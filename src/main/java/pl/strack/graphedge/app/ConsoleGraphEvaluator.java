package pl.strack.graphedge.app;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.GraphBuilder;
import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.Painter;
import pl.strack.graphedge.painter.SimplePainter;
import pl.strack.graphedge.painter.TreePainter;

public class ConsoleGraphEvaluator {

	private static Logger log = LoggerFactory.getLogger(ConsoleGraphEvaluator.class);

	private final GraphBuilder builder;

	public ConsoleGraphEvaluator(GraphBuilder builder) {
		this.builder = builder;
	}

	public void execute() throws Exception {
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
		case SIMPLE:
		default:
			painter = new SimplePainter();
			break;
		}

		long painterTime = System.nanoTime();
		int colors = painter.paint(graph);
		painterTime = System.nanoTime() - painterTime;
		
		final int factor = 1000000;

		System.out.println(MessageFormat.format("Number of colors: {0}\nNumber of vertices: {1}"
				+ "\nNumber of edges: {2}" + "\nColoring time: {3} ms"
				+ "\nClassification time: {4} ms" + "\nGraph type: {5}", colors, graph.vertexSet()
				.size(), graph.edgeSet().size(), painterTime / factor, classifierTime / factor, type));
	}

}
