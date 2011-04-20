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
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

public class GraphEdgePainterTest {

	private GraphClassifier classifier;
	private GraphEdgePainter painter;

	@Before
	public void setup() {
		classifier = new GraphClassifier();
		painter = new GraphEdgePainter(classifier);
	}

	@Test
	public void paintTreeTest() throws FileNotFoundException,
			GraphColoringNotFoundException {

		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/tree3.g");
		Graph graph = builder.build();
		
		assertEquals(GraphType.TREE, classifier.determineGraphType(graph));
		assertEquals(3, painter.paintGraph(graph));
		assertTrue(graph.checkEdgeColoring());

	}

	@Test
	public void paintSimpleTest1() throws FileNotFoundException,
			GraphColoringNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/simple3.g");
		Graph graph = builder.build();

		assertEquals(GraphType.SIMPLE, classifier.determineGraphType(graph));
		assertEquals(3, painter.paintGraph(graph));
		assertTrue(graph.checkEdgeColoring());
	}

	@Test
	public void paintSimpleTest2() throws FileNotFoundException, GraphColoringNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/simple4.g");
		Graph graph = builder.build();

		assertEquals(GraphType.SIMPLE, classifier.determineGraphType(graph));
		assertEquals(5, painter.paintGraph(graph));
		assertTrue(graph.checkEdgeColoring());
	}
}
