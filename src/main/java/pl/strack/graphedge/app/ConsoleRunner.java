package pl.strack.graphedge.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.strack.graphedge.builder.RandomGraphBuilder;

public class ConsoleRunner implements GraphEdgeRunner {

	private static Logger log = LoggerFactory.getLogger(ConsoleRunner.class);

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Number of vertices: ");
			int vertices = Integer.valueOf(reader.readLine());
			System.out.println("Number of edges: ");
			int edges = Integer.valueOf(reader.readLine());

			ConsoleGraphEvaluator evaluator = new ConsoleGraphEvaluator(new RandomGraphBuilder(
					vertices, edges));
			evaluator.execute();
		} catch (IOException e) {
			System.err.println("Error reading input. Will now exit.");
			System.exit(1);
		} catch (Exception e) {
			log.error("Error evaluating graph.");
		}
	}
}
