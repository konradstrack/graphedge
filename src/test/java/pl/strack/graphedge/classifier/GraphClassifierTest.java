package pl.strack.graphedge.classifier;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.core.Graph;

public class GraphClassifierTest {
	
	private GraphClassifier classifier;
	
	@Before
	public void setup() {
		classifier = new GraphClassifier();
	}
	
	@Test
	public void testSimple() throws FileNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/simple2.g");
		Graph graph = builder.build();
		
		assertEquals(GraphType.SIMPLE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testBipartite() throws FileNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/bipartite1.g");
		Graph graph = builder.build();
		
		assertEquals(GraphType.BIPARTITE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testTree1() throws FileNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/tree1.g");
		Graph graph = builder.build();
		
		assertEquals(GraphType.TREE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testTree2() throws FileNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/tree2.g");
		Graph graph = builder.build();
		
		assertEquals(GraphType.TREE, classifier.determineGraphType(graph));
	}
	
	@Test
	public void testDisconnectedTree() throws FileNotFoundException {
		FileGraphBuilder builder = new FileGraphBuilder("src/test/resources/graphs/disconnected_tree.g");
		Graph graph = builder.build();
		
		assertEquals(GraphType.BIPARTITE, classifier.determineGraphType(graph));
	}
	
}
