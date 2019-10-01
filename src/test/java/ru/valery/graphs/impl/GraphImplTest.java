package ru.valery.graphs.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.misc.EdgeDirectional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Simple Graph lib:
 *
 * Should support 2 types of graphs - directed and undirected with 3 operations:
 *  addVertex - adds vertex to the graph
 *  addEdge - adds edge to the graph
 *  getPath - returns a list of edges between 2 vertices (path doesn’t have to be optimal)
 *  Vertices should be of a user defined type.
 *
 * Questions to be ready to answer (don’t have to implement):
 * Add weighted edges support in your lib.
 * Add traverse function that will take a user defined function and apply it on every vertex of the graph.
 * Make you graphs thread safe.
 */
public class GraphImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void addVertex() {
		GraphImpl<String, Double> gi = new GraphImpl<>();

		VertexImpl<String, Double> viFirst = new VertexImpl<>("Первая вершина");
		VertexImpl<String, Double> viSecond = new VertexImpl<>("Вторая вершина");

		EdgeImpl<String, Double> edge = new EdgeImpl<>(viFirst, viSecond, 1.0, EdgeDirectional.NON_DIRECTIONAL);

		gi.addEdge(edge);

		Collection<Edge<String, Double>> path = gi.getPath(viFirst, viSecond);

		Assert.assertEquals(Collections.singletonList(edge), path);
	}

	@Test
	public void addEdge() {

	}

	@Test
	public void getPath() {
		GraphImpl<String, Double> gi = new GraphImpl<>();

		VertexImpl<String, Double> vi1 = new VertexImpl<>("Первая вершина");
		VertexImpl<String, Double> vi2 = new VertexImpl<>("Вторая вершина");
		VertexImpl<String, Double> vi3 = new VertexImpl<>("Третья вершина");

		EdgeImpl<String, Double> edge1 = new EdgeImpl<>(vi1, vi2, 1.0, EdgeDirectional.NON_DIRECTIONAL);
		EdgeImpl<String, Double> edge2 = new EdgeImpl<>(vi2, vi3, 1.0, EdgeDirectional.NON_DIRECTIONAL);

		gi.addEdge(edge1);
		gi.addEdge(edge2);

		Collection<Edge<String, Double>> path = gi.getPath(vi1, vi3);

		Assert.assertEquals(Arrays.asList(edge1, edge2), path);
	}

	@Test
	public void traverse() {
		GraphImpl<String, Double> gi = new GraphImpl<>();

		VertexImpl<String, Double> vi1 = new VertexImpl<>("Первая вершина");
		VertexImpl<String, Double> vi2 = new VertexImpl<>("Вторая вершина");
		VertexImpl<String, Double> vi3 = new VertexImpl<>("Третья вершина");

		EdgeImpl<String, Double> edge1 = new EdgeImpl<>(vi1, vi2, 1.0, EdgeDirectional.NON_DIRECTIONAL);
		EdgeImpl<String, Double> edge2 = new EdgeImpl<>(vi2, vi3, 1.0, EdgeDirectional.NON_DIRECTIONAL);

		gi.addEdge(edge1);
		gi.addEdge(edge2);

		gi.traverse(v -> v.updateValue(v.getValue() + 1));
		gi.traverse(v -> System.out.println(v.getValue()));
	}

	//TODO:
}