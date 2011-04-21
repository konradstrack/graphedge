package pl.strack.graphedge.core;

/**
 * Custom edge implementation.
 */
public class Edge {

	private int color;

	/**
	 * @param color
	 *            edge color to be set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * @return edge's color
	 */
	public int getColor() {
		return color;
	}

}
