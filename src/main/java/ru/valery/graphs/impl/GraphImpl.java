package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Finder;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;

public class GraphImpl<T, W> implements Graph<T, W> {
	private final Finder<T, W> finder;

	public static <T, W> Graph<T, W> createDefaultGraph() {
		return new GraphImpl<>();
	}

	 GraphImpl() {
		this(new RecursiveFinderImpl<>());
	}

	public GraphImpl(Finder<T, W> finder) {
		this.finder = Objects.requireNonNull(finder, "Должна присутствовать реализация поиска маршрута");
	}

	@Override
	public void addVertex(final Vertex<T, W> vertex) {
		finder.addVertex(Objects.requireNonNull(vertex, "Невозможно добавить в граф пустую вершину"));
	}

	@Override
	public void addEdge(final Edge<T, W> edge) {
		Objects.requireNonNull(edge, "Невозможно добавить в граф пустое ребро");
		final Vertex<T, W> first = Objects.requireNonNull(edge.getFirst(), "У ребра не указана первая вершина");
		final Vertex<T, W> second = Objects.requireNonNull(edge.getSecond(), "У ребра не указана вторая вершина");

		finder.addVertex(first);
		finder.addVertex(second);
	}

	@Override
	public Collection<Edge<T, W>> getPath(final Vertex<T, W> first, final Vertex<T, W> second) {
		return finder.getPath(
				Objects.requireNonNull(first, "Не указана первая вершина"),
				Objects.requireNonNull(second, "Не указана вторая вершина"));
	}

	@Override
	public void traverse(final Consumer<Vertex<T, W>> consumer) {
		finder.traverse(Objects.requireNonNull(consumer, "Невозможно выполнить операцию"));
	}

	@Override
	public Collection<Edge<T, W>> getAllEdges() {
		return Collections.unmodifiableCollection(finder.getAllEdges());
	}
}
