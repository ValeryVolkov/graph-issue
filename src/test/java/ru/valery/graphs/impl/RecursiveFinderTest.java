package ru.valery.graphs.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;

import java.util.Collection;

class RecursiveFinderTest extends CommonTest {


	@Test
	@DisplayName("Проверка нахождения маршрута через 2 ребра")
	void test2Vertices() {
		final Graph<String> graph = createGraph("graph-for-two-edges.csv");
		final Collection<Edge<String>> path = graph.getPath(START, FINISH);
		System.out.println(path);
		Assertions.assertFalse(path.isEmpty());
	}

	@Test
	@DisplayName("Проверка нахождения оптимального маршрута при наличие двух путей")
	void test2Routes() {
		final Graph<String> graph = createGraph("graph-with-two-route.csv");
		final Collection<Edge<String>> path = graph.getPath(START, FINISH);
		System.out.println(path);
		Assertions.assertFalse(path.isEmpty());
	}

}
