package ru.valery.graphs;

import java.util.Collection;

public interface Vertex<T, W>{
	/**
	 *
	 * @return Объект в вершине графа
	 */
	T getValue();

	/**
	 *
	 * @return Список рёбер графа
	 */
	Collection<Edge<T, W>> getEdges();

	/**
	 *
	 * @param edge Новое ребро графа
	 */
	void addEdge(Edge<T,W> edge);

	void updateValue(T value);
}
