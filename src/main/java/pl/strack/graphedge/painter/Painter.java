package pl.strack.graphedge.painter;

import pl.strack.graphedge.core.Graph;
import pl.strack.graphedge.painter.exceptions.GraphColoringNotFoundException;

public interface Painter {

	int paint(Graph graph) throws GraphColoringNotFoundException;

}
