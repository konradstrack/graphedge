package pl.strack.graphedge.classifier;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;

public class GraphClassifierTest {
	
	private FileGraphBuilder builder;
	private GraphClassifier classifier;
	
	@Before
	public void setup() {
		builder = new FileGraphBuilder();
		classifier = new GraphClassifier();
	}
	
	@Test
	public void testSimple() throws FileNotFoundException {
		Graph graph = builder.build("src/test/resources/graphs/simple2.g");
		
		assertEquals(GraphType.SIMPLE, classifier.determineGraphType(graph));
	}
	
}
