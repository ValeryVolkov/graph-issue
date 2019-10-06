package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Finder;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;

public class GraphImpl<T> implements Graph<T> {
	private final Finder<T> finder;

	public static <T> Graph<T> createDefaultGraph() {
		return new GraphImpl<>();
	}

	private GraphImpl() {
		this(new FinderImpl<>());
	}

	public GraphImpl(Finder<T> finder) {
		this.finder = Objects.requireNonNull(finder, "Должна присутствовать реализация поиска маршрута");
	}

	@Override
	public void addVertex(final Vertex<T> vertex) {
		finder.addVertex(Objects.requireNonNull(vertex, "Невозможно добавить в граф пустую вершину"));
	}

	@Override
	public void addEdge(final Edge<T> edge) {
		Objects.requireNonNull(edge, "Невозможно добавить в граф пустое ребро");
		final Vertex<T> first = Objects.requireNonNull(edge.getFirst(), "У ребра не указана первая вершина");
		final Vertex<T> second = Objects.requireNonNull(edge.getSecond(), "У ребра не указана вторая вершина");

		finder.addVertex(first);
		finder.addVertex(second);
	}

	@Override
	public Collection<Edge<T>> getPath(final Vertex<T> first, final Vertex<T> second) {
		return finder.getPath(
				Objects.requireNonNull(first, "Не указана первая вершина"),
				Objects.requireNonNull(second, "Не указана вторая вершина"));
	}

	@Override
	public void traverse(final Consumer<Vertex<T>> consumer) {
		finder.traverse(Objects.requireNonNull(consumer, "Невозможно выполнить операцию"));
	}

	@Override
	public Collection<Edge<T>> getAllEdges() {
		return Collections.unmodifiableCollection(finder.getAllEdges());
	}
}
