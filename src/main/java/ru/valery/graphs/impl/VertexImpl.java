package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.excs.LoopException;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class VertexImpl<T, W> implements Vertex<T, W> {
	private final Set<Edge<T, W>> edges = new CopyOnWriteArraySet<>();

	private final String id;

	private T value;

	public VertexImpl(final String id, final T value) {
		this.id = Objects.requireNonNull(id,"Идентификатор не может быть пустым");
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
	public void addEdge(final Edge<T, W> edge) {
		Objects.requireNonNull(edge, "Невозможно прикрепить пустое ребро");
		Objects.requireNonNull(edge.getFirst(), "Невозможно прикрепить ребро c незаполненной первой вершиной");
		Objects.requireNonNull(edge.getSecond(), "Невозможно прикрепить ребро с незаполненной второй вершиной");

		if (this.equals(edge.getFirst()) && this.equals(edge.getSecond())) {
			throw new LoopException("Запрещено создавать явную петлю");
		}
		edges.add(edge);
	}

	@Override
	public T updateValue(final T value) {
		final T oldValue;
		synchronized (this) {
			oldValue = this.value;
			this.value = value;
		}
		return oldValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final VertexImpl<?, ?> vertex = (VertexImpl<?, ?>) o;
		return id.equals(vertex.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String getId() {
		return id;
	}
}
