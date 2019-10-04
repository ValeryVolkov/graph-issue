package ru.valery.graphs;

import java.util.Collection;
import java.util.function.Consumer;

public interface Finder<T, W> {

	/**
	 * Добавить вершину
	 *
	 * @param vertex Вершина графа
	 */
	void addVertex(final Vertex<T, W> vertex);


	/**
	 * Возвращает список рёбер между первой и второй вершиной
	 *
	 * @param first  Первая вершина
	 * @param second Вторая вершина
	 * @return Список рёбер может возвращать пустую коллекцию при отсутствии маршрута
	 */
	Collection<Edge<T, W>> getPath(final Vertex<T, W> first, final Vertex<T, W> second);

	/**
	 * Выполняем операцию по всему набору вершин
	 * @param consumer потребитель вершин ,)
	 */
	void traverse(final Consumer<Vertex<T, W>> consumer);

	Collection<Edge<T, W>> getAllEdges();
}
