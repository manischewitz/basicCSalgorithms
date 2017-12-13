package graph;

import queue.AbstractQueue;

public class MatrixDirectedWeightedGraph<T> extends DirectedGraph<T> {

	private double[][] adjacencyMatrix;
	
	public MatrixDirectedWeightedGraph(int vertex) {
		super(vertex);
		adjacencyMatrix = new double[vertex][vertex];
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		} 
	}

	@Override
	public void addEdge(Edge edge) {
		adjacencyMatrix[edge.from][edge.to] = edge.distance;
	}

	@Override
	public void removeEdge(Edge edge) {
		adjacencyMatrix[edge.from][edge.to] = Double.POSITIVE_INFINITY;
	}

	@Override
	public T deleteVertex(int no) {
		if (no >= vertexSize || no < 0) {
			throw new IllegalArgumentException();
		}
		final int last = vertexSize - 1;
		if (no != last) {
			for (int i = no; i < last; i++) {
				vertexList[i] = vertexList[i + 1];
			}
			for (int row = no; row < last; row++) {
				moveRowUp(row, vertexSize);
			}
			for (int col = no; col < last; col++) {
				moveColLeft(col, last);
			}
		}
		vertexSize--;
		return vertexList[no].payload;
	}
	
	private void moveRowUp(int row, int length) {
		for (int col = 0; col < length; col++) {
			adjacencyMatrix[row][col] = adjacencyMatrix[row + 1][col];
		}
	}
	
	private void moveColLeft(int col, int length) {
		for (int row = 0; row < length; row++) {
			adjacencyMatrix[row][col] = adjacencyMatrix[row][col + 1];
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void ensureCapacity(int newCapacity) {
		Vertex<T>[] newVertexList = new Vertex[newCapacity];
		for (int i = 0; i < vertexSize; i++) {
			newVertexList[i] = vertexList[i];
		}
		
		double[][] newAdjacencyMatrix = new double[newCapacity][newCapacity];;
		for (int i = 0; i < newCapacity; i++) {
			for (int j = 0; j < newCapacity; j++) {
				if (i < vertexSize && j < vertexSize) {
					newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
				} else {
					newAdjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
	
		currentMax *= 2;
		vertexList = newVertexList;
		adjacencyMatrix = newAdjacencyMatrix;
	}

	@Override
	protected int noSuccessors() {
		boolean isEdge;
		
		for(int row = 0; row < vertexSize; row++) {
			isEdge = false;
			
			for (int col = 0; col < vertexSize; col++) {
				if(adjacencyMatrix[row][col] < Double.POSITIVE_INFINITY) {
					isEdge = true;
					break;
				}
			}
			if (!isEdge) {
				return row;
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
		for (int j = 0; j < vertexSize; j++) {
			if (adjacencyMatrix[v][j] != Double.POSITIVE_INFINITY 
					&& !vertexList[j].wasVisited) {
				return j;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < vertexSize; i++) {
			sb.append(vertexList[i].payload);
			sb.append(' ');
		}
		sb.append('\n');
		
		for (int i = 0; i < vertexSize; i++) {
			for (int j = 0; j < vertexSize; j++) {
				if (adjacencyMatrix[i][j] != Double.POSITIVE_INFINITY) {
					sb.append(adjacencyMatrix[i][j]);
					sb.append(" ");
				} else {
					sb.append("inf ");
				}
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public Path<T>[] dijkstraAlgorithm(final int startFrom) {
		
		if (startFrom >= vertexSize || startFrom < 0) {
			throw new IllegalArgumentException();
		}
		
		Distance[] paths = new Distance[vertexSize];
		vertexList[startFrom].wasVisited = true;
		
		for (int j = 0; j < vertexSize; j++) {
			paths[j] = new Distance(startFrom, adjacencyMatrix[startFrom][j]);
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
		for (int i = 0; i < vertexSize; i++) {
			if (!vertexList[i].wasVisited) {
				double currentToFringe = adjacencyMatrix[current][i];
				double startToFringe = currentToFringe + minDistance;
				double shortestPath = paths[i].distance;
				
				if (startToFringe < shortestPath) {
					paths[i].parentVertex = current;
					paths[i].distance = startToFringe;
				}
			}
		}
	}
	
}
