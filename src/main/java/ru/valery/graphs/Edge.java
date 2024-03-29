package ru.valery.graphs;

import ru.valery.graphs.misc.EdgeDirectional;

/**
 * Интерфейс ребра
 */
public interface Edge<T> {

	/**
	 * @return Первая вершина ребра
	 */
	Vertex<T> getFirst();

	/**
	 * @return Последняя вершина ребра
	 */
	Vertex<T> getSecond();


	/**
	 * Направление графа
	 *
	 * @return Прямое, обратное, ненаправленный (
	 * {@link EdgeDirectional#FORWARD},
	 * {@link EdgeDirectional#REVERSE},
	 * {@link EdgeDirectional#NON_DIRECTIONAL})
	 */
	EdgeDirectional getDirect();

	/**
	 * @return Возвращает вес ребра (тип указывается пользователем)
	 */
	double getWeight();

}
