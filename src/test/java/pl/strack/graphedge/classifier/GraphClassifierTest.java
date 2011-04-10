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
	
	@Test
	public void testBipartite() throws FileNotFoundException {
		Graph graph = builder.build("src/test/resources/graphs/bipartite1.g");
		
		assertEquals(GraphType.BIPARTITE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testTree1() throws FileNotFoundException {
		Graph graph = builder.build("src/test/resources/graphs/tree1.g");
		
		assertEquals(GraphType.TREE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testTree2() throws FileNotFoundException {
		Graph graph = builder.build("src/test/resources/graphs/tree2.g");
		
		assertEquals(GraphType.TREE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testDisconnectedTree() throws FileNotFoundException {
		Graph graph = builder.build("src/test/resources/graphs/disconnected_tree.g");
		
		assertEquals(GraphType.BIPARTITE, classifier.determineGraphType(graph));
	}
	
}
