package pl.strack.graphedge.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EdgeTest {
	
	@Before
	public void setup() {
		
	}

	@Test
	public void testSetGetColor() {
		Edge edge = new Edge();
		
		final int color = 10;
		edge.setColor(color);
		assertEquals(color, edge.getColor());
	}
}
