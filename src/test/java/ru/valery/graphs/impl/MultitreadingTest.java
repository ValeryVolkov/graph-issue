package ru.valery.graphs.impl;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.valery.graphs.Graph;

@DisplayName("A special test case")
class MultitreadingTest extends CommonTest {
	static Graph<String> graph;

	@BeforeAll
	static void beforeMethod() {
		graph = createGraph("graph-with-direct.csv");
	}

	@Test
	@DisplayName("Custom test name containing testMethods")
	void testMethodsOne() throws InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("Simple test-method One. Thread id is: " + id);

		graph.getPath(START, FINISH);
		graph.addVertex(new VertexImpl<>("11", "444.0"));
	}

	@Test
	@DisplayName("Custom test name containing testMethodsTwo")
	void testMethodsTwo() throws InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("Simple test-method One. Thread id is: " + id);

		graph.getPath(START, FINISH);
	}

	@AfterAll
	static void afterMethod() {
		graph = null;
	}
}
