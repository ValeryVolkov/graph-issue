package ru.valery.graphs.impl;

import org.junit.Before;
import org.junit.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;

import java.util.Collection;

public class RecursiveFinderTest extends CommonTest{
	@Before
	public void init() {
	}


	@Test
	public void test2(){
		Graph<String, Double> graph = createGraph("graph-with-two-vertices.csv");
		Collection<Edge<String, Double>> path = graph.getPath(new VertexImpl<>("start", "start"), new VertexImpl<>("finish", "finish"));
		System.out.println(path);
	}

}
