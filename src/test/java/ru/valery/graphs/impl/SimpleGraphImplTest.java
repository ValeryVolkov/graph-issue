package ru.valery.graphs.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;

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

//TODO: Добавить работу с весами (оптимальные маршруты)
//TODO: Проверить работу с направлениями
//TODO: Проверить циклы
//TODO: Проверить отсутствие проблем в многопоточном режиме
//TODO: Проверить работу на различных объемах данных 10К х 10К
//TODO: Передача для вершин equals & hashcode - для возможности работы с дорогами
//TODO: Добавить custom правило обхода графа для поиска

public class SimpleGraphImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1EdgePath() {
		Graph<String, Double> gi = GraphImpl.createDefaultGraph();

		Vertex<String, Double> viFirst = new VertexImpl<>("1","Первая вершина");
		Vertex<String, Double> viSecond = new VertexImpl<>("2","Вторая вершина");

		Edge<String, Double> edge = new EdgeImpl<>(viFirst, viSecond, 1.0);

		gi.addEdge(edge);

		Collection<Edge<String, Double>> path = gi.getPath(viFirst, viSecond);

		Assert.assertEquals(Collections.singletonList(edge), path);
	}



	@Test
	public void test2EdgesPath() {
		Graph<String, Double> gi = GraphImpl.createDefaultGraph();

		Vertex<String, Double> vi1 = new VertexImpl<>("1","Первая вершина");
		Vertex<String, Double> vi2 = new VertexImpl<>("2","Вторая вершина");
		Vertex<String, Double> vi3 = new VertexImpl<>("3","Третья вершина");

		Edge<String, Double> edge1 = new EdgeImpl<>(vi1, vi2, 1.0);
		Edge<String, Double> edge2 = new EdgeImpl<>(vi2, vi3, 1.0);

		gi.addEdge(edge1);
		gi.addEdge(edge2);

		Collection<Edge<String, Double>> path = gi.getPath(vi1, vi3);

		Assert.assertEquals(Arrays.asList(edge1, edge2), path);
	}

	@Test
	public void traverse() {
		Graph<String, Double> gi = GraphImpl.createDefaultGraph();

		Vertex<String, Double> vi1 = new VertexImpl<>("1","Первая вершина");
		Vertex<String, Double> vi2 = new VertexImpl<>("2","Вторая вершина");
		Vertex<String, Double> vi3 = new VertexImpl<>("3","Третья вершина");

		Edge<String, Double> edge1 = new EdgeImpl<>(vi1, vi2, 1.0);
		Edge<String, Double> edge2 = new EdgeImpl<>(vi2, vi3, 1.0);

		gi.addEdge(edge1);
		gi.addEdge(edge2);

		gi.traverse(v -> v.updateValue(v.getValue() + 1));
		gi.traverse(v -> System.out.println(v.getValue()));
	}

	//TODO:
}