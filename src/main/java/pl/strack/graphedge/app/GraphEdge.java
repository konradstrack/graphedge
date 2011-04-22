package pl.strack.graphedge.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Main class. Starts the application.
 */
public final class GraphEdge {

	private static Logger log = LoggerFactory.getLogger(GraphEdge.class);

	private GraphEdge() {
	}

	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("c", "console", false, "run in console mode");

		CommandLineParser parser = new PosixParser();

		GraphEdgeModule module = null;
		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("c")) {
				module = new ConsoleGraphEdgeModule();
			} else {
				module = new SwingGraphEdgeModule();
			}
		} catch (ParseException e) {
			log.error("Error parsing command line.");
		}

		Injector injector = Guice.createInjector(module);
		GraphEdgeRunner runner = injector.getInstance(GraphEdgeRunner.class);

		runner.run();
	}

}
