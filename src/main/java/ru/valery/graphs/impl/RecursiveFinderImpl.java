package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Finder;
import ru.valery.graphs.Vertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

public class RecursiveFinderImpl<T, W> implements Finder<T, W> {
	private final Set<Vertex<T, W>> vertices = new CopyOnWriteArraySet<>();

	@Override
	public Collection<Edge<T, W>> getPath(final Vertex<T, W> first, final Vertex<T, W> second) {
		final Vertex<T, W> nnFirst = getVertex(Objects.requireNonNull(first));
		final Vertex<T, W> nnSecond = getVertex(Objects.requireNonNull(second));
		if (vertices.contains(nnFirst) && vertices.contains(nnSecond)) {
			final Deque<Edge<T, W>> ret = new ConcurrentLinkedDeque<>();
			for (final Edge<T, W> edge : nnFirst.getEdges()) {
				ret.push(edge);
				if (nnFirst.equals(edge.getFirst()) && findVertex(ret, nnSecond)) {
					return getSortedEdges(ret);
				}
				if (nnFirst.equals(edge.getSecond()) && findVertex(ret, nnSecond)) {
					return getSortedEdges(ret);
				}
				ret.pop();
			}
		}
		return Collections.emptyList();
	}

	private Vertex<T, W> getVertex(Vertex<T, W> requireNonNull) {
		return vertices.stream().filter(v -> v.getId().equals(requireNonNull.getId())).findFirst().orElse(null);
	}

	private List<Edge<T, W>> getSortedEdges(Deque<Edge<T, W>> ret) {
		final List<Edge<T, W>> path = new ArrayList<>();
		final Iterator<Edge<T, W>> ei = ret.descendingIterator();
		while (ei.hasNext()) {
			path.add(ei.next());
		}
		return path;
	}

	private boolean findVertex(Deque<Edge<T, W>> ret, Vertex<T, W> next) {
		final Edge<T, W> edge = ret.peek();
		if (edge != null) {
			if ((next.equals(edge.getFirst()) || next.equals(edge.getSecond()))) {
				return true;
			}

			//region Обход
			for (final Edge<T, W> e : edge.getSecond().getEdges()) {
				if (!ret.contains(e)) {
					ret.push(e);
					if (findVertex(ret, next)) {
						return true;
					}
					ret.pop();
				}
			}
			//endregion
		}
		return false;
	}

	@Override
	public void addVertex(final Vertex<T, W> vertex) {
		vertices.add(vertex);
	}

	@Override
	public void traverse(Consumer<Vertex<T, W>> consumer) {
		vertices.forEach(consumer);
	}

	@Override
	public Collection<Edge<T, W>> getAllEdges() {
		final Set<Edge<T, W>> ret = new HashSet<>();
		vertices.forEach(v -> ret.addAll(v.getEdges()));
		return ret;
	}
}
