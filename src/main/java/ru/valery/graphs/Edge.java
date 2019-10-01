package ru.valery.graphs;

import ru.valery.graphs.misc.EdgeDirectional;

/**
 * Ребро
 */
public interface Edge<T, W> {

	/**
	 * @return Первая вершина ребра
	 */
	Vertex<T, W> getFirst();

	/**
	 * @return Последняя вершина ребра
	 */
	Vertex<T, W> getSecond();


	/**
	 * Направление графа
	 *
	 * @return Прямое, обратное, ненаправленный (
	 * {@link EdgeDirectional.FORWARD},
	 * {@link EdgeDirectional.REVERSE},
	 * {@link EdgeDirectional.NON_DIRECTIONAL})
	 */
	EdgeDirectional getDirect();

	/**
	 * @return Возвращает вес ребра в указанном пользователем типе
	 */
	W getWeight();

}
