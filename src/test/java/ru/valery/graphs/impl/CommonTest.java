package ru.valery.graphs.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.excs.GraphLoadException;
import ru.valery.graphs.misc.EdgeDirectional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

abstract class CommonTest {
	static final Vertex<String> START = new VertexImpl<>("start", "start", 0);
	static final Vertex<String> FINISH = new VertexImpl<>("finish", "finish");

	static <T> Graph<T> createGraph(String filePath) {
		try {
			final Collection<Vertex<T>> vertices = loadVertices(filePath);
			final Graph<T> graph = GraphImpl.createDefaultGraph();
			for (final Vertex<T> vertex : vertices) {
				graph.addVertex(vertex);
			}
			return graph;
		} catch (Exception e) {
			throw new GraphLoadException(e);
		}
	}

	private static <T, W> Collection<Vertex<T>> loadVertices(final String filePath) throws IOException {
		URL url = CommonTest.class.getClassLoader().getResource(filePath);
		if (url == null) {
			throw new FileNotFoundException(filePath);
		}

		try (final CSVParser parser =
				     new CSVParser(new FileReader(
						     url.getFile()),
						     CSVFormat.DEFAULT
								     .withHeader())) {
			final Iterator<CSVRecord> iterator = parser.iterator();

			if (iterator.hasNext()) {
				final Map<String, Vertex<T>> ret = new HashMap<>();
				while (iterator.hasNext()) {
					final CSVRecord record = iterator.next();
					final String idFirst = record.get("ID_FIRST");
					final String valueFirst = record.get("VALUE_FIRST");

					final String idSecond = record.get("ID_SECOND");
					final String valueSecond = record.get("VALUE_SECOND");

					final String directional = record.get("DIRECTIONAL");
					final double weight = Double.parseDouble(record.get("WEIGHT"));

					Vertex<T> vf = new VertexImpl<>(idFirst, (T) valueFirst);
					Vertex<T> vs = new VertexImpl<>(idSecond, (T) valueSecond);


					ret.putIfAbsent(vf.getId(), vf);
					ret.putIfAbsent(vs.getId(), vs);

					vf = ret.get(vf.getId());
					vs = ret.get(vs.getId());

					final EdgeDirectional ed = EdgeDirectional.valueOf(directional);
					if (ed == EdgeDirectional.NON_DIRECTIONAL) {
						new EdgeImpl<>(vf, vs, weight, EdgeDirectional.FORWARD);
						new EdgeImpl<>(vs, vf, weight, EdgeDirectional.FORWARD);
					} else if (ed == EdgeDirectional.FORWARD) {
						new EdgeImpl<>(vf, vs, weight, EdgeDirectional.FORWARD);
					} else if (ed == EdgeDirectional.REVERSE) {
						new EdgeImpl<>(vs, vf, weight, EdgeDirectional.FORWARD);
					}
				}
				return ret.values();
			}
		}
		return Collections.emptySet();
	}
}
