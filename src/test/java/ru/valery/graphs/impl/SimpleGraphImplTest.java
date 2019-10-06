package ru.valery.graphs.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Simple Graph lib:
 * <p>
 * Should support 2 types of graphs - directed and undirected with 3 operations:
 * addVertex - adds vertex to the graph
 * addEdge - adds edge to the graph
 * getPath - returns a list of edges between 2 vertices (path doesn’t have to be optimal)
 * Vertices should be of a user defined type.
 * <p>
 * Questions to be ready to answer (don’t have to implement):
 * Add weighted edges support in your lib.
 * Add traverse function that will take a user defined function and apply it on every vertex of the graph.
 * Make you graphs thread safe.
 */

//+ Добавить работу с весами (оптимальные маршруты)
//+ Проверить работу с направлениями
//+ Проверить циклы
//TODO: Проверить отсутствие проблем в многопоточном режиме
//+ Проверить работу на различных объемах данных 1К х 1К
//+ для вершин equals & hashcode - для возможности работы с дорогами
//+ custom правило обхода графа для поиска

class SimpleGraphImplTest {


	@Test
	@DisplayName("Поиск маршрута при наличие всего двух вершин и направленного ребра")
	void testForwardEdgePath() {
		Graph<String> gi = GraphImpl.createDefaultGraph();

		Vertex<String> viFirst = new VertexImpl<>("1", "Первая вершина");
		Vertex<String> viSecond = new VertexImpl<>("2", "Вторая вершина");

		Edge<String> edge = new EdgeImpl<>(viFirst, viSecond, 1.0, EdgeDirectional.FORWARD);

		gi.addEdge(edge);

		Collection<Edge<String>> path = gi.getPath(viFirst, viSecond);

		Assertions.assertEquals(Collections.singletonList(edge), path);
	}

	@Test
	@DisplayName("Отсутствие маршрута при наличие всего двух вершин и направленного ребра")
	void testReverseEdgePath() {
		Graph<String> gi = GraphImpl.createDefaultGraph();

		Vertex<String> viFirst = new VertexImpl<>("1", "Первая вершина");
		Vertex<String> viSecond = new VertexImpl<>("2", "Вторая вершина");

		Edge<String> edge = new EdgeImpl<>(viFirst, viSecond, 1.0, EdgeDirectional.REVERSE);

		gi.addEdge(edge);

		Collection<Edge<String>> path = gi.getPath(viFirst, viSecond);

		Assertions.assertTrue(path.isEmpty());
	}


	@Test
	void testNonDirectional2EdgesPath() {
		Graph<String> gi = GraphImpl.createDefaultGraph();

		Vertex<String> vi1 = new VertexImpl<>("1", "Первая вершина");
		Vertex<String> vi2 = new VertexImpl<>("2", "Вторая вершина");
		Vertex<String> vi3 = new VertexImpl<>("3", "Третья вершина");

		Edge<String> edge1 = new EdgeImpl<>(vi1, vi2, 1.0);
		Edge<String> edge2 = new EdgeImpl<>(vi2, vi3, 1.0);

		gi.addEdge(edge1);
		gi.addEdge(edge2);

		Collection<Edge<String>> path = gi.getPath(vi1, vi3);

		Assertions.assertEquals(Arrays.asList(edge1, edge2), path);
	}

	@Test
	@DisplayName("Проверка проведения массовых операций над значениями хранящимися в вершинах")
	void traverse() {
		Graph<String> gi = GraphImpl.createDefaultGraph();

		Vertex<String> vi1 = new VertexImpl<>("1", "Первая вершина");
		Vertex<String> vi2 = new VertexImpl<>("2", "Вторая вершина");
		Vertex<String> vi3 = new VertexImpl<>("3", "Третья вершина");

		Edge<String> edge1 = new EdgeImpl<>(vi1, vi2, 1.0);
		Edge<String> edge2 = new EdgeImpl<>(vi2, vi3, 1.0);

		gi.addEdge(edge1);
		gi.addEdge(edge2);

		gi.traverse(v -> v.updateValue(v.getValue() + 1));
		gi.traverse(v -> System.out.println(v.getValue()));
		gi.getAllEdges().forEach(e -> {
			Assertions.assertTrue(e.getFirst().getValue().endsWith("1"));
			Assertions.assertTrue(e.getSecond().getValue().endsWith("1"));
		});

	}

	//TODO:
}