package pl.strack.graphedge.painter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.classifier.GraphClassifier;
import pl.strack.graphedge.classifier.GraphType;
import pl.strack.graphedge.core.Graph;

public class GraphEdgePainterTest {

	private FileGraphBuilder builder;
	private GraphClassifier classifier;
	private GraphEdgePainter painter;

	@Before
	public void setup() {
		builder = new FileGraphBuilder();
		classifier = new GraphClassifier();
		painter = new GraphEdgePainter(classifier);
	}

	@Test
	public void paintTreeTest() throws FileNotFoundException {
		Graph graph = builder.build("src/test/resources/graphs/tree3.g");

		assertEquals(GraphType.TREE, classifier.determineGraphType(graph));
		assertEquals(3, painter.paintGraph(graph));
		assertTrue(painter.checkColoring(graph));
		
	}
}
