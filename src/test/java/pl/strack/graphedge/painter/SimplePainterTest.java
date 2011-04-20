package pl.strack.graphedge.painter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

public class SimplePainterTest {

	@Before
	public void setup() {
	}

	@Test
	public void testGraphEdgePainting1() throws FileNotFoundException,
			GraphColoringNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/simple3.g");
		Graph graph = builder.build();

		Painter painter = new SimplePainter();
		assertEquals(3, painter.paint(graph));
		assertTrue(graph.checkEdgeColoring());
	}

	@Test
	public void testGraphEdgePainting2() throws FileNotFoundException,
			GraphColoringNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/simple4.g");
		Graph graph = builder.build();

		Painter painter = new SimplePainter();
		assertEquals(5, painter.paint(graph));
		assertTrue(graph.checkEdgeColoring());
	}

}
