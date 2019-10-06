package ru.valery.graphs;

import java.util.Collection;

public interface Graph<T> extends Finder<T>{

	/**
	 * Добавить ребро
	 *
	 * @param edge Ребро графа
	 */
	void addEdge(final Edge<T> edge);


	Collection<Edge<T>> getAllEdges();
}
