package ru.valery.graphs.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;

import java.util.Collection;

class DirectGraphTest extends CommonTest {
	static Graph<String> GRAPH;

	@BeforeAll
	static void init(){
		GRAPH = createGraph("graph-with-direct.csv");
	}

	@Test
	void testGraphWithDirect() {
		final Collection<Edge<String>> path = GRAPH.getPath(START, FINISH);
		System.out.println(path);
		Assertions.assertFalse(path.isEmpty());
	}
}
