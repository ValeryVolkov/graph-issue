package ru.valery.graphs;

import java.util.Collection;

public interface Graph<T, W> extends Finder<T, W>{

	/**
	 * Добавить ребро
	 *
	 * @param edge Ребро графа
	 */
	void addEdge(final Edge<T, W> edge);


	Collection<Edge<T, W>> getAllEdges();
}
