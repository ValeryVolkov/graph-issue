package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

import java.util.Objects;

public class EdgeImpl<T, W> implements Edge<T, W> {
	private final Vertex<T, W> first;
	private final Vertex<T, W> second;
	private final EdgeDirectional directional;
	private final W weight;

	public EdgeImpl(final Vertex<T, W> first, final Vertex<T, W> second) {
		this(first, second, null);
	}

	public EdgeImpl(final Vertex<T, W> first, final Vertex<T, W> second, final W weight) {
		this(first, second, weight, EdgeDirectional.NON_DIRECTIONAL);
	}

	public EdgeImpl(final Vertex<T, W> first, final Vertex<T, W> second, final W weight, final EdgeDirectional directional) {
		this.first = Objects.requireNonNull(first, "Невозможно создать ребро без первой вершины");
		this.second = Objects.requireNonNull(second, "Невозможно создать ребро без второй вершины");
		this.directional = Objects.requireNonNull(directional, "Не указана направленность ребра");
		first.addEdge(this);
		second.addEdge(this);
		this.weight = weight;
	}

	@Override
	public Vertex<T, W> getFirst() {
		return first;
	}

	@Override
	public Vertex<T, W> getSecond() {
		return second;
	}

	@Override
	public EdgeDirectional getDirect() {
		return directional;
	}

	@Override
	public W getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return first.getValue().toString() + ":" + second.getValue().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final EdgeImpl<?, ?> edge = (EdgeImpl<?, ?>) o;
		return first.equals(edge.first) &&
				second.equals(edge.second) &&
				directional == edge.directional;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second, directional);
	}
}
