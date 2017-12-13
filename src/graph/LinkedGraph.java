package graph;

import java.util.Iterator;
import queue.AbstractQueue;

public class LinkedGraph<T> extends DirectedGraph<T> {

	public LinkedGraph(int vertex) {
		super(vertex);
	}

	@Override
	public T deleteVertex(int no) {
		if (no >= vertexSize || no < 0) {
			throw new IllegalArgumentException();
		}
		T temp = vertexList[no].payload;
		final int last = vertexSize - 1;
		if (no != last) {
			
			for (int i = no; i < last; i++) {
				vertexList[i] = vertexList[i + 1];
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			Iterator<Edge> edges = vertexList[i].edges.iterator();
			
			while (edges.hasNext()) {
				Edge edge = edges.next();
				if (edge.to == no) {
					edges.remove();
				}
				if (edge.to > no) {
					edge.to--;
				}
			}
		}
		vertexSize--;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void ensureCapacity(int newCapacity) {
		Vertex<T>[] newVertexList = new Vertex[newCapacity];
		for (int i = 0; i < currentMax; i++) {
			newVertexList[i] = vertexList[i];
		}
		currentMax *= 2;
		vertexList = newVertexList;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < vertexSize; i++) {
			sb.append(vertexList[i].payload);
			sb.append(" -> ");
			vertexList[i].edges.forEach(val -> {
				sb.append(vertexList[val.to].payload);
				sb.append('(');
				sb.append(val.distance);
				sb.append(')');
				sb.append(' ');
			});
			sb.append('\n');
		}
		return sb.toString();
	}
	
	@Override
	protected int noSuccessors() {
		for (int i = 0; i < currentMax; i++) {
			if (vertexList[i].edges.isEmpty()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public AbstractQueue<T> topologicalSort() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected int getAdjancedUnvisitedVertex(int v) {
		Iterator<Edge> edges = vertexList[v].edges.iterator();
		while (edges.hasNext()) {
			int vertex = edges.next().to;
			if (!vertexList[vertex].wasVisited) {
				return vertex;
			}
		}
		return -1;
	}

	public Path<T>[] dijkstraAlgorithm(final int startFrom) {
		
		if (startFrom >= vertexSize || startFrom < 0) {
			throw new IllegalArgumentException();
		}
		
		Distance[] paths = new Distance[vertexSize];
		vertexList[startFrom].wasVisited = true;
		Edge current = null;
		
		for (int j = 0; j < vertexSize; j++) {
			if (vertexList[startFrom].edges.size() > j) {
				current = vertexList[startFrom].edges.get(j);
				paths[current.to] = new Distance(startFrom, current.distance);
			} 
			if (paths[j] == null) {
				paths[j] = new Distance(startFrom, Double.POSITIVE_INFINITY);
			}
		} 
		
		for (int i = 1; i < vertexSize; i++) {
			int lessExspensive = getMin(paths);
			double minDistance = (lessExspensive != -1) ?
					paths[lessExspensive].distance : Double.POSITIVE_INFINITY;
			
			if (minDistance == Double.POSITIVE_INFINITY) {
				break;
			}
			
			vertexList[lessExspensive].wasVisited = true;
			adjustPaths(paths, lessExspensive, minDistance);
		}
		
		Path<T>[] list = getPathList(paths, startFrom);
		
		for (int j = 0; j < vertexSize; j++) {
			vertexList[j].wasVisited = false;
		} 
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private Path<T>[] getPathList(Distance[] paths, int start) {
		Path<T>[] list = new Path[paths.length];
		for (int i = 0; i < paths.length; i++) {
			int index = i;
			list[i] = new Path<T>(paths[i].distance);
			while (index != start) {
				list[i].path.add(vertexList[index].payload);
				index = paths[index].parentVertex;
			}
			list[i].path.add(vertexList[start].payload);
		}
		return list;
	}
	
	private int getMin(Distance[] paths) {
		int currentMin = -1;
		double minDistance = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < vertexSize; i++) {
			if (!vertexList[i].wasVisited && paths[i].distance < minDistance) {
				minDistance = paths[i].distance;
				currentMin = i;
			}
		}
		return currentMin;
	}
	
	private void adjustPaths(Distance[] paths, int current, double minDistance) {
		vertexList[current].edges.forEach((edge) -> {
			if (!vertexList[edge.to].wasVisited) {
				double startToFringe = edge.distance + minDistance;
				double shortestPath = paths[edge.to].distance;
				
				if (startToFringe < shortestPath) {
					paths[edge.to].parentVertex = current;
					paths[edge.to].distance = startToFringe;
				}
			}
		});
	}

	@Override
	public void addEdge(graph.Edge edge) {
		vertexList[edge.from].edges.add(edge);
	}

	@Override
	public void removeEdge(graph.Edge edge) {
		vertexList[edge.from].edges.remove(edge);
	}

}
