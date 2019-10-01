package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

public class EdgeImpl<T, W> implements Edge<T, W> {
	private final Vertex<T, W> first;
	private final Vertex<T, W> second;
	private final EdgeDirectional directional;
	private final W weight;

	public EdgeImpl(final Vertex<T, W> first, final Vertex<T, W> second, final W weight, final EdgeDirectional directional) {
		this.first = first;
		this.second = second;
		this.directional = directional;
		this.weight = weight;
		first.addEdge(this);
		second.addEdge(this);
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
}
