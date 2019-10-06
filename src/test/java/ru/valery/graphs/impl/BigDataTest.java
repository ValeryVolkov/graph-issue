package ru.valery.graphs.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigDataTest {

	@Test
	@DisplayName("Проверяем, что из всего набора рёбер(полный перебор) - оптимальный маршрут состоит из трёх")
	void test1000K() {
		Map<Vertex<String>,Vertex<String>> vertexMap = new HashMap<>();

		List<Edge<String>> edgeList = new ArrayList<>();

		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= 1000; j++) {
				if (i < j) {//Исключаем явную петлю
					Vertex<String> vertex1 = new VertexImpl<>("" + i, "value=" + i);
					Vertex<String> vertex2 = new VertexImpl<>("" + j, "value=" + j);
					vertexMap.putIfAbsent(vertex1, vertex1);
					vertexMap.putIfAbsent(vertex2, vertex2);
					edgeList.add(new EdgeImpl<>(vertexMap.get(vertex1), vertexMap.get(vertex2), 5.0));
				}
			}
		}

		Vertex<String> vertex0 = new VertexImpl<>("0", "start");
		Vertex<String> vertex1 = vertexMap.get(new VertexImpl<>("1", "value=1"));

		edgeList.add(new EdgeImpl<>(vertex0, vertex1, 5.0));

		Vertex<String> vertex101 = new VertexImpl<>("1001", "finish");
		Vertex<String> vertex100 = vertexMap.get(new VertexImpl<>("1000", "value=1000"));

		edgeList.add(new EdgeImpl<>(vertex100, vertex101, 5.0));

		Graph<String> graph = GraphImpl.createDefaultGraph();
		edgeList.forEach(graph::addEdge);

		Collection<Edge<String>> path = graph.getPath(vertex0, vertex101);

		Assertions.assertEquals(path.size(), 3);
	}
}
