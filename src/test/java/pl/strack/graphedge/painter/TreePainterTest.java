package pl.strack.graphedge.painter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

public class TreePainterTest {

	@Before
	public void setup() {

	}

	@Test
	public void testGraphEdgePainting() throws FileNotFoundException,
			GraphColoringNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/tree3.g");
		Graph graph = builder.build();

		Painter painter = new TreePainter();
		assertEquals(3, painter.paint(graph));
		assertTrue(graph.checkEdgeColoring());
	}

}
