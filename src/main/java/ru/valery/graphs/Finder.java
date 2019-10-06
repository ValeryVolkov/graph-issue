package ru.valery.graphs;

import java.util.Collection;
import java.util.function.Consumer;

public interface Finder<T> {

	/**
	 * Добавить вершину
	 *
	 * @param vertex Вершина графа
	 */
	void addVertex(final Vertex<T> vertex);


	/**
	 * Возвращает список рёбер между первой и второй вершиной
	 *
	 * @param first  Первая вершина
	 * @param second Вторая вершина
	 * @return Список рёбер может возвращать пустую коллекцию при отсутствии маршрута
	 */
	Collection<Edge<T>> getPath(final Vertex<T> first, final Vertex<T> second);

	/**
	 * Выполняем операцию по всему набору вершин
	 * @param consumer потребитель вершин ,)
	 */
	void traverse(final Consumer<Vertex<T>> consumer);

	Collection<Edge<T>> getAllEdges();
}
