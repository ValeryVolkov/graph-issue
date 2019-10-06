package ru.valery.graphs.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;

import java.util.Collection;


class GraphWithoutRouteTest extends CommonTest {
	static Graph<String> graph;
	@BeforeAll
	static void init(){
		graph = createGraph("graph-with-direct-without-route.csv");
	}

	@Test
	@DisplayName("Проверка отсутствия маршрута к финальной вершине")
	void testGraphWithDirectWithoutRoute() {
		final Collection<Edge<String>> path = graph.getPath(START, FINISH);
		System.out.println(path);
		Assertions.assertTrue(path.isEmpty());
	}
}
