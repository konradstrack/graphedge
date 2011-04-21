package pl.strack.graphedge.visualizer;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.VertexView;
import org.jgrapht.ext.JGraphModelAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.core.Edge;
import pl.strack.graphedge.core.Graph;

/**
 * Class responsible for visualization of the graph.
 */
public class JGraphVisualizer {

	private static Logger log = LoggerFactory.getLogger(JGraphVisualizer.class);

	private JGraphModelAdapter<Integer, Edge> modelAdapter;

	private final Dimension size;
	private final Graph graph;

	public JGraphVisualizer(Graph graph, Dimension size) {
		this.graph = graph;
		this.size = size;
	}

	/**
	 * Creates a visualization.
	 * 
	 * @return component containing visualization that should be displayed
	 */
	public JComponent createVisualization() {

		log.debug("Creating JGraph visualization");

		modelAdapter = new JGraphModelAdapter<Integer, Edge>(graph);
		JGraph jgraph = new JGraph(modelAdapter);

		setEdgeLabels(jgraph);
		setVertexPositions(jgraph);

		return jgraph;
	}

	/**
	 * Sets the coordinates of vertices so that they lay on a circle.
	 * 
	 * @param jgraph
	 *            a graph to be displayed
	 */
	private void setVertexPositions(JGraph jgraph) {

		GraphLayoutCache cache = jgraph.getGraphLayoutCache();
		CellView[] cells = cache.getAllViews();

		final int square = 20;
		final int x = size.width / 2;
		final int y = (int) (0.04 * size.height);
		final int r = (size.height - 2 * y - square) / 2;
		int i = 0;

		int vertices = graph.vertexSet().size();

		for (CellView cell : cells) {
			if (cell instanceof VertexView) {
				Rectangle2D rectangle = cell.getBounds();
				double angle = 2 * i * Math.PI / vertices;
				int xi = (int) (x + r * Math.sin(angle));
				int yi = (int) (y + r * (1 - Math.cos(angle)));

				rectangle.setRect(xi, yi, square, square);

				++i;
			}
		}
	}

	/**
	 * Sets color numbers as edge labels.
	 * 
	 * @param jgraph
	 *            a graph to be displayed
	 */
	private void setEdgeLabels(JGraph jgraph) {
		log.debug("Setting edge labels");

		GraphLayoutCache cache = jgraph.getGraphLayoutCache();
		CellView[] cells = cache.getAllViews();

		for (CellView cell : cells) {
			if (cell instanceof EdgeView) {
				EdgeView edgeView = (EdgeView) cell;
				DefaultEdge defaultEdge = (DefaultEdge) edgeView.getCell();

				defaultEdge.setUserObject("");

				for (Edge edge : graph.edgeSet()) {
					if (defaultEdge == modelAdapter.getEdgeCell(edge)) {
						defaultEdge.setUserObject(edge.getColor());
						break;
					}
				}
			}
		}
	}
}
