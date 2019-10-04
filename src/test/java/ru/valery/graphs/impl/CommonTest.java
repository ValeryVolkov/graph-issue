package ru.valery.graphs.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommonTest {
	<T, W> Graph<T, W> createGraph(String filePath) {
		try {
			final Collection<Vertex<T, W>> vertices = this.loadVertices("graph-with-two-vertices.csv");
			Graph<T, W> graph = new GraphImpl<>();
			for (Vertex<T, W> vertex : vertices) {
				graph.addVertex(vertex);
			}
			return graph;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	<T, W> Collection<Vertex<T, W>> loadVertices(final String filePath) throws IOException {
		try (final CSVParser parser =
				     new CSVParser(new FileReader(
						     CommonTest.class.getClassLoader().getResource(filePath).getFile()),
						     CSVFormat.DEFAULT
								     .withHeader())) {
			final Iterator<CSVRecord> iterator = parser.iterator();

			if (iterator.hasNext()) {
				final Map<String, Vertex<T, W>> ret = new HashMap<>();
				while (iterator.hasNext()) {
					final CSVRecord record = iterator.next();
					String idFirst = record.get("ID_FIRST");
					String valueFirst = record.get("VALUE_FIRST");

					String idSecond = record.get("ID_SECOND");
					String valueSecond = record.get("VALUE_SECOND");

					String directional = record.get("DIRECTIONAL");
					String weight = record.get("WEIGHT");

					Vertex<T, W> vf = new VertexImpl<>(idFirst, (T) valueFirst);
					Vertex<T, W> vs = new VertexImpl<>(idSecond, (T) valueSecond);

					ret.putIfAbsent(vf.getId(), vf);
					ret.putIfAbsent(vs.getId(), vs);

					vf = ret.get(vf.getId());
					vs = ret.get(vs.getId());

					EdgeDirectional ed = EdgeDirectional.valueOf(directional);
					new EdgeImpl<>(vf, vs, (W) weight, ed);
				}
				return ret.values();
			}
		}
		return Collections.emptySet();
	}
}
