package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Graph;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

public class GraphImpl<T, W> implements Graph<T, W> {
	private final Set<Vertex<T, W>> vertices = new CopyOnWriteArraySet<>();

	public GraphImpl() {
		//TODO: Добавить работу с весами (оптимальные маршруты)
		//TODO: Проверить работу с направлениями
		//TODO: Проверить циклы
		//TODO: Проверить отсутствие проблем в многопоточном режиме
		//TODO: Проверить работу на различных объемах данных 10К х 10К
		//TODO: Передача для вершин equals & hashcode - для возможности работы с дорогами
	}

	@Override
	public void addVertex(final Vertex<T, W> vertex) {
		vertices.add(vertex);
	}

	@Override
	public void addEdge(final Edge<T, W> edge) {
		vertices.add(edge.getFirst());
		vertices.add(edge.getSecond());
	}

	@Override
	public Collection<Edge<T, W>> getPath(final Vertex<T, W> first, final Vertex<T, W> second) {
		final Deque<Edge<T, W>> ret = new ConcurrentLinkedDeque<>();
		for (Vertex<T, W> vertex : vertices) {
			for (Edge<T, W> edge : vertex.getEdges()) {
				ret.push(edge);
				if (findVertex(ret, second)) {
					final List<Edge<T,W>> path = new ArrayList<>();
					final Iterator<Edge<T, W>> ei = ret.descendingIterator();
					while(ei.hasNext()){
						path.add(ei.next());
					}
					return path;
				}
				ret.pop();
			}
		}

		return Collections.emptyList();
	}

	private boolean findVertex(Deque<Edge<T, W>> ret, Vertex<T, W> second) {
		final Edge<T, W> edge = ret.peek();
		if (edge != null) {
			if ((second.equals(edge.getFirst()) || second.equals(edge.getSecond()))) {
				return true;
			}

			if(edge.getDirect()== EdgeDirectional.NON_DIRECTIONAL || edge.getDirect()==EdgeDirectional.REVERSE) {
				for (final Edge<T, W> e : edge.getFirst().getEdges()) {
					if (!ret.contains(e)) {
						ret.push(e);
						if (findVertex(ret, second)) {
							return true;
						}
						ret.pop();
					}
				}
			}
			if(edge.getDirect()== EdgeDirectional.NON_DIRECTIONAL || edge.getDirect()==EdgeDirectional.FORWARD) {
				for (final Edge<T, W> e : edge.getSecond().getEdges()) {
					if (!ret.contains(e)) {
						ret.push(e);
						if (findVertex(ret, second)) {
							return true;
						}
						ret.pop();
					}
				}
			}
		}
		return false;
	}

	@Override
	public void traverse(final Consumer<Vertex<T, W>> consumer) {
		vertices.forEach(consumer);
	}
}
