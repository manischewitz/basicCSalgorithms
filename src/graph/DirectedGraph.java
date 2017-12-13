package graph;

import queue.AbstractQueue;
import queue.ArrayQueue;
import stack.AbstractStack;
import stack.ArrayStack;

public abstract class DirectedGraph<T> implements Graph<T> {

	protected Vertex<T>[] vertexList;
	protected int vertexSize;
	protected AbstractStack<Integer> stack;
	protected int currentMax;
	protected AbstractQueue<Integer> queue;
	
	@SuppressWarnings("unchecked")
	public DirectedGraph(int vertex) {
		currentMax = vertex;
		vertexList = new Vertex[currentMax];
		stack = new ArrayStack<Integer>();
		queue = new ArrayQueue<>();
	}
	
	protected abstract void ensureCapacity(int newCapacity);
	
	public void addVertex(T vertex) {
		if (currentMax == vertexSize) {
			ensureCapacity(currentMax * 2);
		}
		vertexList[vertexSize] = new Vertex<>(vertex);
		vertexSize++;
	}
	
	public int size() {
		return vertexSize;
	}
	
	protected abstract int noSuccessors();
	
	public abstract AbstractQueue<T> topologicalSort();
	
	public T vertexWithNoSuccessors() {
		int i = noSuccessors();
		return (i != -1) ? vertexList[i].payload : null;
	}
	
	protected abstract int getAdjancedUnvisitedVertex(int v);
	
	public abstract Path<T>[] dijkstraAlgorithm(final int startFrom);
	
	@SuppressWarnings("unchecked")
	public AbstractQueue<T>[] getConnectivities(){
		AbstractQueue<T>[] paths = new ArrayQueue[vertexSize];
		
		if (vertexSize != 0) {
			for (int i = 0; i < vertexSize; i++) {
				paths[i] = depthFirstSearch(i);
			}
		}
		return paths;
	}
	
	
	private AbstractQueue<T> depthFirstSearch(int forVertex) {
		AbstractQueue<T> path = new ArrayQueue<>(vertexSize);
		
		vertexList[forVertex].wasVisited = true;
		stack.push(forVertex);
		path.insert(vertexList[forVertex].payload);
		
		while (!stack.isEmpty()) {
			int v = getAdjancedUnvisitedVertex(stack.peek());
			if (v == -1) {
				stack.pop();
			} else {
				vertexList[v].wasVisited = true;
				path.insert(vertexList[v].payload);
				stack.push(v);
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			vertexList[i].wasVisited = false;
		}
		return path;
	}
	
}
