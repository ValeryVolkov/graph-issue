package ru.valery.graphs.impl;

import ru.valery.graphs.Edge;
import ru.valery.graphs.Finder;
import ru.valery.graphs.Vertex;
import ru.valery.graphs.misc.EdgeDirectional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Реализация алгоритма Дейкстры
 *
 * @param <T> Тип значения хранимого в вершине
 */
public class FinderImpl<T> implements Finder<T> {
	private final Set<Vertex<T>> vertices = new CopyOnWriteArraySet<>();

	@Override
	public Collection<Edge<T>> getPath(Vertex<T> first, Vertex<T> second) {
		//Сбрасываем состояние вершин
		vertices.forEach(v -> {
			v.setMeet(false);
			v.setWidth(Double.MAX_VALUE);
			v.setParentEdge(null);
		});

		first = getVertex(Objects.requireNonNull(first));
		first.setWidth(0);//Вес стартовой вершины должен быть =0 (Дейкстра)

		second = getVertex(Objects.requireNonNull(second));

		while (vertices.stream().anyMatch(v -> !v.isMeet())) {
			//Отфильтровываем вершины в которых мы уже были
			//сортируем по возрастанию
			final Vertex<T> minVertex = vertices.stream()
					.filter(v -> !v.isMeet())
					.min(Comparator.comparingDouble(Vertex::getWidth))//Получаем вершину с минимальным значением растояния до неё
					.orElse(null);

			if (minVertex == null) break;//Если все вершины посетили

			minVertex.setMeet(true);// Помечаем посещённую вершину

			for (Edge<T> edge : minVertex
					.getEdges()
					.stream()
					.filter(e ->
							(e.getDirect() == EdgeDirectional.NON_DIRECTIONAL || e.getDirect() == EdgeDirectional.FORWARD)
									&& Objects.equals(e.getFirst(), minVertex))
					.collect(Collectors.toList())) {

				final Vertex<T> secondVertex = edge.getSecond();
				double min = Math.min(secondVertex.getWidth(), minVertex.getWidth() + edge.getWeight());
				if (min < secondVertex.getWidth()) {
					secondVertex.setParentEdge(edge);//Сохраняем последнее оптимальное ребро
				}
				secondVertex.setWidth(min);// Улучшаем значение каждой доступной вершине
			}
		}

		return getPath(second);
	}

	/**
	 * Получаем вершину по идентификатору
	 *
	 * @param vertex Искомая вершина (необходим заполненный идентификатор ID)
	 * @return Возвращаем вершину из коллекции
	 */
	private Vertex<T> getVertex(Vertex<T> vertex) {
		return vertices
				.stream()
				.filter(v -> v.getId().equals(vertex.getId()))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Восстанавливаем путь от искомой вершины к первой
	 *
	 * @param second Искомая вершина
	 * @return Набор рёбер в порядке от первой к последней вершине
	 */
	private List<Edge<T>> getPath(final Vertex<T> second) {
		if (second.getParentEdge() == null) {
			return Collections.emptyList();
		}
		final List<Edge<T>> ret = new ArrayList<>();
		Vertex<T> vertex = second;
		do {
			ret.add(vertex.getParentEdge());
			if (vertex.getParentEdge() != null) {
				vertex = vertex.getParentEdge().getFirst();
			}
		} while (vertex.getParentEdge() != null);

		Collections.reverse(ret);//Восстанавливаем порядок следования рёбер

		return ret;
	}

	@Override
	public void addVertex(final Vertex<T> vertex) {
		vertices.add(vertex);
	}

	@Override
	public void traverse(Consumer<Vertex<T>> consumer) {
		vertices.forEach(consumer);
	}

	@Override
	public Collection<Edge<T>> getAllEdges() {
		final Set<Edge<T>> ret = new HashSet<>();
		vertices.forEach(v -> ret.addAll(v.getEdges()));
		return ret;
	}
}
