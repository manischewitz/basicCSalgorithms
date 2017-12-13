package graph;

import queue.AbstractQueue;
import queue.ArrayQueue;

public class MatrixDirectedGraph<T> extends DirectedGraph<T> {

	private boolean[][] adjacencyMatrix;
	
	public MatrixDirectedGraph(int vertex) {
		super(vertex);
		adjacencyMatrix = new boolean[currentMax][currentMax];
	}

	@Override
	public void addEdge(Edge e) {
		adjacencyMatrix[e.from][e.to] = true;
	}
	
	@Override
	public void removeEdge(Edge e) {
		adjacencyMatrix[e.from][e.to] = false;
	}

	public T deleteVertex(final int no) {
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
	
	public int noSuccessors() {
		boolean isEdge;
		
		for(int row = 0; row < vertexSize; row++) {
			isEdge = false;
			
			for (int col = 0; col < vertexSize; col++) {
				if(adjacencyMatrix[row][col]) {
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
		MatrixDirectedGraph<T> sorted = this.clone();
		AbstractQueue<T> sortedQueue = new ArrayQueue<>(vertexSize);
		
		while (sorted.vertexSize > 0) {
			int currentVertex = sorted.noSuccessors();
			
			if (currentVertex == -1) {
				//"Graph with cycles"
				return null;
			}
			sortedQueue.insert(sorted.vertexList[currentVertex].payload);
			sorted.deleteVertex(currentVertex);
		}
		return sortedQueue;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void ensureCapacity(int newCapacity) {
		Vertex<T>[] newVertexList = new Vertex[newCapacity];
		boolean[][] newAdjacencyMatrix = new boolean[newCapacity][newCapacity];
		for (int i = 0; i < currentMax; i++) {
			newVertexList[i] = vertexList[i];
			for (int j = 0; j < currentMax; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
		
		currentMax *= 2;
		vertexList = newVertexList;
		adjacencyMatrix = newAdjacencyMatrix;
	}

	@Override
	public MatrixDirectedGraph<T> clone() {
		MatrixDirectedGraph<T> graph = new MatrixDirectedGraph<T>(currentMax);
		
		for (int i = 0; i < vertexSize; i++) {
			graph.vertexList[i] = vertexList[i];
			for (int j = 0; j < currentMax; j++) {
				graph.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
		
		graph.vertexSize = vertexSize;
		return graph;
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
				if (adjacencyMatrix[i][j]) {
					sb.append("1 ");
				} else {
					sb.append("0 ");
				}
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	protected int getAdjancedUnvisitedVertex(int v) {
		for (int j = 0; j < vertexSize; j++) {
			if (adjacencyMatrix[v][j] && !vertexList[j].wasVisited) {
				return j;
			}
		}
		return -1;
	}
	
	@Override
	public Path<T>[] dijkstraAlgorithm(int startFrom) {
		throw new UnsupportedOperationException();
	}

	public boolean[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(boolean[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

}
