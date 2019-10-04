package ru.valery.graphs;

import java.util.Collection;

public interface Vertex<T, W>{
	String getId();
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

	/**
	 * Обновляем значение вершины
	 * @param value Новое значение
	 * @return Возвращаем старое значение (значение до изменения)
	 */
	T updateValue(T value);
}
