package graph;

import queue.AbstractQueue;
import queue.ArrayQueue;
import stack.AbstractStack;
import stack.ArrayStack;

public abstract class BidirectedGraph<T> implements Graph<T> {

	protected Vertex<T>[] vertexList;
	protected int vertexSize;
	protected AbstractStack<Integer> stack;
	protected int currentMax;
	protected AbstractQueue<Integer> queue;
	
	@SuppressWarnings("unchecked")
	public BidirectedGraph(int vertex) {
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
	
	protected abstract int getAdjancedUnvisitedVertex(int v);
	
	public AbstractQueue<T> depthFirstSearch() {
		AbstractQueue<T> path = new ArrayQueue<>(vertexSize);
		
		if (vertexSize == 0) {
			return path;
		}
		vertexList[0].wasVisited = true;
		stack.push(0);
		path.insert(vertexList[0].payload);
		
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
	
	public AbstractQueue<T> breadthFirstSearch() {
		AbstractQueue<T> path = new ArrayQueue<>(vertexSize);
		
		if (vertexSize == 0) {
			return path;
		}
		vertexList[0].wasVisited = true;
		queue.insert(0);
		path.insert(vertexList[0].payload);
		int childVertex;
		
		while (!queue.isEmpty()) {
			int parentVertex = queue.remove();
			
			while ((childVertex = getAdjancedUnvisitedVertex(parentVertex)) != -1) {
				vertexList[childVertex].wasVisited = true;
				path.insert(vertexList[childVertex].payload);
				queue.insert(childVertex);
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			vertexList[i].wasVisited = false;
		}
		return path;
	}
	
	public abstract BidirectedGraph<T> getMinimumSpanningTree(int startFrom);
	
	public int size() {
		return vertexSize;
	}
	
	protected abstract int noSuccessors();
	
	public T vertexWithNoSuccessors() {
		int i = noSuccessors();
		return (i != -1) ? vertexList[i].payload : null;
	}

}
