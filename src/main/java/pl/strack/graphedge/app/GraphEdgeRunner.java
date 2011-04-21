package pl.strack.graphedge.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.FileGraphBuilder;
import pl.strack.graphedge.builder.RandomGraphBuilder;

public class GraphEdgeRunner {

	private static Logger log = LoggerFactory.getLogger(GraphEdgeRunner.class);

	private JFrame frame;
	private JPanel graphPanel;

	public void run() {
		display();
	}

	/**
	 * Creates the Swing environment: frame, buttons, etc.
	 */
	private void display() {
		frame = new JFrame("GraphEdge");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		graphPanel = new JPanel();
		graphPanel.setBackground(Color.white);
		contentPane.add(graphPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

		JSpinner vertexSpinner = new JSpinner();
		buttonPanel.add(vertexSpinner);
		JSpinner edgeSpinner = new JSpinner();
		buttonPanel.add(edgeSpinner);

		JButton randomButton = new JButton("Random graph");
		randomButton.addActionListener(new RandomButtonListener(vertexSpinner, edgeSpinner));
		buttonPanel.add(randomButton);

		JButton openFileButton = new JButton("Open file");
		openFileButton.addActionListener(new OpenFileButtonListener());
		buttonPanel.add(openFileButton);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

	/**
	 * Inner listener class for the "Random graph" button.
	 */
	private class RandomButtonListener implements ActionListener {

		private final JSpinner vertexSpinner;
		private final JSpinner edgeSpinner;

		public RandomButtonListener(JSpinner vertexSpinner, JSpinner edgeSpinner) {
			this.vertexSpinner = vertexSpinner;
			this.edgeSpinner = edgeSpinner;
		}

		public void actionPerformed(ActionEvent e) {
			log.info("Selected random graph with {} vertices and {} edges.",
					vertexSpinner.getValue(), edgeSpinner.getValue());
			GraphEvaluator evaluator = new GraphEvaluator(new RandomGraphBuilder(
					(Integer) vertexSpinner.getValue(), (Integer) edgeSpinner.getValue()),
					graphPanel);
			evaluator.execute();
		}
	}

	/**
	 * Inner listener class for the "Open file" button.
	 */
	private class OpenFileButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				log.debug("Selected file: {}", fileChooser.getSelectedFile().getPath());
				GraphEvaluator evaluator = new GraphEvaluator(new FileGraphBuilder(fileChooser
						.getSelectedFile().getPath()), graphPanel);
				evaluator.execute();
			}
		}
	}

}
