package ru.valery.graphs;

import java.util.Collection;
import java.util.function.Consumer;

public interface Graph<T, W> {
	/**
	 * Добавить вершину
	 *
	 * @param vertex Вершина графа
	 */
	void addVertex(final Vertex<T, W> vertex);

	/**
	 * Добавить ребро
	 *
	 * @param edge Ребро графа
	 */
	void addEdge(final Edge<T, W> edge);

	/**
	 * Возвращает список рёбер между первой и второй вершиной
	 *
	 * @param first  Первая вершина
	 * @param second Вторая вершина
	 * @return Список рёбер может возвращать пустую коллекцию при отсутствии маршрута
	 */
	Collection<Edge<T, W>> getPath(final Vertex<T, W> first, final Vertex<T, W> second);

	void traverse(final Consumer<Vertex<T, W>> consumer);
}
