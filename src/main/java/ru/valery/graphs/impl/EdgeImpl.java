package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

import java.util.Objects;

public class EdgeImpl<T> implements Edge<T> {
	private final Vertex<T> first;
	private final Vertex<T> second;
	private final EdgeDirectional directional;
	private final double weight;

	public EdgeImpl(final Vertex<T> first, final Vertex<T> second) {
		this(first, second, Double.MAX_VALUE);
	}

	public EdgeImpl(final Vertex<T> first, final Vertex<T> second, final double weight) {
		this(first, second, weight, EdgeDirectional.NON_DIRECTIONAL);
	}

	public EdgeImpl(final Vertex<T> first, final Vertex<T> second, final double weight, final EdgeDirectional directional) {
		this.first = Objects.requireNonNull(first, "Невозможно создать ребро без первой вершины");
		this.second = Objects.requireNonNull(second, "Невозможно создать ребро без второй вершины");
		this.directional = Objects.requireNonNull(directional, "Не указана направленность ребра");
		first.addEdge(this);
		second.addEdge(this);
		this.weight = weight;
	}

	@Override
	public Vertex<T> getFirst() {
		return first;
	}

	@Override
	public Vertex<T> getSecond() {
		return second;
	}

	@Override
	public EdgeDirectional getDirect() {
		return directional;
	}

	@Override
	public double getWeight() {
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
		final EdgeImpl<?> edge = (EdgeImpl<?>) o;
		return first.equals(edge.first) &&
				second.equals(edge.second) &&
				directional == edge.directional;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second, directional);
	}
}
