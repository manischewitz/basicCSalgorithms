package graph;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.PriorityQueue;


public class MatrixBidirectedWeightedGraph<T> extends BidirectedGraph<T>{

	private double[][] adjacencyMatrix;
	private AbstractQueue<Edge> priorityQueue;
	
	public MatrixBidirectedWeightedGraph(int vertex) {
		super(vertex);
		adjacencyMatrix = new double[vertex][vertex];
		priorityQueue = new PriorityQueue<>();//heap-based
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		} 
	}
	
	@Override
	public void addEdge(Edge edge) {
		adjacencyMatrix[edge.from][edge.to] = edge.distance;
		adjacencyMatrix[edge.to][edge.from] = edge.distance;
	}

	@Override
	public void removeEdge(Edge edge) {
		adjacencyMatrix[edge.from][edge.to] = Double.POSITIVE_INFINITY;
		adjacencyMatrix[edge.to][edge.from] = Double.POSITIVE_INFINITY;
	}

	@Override
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
	public BidirectedGraph<T> getMinimumSpanningTree(int startFrom) {
		BidirectedGraph<T> tree = 
				new MatrixBidirectedWeightedGraph<>(vertexSize);
		int currentVertex = startFrom;
		
		for (int t = 0; t < vertexSize - 1; t++) {
			vertexList[currentVertex].wasVisited = true;
			
			for (int j = 0; j < vertexSize; j++) {
				double distance = adjacencyMatrix[currentVertex][j];
				if (j == currentVertex || vertexList[j].wasVisited 
						|| distance == Double.POSITIVE_INFINITY) {
					continue;
				}
				insertIntoQueue(currentVertex, j, distance);
			}
			if (priorityQueue.isEmpty()) { // if graph is not connected
				break;
			}
			Edge edge = priorityQueue.remove();
			currentVertex = edge.to;
			tree.addEdge(edge);
		}
		
		for (int i = 0; i < vertexSize; i++) {
			tree.addVertex(vertexList[i].payload);
			vertexList[i].wasVisited = false;
		}
		return tree;
	}

	private void insertIntoQueue(int from,int to, double distance) {
		Iterator<Edge> iterator = priorityQueue.iterator();
		while (iterator.hasNext()) {
			Edge val = iterator.next();
			if (val.to == to && val.distance > distance) {
				priorityQueue.remove(val);
				break;
			} else if (val.to == to && val.distance < distance) {
				return;
			}
		}
		priorityQueue.add(new Edge(from ,to, distance));
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

	
}
