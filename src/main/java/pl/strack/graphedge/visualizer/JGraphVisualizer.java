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

public class JGraphVisualizer {

	private static Logger log = LoggerFactory.getLogger(JGraphVisualizer.class);

	private JGraphModelAdapter<Integer, Edge> modelAdapter;

	private Dimension size;
	private Graph graph;

	public JGraphVisualizer(Graph graph, Dimension size) {
		this.graph = graph;
		this.size = size;
	}

	public JComponent createVisualization() {

		log.debug("Creating JGraph visualization");

		modelAdapter = new JGraphModelAdapter<Integer, Edge>(graph);
		JGraph jgraph = new JGraph(modelAdapter);

		setEdgeLabels(jgraph);
		setVertexPositions(jgraph);

		return jgraph;
	}

	private void setVertexPositions(JGraph jgraph) {

		GraphLayoutCache cache = jgraph.getGraphLayoutCache();
		CellView[] cells = cache.getAllViews();

		final int x = size.width / 2;
		final int y = 80;
		final int r = (size.height - 200) / 2;
		int i = 0;
		
		int vertices = graph.vertexSet().size();

		for (CellView cell : cells) {
			if (cell instanceof VertexView) {
				Rectangle2D rectangle = cell.getBounds();
				double angle = 2 * i * Math.PI / vertices; 
				int xi = (int) (x + r * Math.sin(angle));
				int yi = (int) (y + r * (1 - Math.cos(angle)));

				log.debug("Vertex position: {}, {}", xi, yi);
				rectangle.setRect(xi, yi, 20, 20);

				++i;
			}
		}
	}

	private void setEdgeLabels(JGraph jgraph) {
		log.debug("Setting edge labels");

		GraphLayoutCache cache = jgraph.getGraphLayoutCache();
		CellView[] cells = cache.getAllViews();

		for (CellView cell : cells) {
			if (cell instanceof EdgeView) {
				EdgeView edgeView = (EdgeView) cell;
				DefaultEdge defaultEdge = (DefaultEdge) edgeView.getCell();
				defaultEdge.setUserObject("");
			}
		}
	}
}
