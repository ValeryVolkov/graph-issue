package ru.valery.graphs.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;

import java.util.Collection;

class RingTest extends CommonTest {
	static Graph<String> graph;

	@BeforeAll
	static void init() {
		graph = createGraph("graph-with-ring.csv");
	}

	@Test
	@DisplayName("Поиск маршрута при наличие кольца")
	void testRing() {
		final Collection<Edge<String>> path = graph.getPath(START, FINISH);
		System.out.println(path);
		Assertions.assertFalse(path.isEmpty());
	}

}
