package ru.valery.graphs;

import java.util.Collection;

public interface Vertex<T>{
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
	Collection<Edge<T>> getEdges();

	/**
	 *
	 * @param edge Новое ребро графа
	 */
	void addEdge(Edge<T> edge);

	/**
	 * Обновляем значение вершины
	 * @param value Новое значение
	 * @return Возвращаем старое значение (значение до изменения)
	 */
	T updateValue(T value);

	void setWidth(double width);

	/**
	 *
	 * @return Лучшее значение
	 */
	double getWidth();

	void setMeet(boolean meet);

	/**
	 *
	 * @return Признак посещённой вершины
	 */
	boolean isMeet();

	/**
	 *
	 * @return Последнее лучшее ребро
	 */
	Edge<T> getParentEdge();

	void setParentEdge(Edge<T> parentEdge);
}
