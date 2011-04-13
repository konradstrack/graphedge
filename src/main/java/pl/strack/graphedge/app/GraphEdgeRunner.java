package pl.strack.graphedge.app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.RandomGraphBuilder;
import pl.strack.graphedge.core.Graph;

public class GraphEdgeRunner {

	private static Logger log = LoggerFactory.getLogger(GraphEdgeRunner.class);

	private JComponent jgraph;
	private Dimension screenSize;
	private JFrame frame;
	private JPanel graphPanel;

	public void run() {

		Graph graph = null;
		// try {
		// graph = builder.build("src/main/resources/graphs/simple2.g");
		// log.debug("Opening graph file.");
		// } catch (FileNotFoundException e) {
		// log.error(e.getMessage());
		// }

		display();
	}

	private void display() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("GraphEdge");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setPreferredSize(screenSize);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

		Container contentPane = frame.getContentPane();

		contentPane.setLayout(new BorderLayout());

		JButton randomButton = new JButton("Random graph");
		randomButton.addActionListener(new RandomButtonListener());

		graphPanel = new JPanel();
		contentPane.add(graphPanel, BorderLayout.CENTER);
		contentPane.add(randomButton, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);

	}

	private class RandomButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			GraphEvaluator evaluator = new GraphEvaluator(new RandomGraphBuilder(20, 80), screenSize, graphPanel);
			evaluator.execute();
		}

	}

}
