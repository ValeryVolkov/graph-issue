package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Vertex;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class VertexImpl<T, W> implements Vertex<T, W> {
	private final Set<Edge<T, W>> edges = new CopyOnWriteArraySet<>();

	private T value;

	public VertexImpl(T value) {
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public Collection<Edge<T, W>> getEdges() {
		return Collections.unmodifiableCollection(edges);
	}

	@Override
	public void addEdge(Edge<T, W> edge) {
		edges.add(edge);
	}

	@Override
	public void updateValue(T value) {
		this.value = value;
	}
}
