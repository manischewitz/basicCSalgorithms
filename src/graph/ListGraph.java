package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ListGraph<T> extends BidirectedGraph<T> {

	private List<Integer>[] adjacencyList;
	
	public ListGraph(int vertex) {
		super(vertex);
		adjacencyList = new LinkedList[vertex];
		for (int i = 0; i < vertex; i++) {
			adjacencyList[i] = new LinkedList<>();
		}
	}

	@Override
	public void addEdge(Edge edge) {
		if (!adjacencyList[edge.from].contains(edge.to) && 
				!adjacencyList[edge.to].contains(edge.from)) {
			adjacencyList[edge.from].add(edge.to);
			adjacencyList[edge.to].add(edge.from);
		}
	}

	@Override
	protected void ensureCapacity(int newCapacity) {
		Vertex<T>[] newVertexList = new Vertex[newCapacity];
		for (int i = 0; i < currentMax; i++) {
			newVertexList[i] = vertexList[i];
		}
		
		List<Integer>[] newAdjacencyList 
		= new LinkedList[newCapacity];
		for (int i = 0; i < newCapacity; i++) {
			if (i < currentMax) {
				newAdjacencyList[i] = adjacencyList[i];
			} else {
				newAdjacencyList[i] = new LinkedList<>();
			}
			
		}
	
		currentMax *= 2;
		vertexList = newVertexList;
		adjacencyList = newAdjacencyList;
	}

	@Override
	protected int getAdjancedUnvisitedVertex(int v) {
		Iterator<Integer> iterator = adjacencyList[v].iterator();
		while (iterator.hasNext()) {
			Integer value = iterator.next();
			if (!vertexList[value].wasVisited) {
				return value;
			}
		}
		return -1;
	}

	@Override
	public T deleteVertex(int no) {
		if (no >= vertexSize || no < 0) {
			throw new IllegalArgumentException();
		}
		final int last = vertexSize - 1;
		if (no != last) {
			for (int i = no; i < last; i++) {
				adjacencyList[i] = adjacencyList[i + 1];
			}
			
			for (int i = 0; i < last; i++) {
				adjacencyList[i].remove(new Integer(no));
			}
			
			for (int i = no; i < last; i++) {
				vertexList[i] = vertexList[i + 1];
			}
		}
		vertexSize--;
		return vertexList[no].payload;
	}

	@Override
	protected int noSuccessors() {
		for (int i = 0; i < vertexSize; i++) {
			if(adjacencyList[i].isEmpty()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public BidirectedGraph<T> getMinimumSpanningTree(int startFrom) {
		BidirectedGraph<T> tree = new ListGraph<>(vertexSize);
		if (vertexSize == 0) {
			return tree;
		}
		
		vertexList[0].wasVisited = true;
		queue.insert(0);
		tree.addVertex(vertexList[0].payload);
		int childVertex;
		
		while (!queue.isEmpty()) {
			int parentVertex = queue.remove();
			
			while ((childVertex = getAdjancedUnvisitedVertex(parentVertex)) != -1) {
				vertexList[childVertex].wasVisited = true;
				queue.insert(childVertex);
				tree.addVertex(vertexList[childVertex].payload);
				tree.addEdge(new Edge(parentVertex, childVertex));
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			vertexList[i].wasVisited = false;
		}
		return tree;
	}

	@Override
	public void removeEdge(Edge edge) {
		adjacencyList[edge.from].remove(edge.to);
		adjacencyList[edge.to].remove(edge.from);
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < vertexSize; i++) {
			sb.append(vertexList[i].payload);
			sb.append(':');
			
			adjacencyList[i].forEach(val -> {
				sb.append(' ');
				sb.append(vertexList[val].payload);
			});
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	@Override
	public ListGraph<T> clone() {
		ListGraph<T> graph = new ListGraph<>(currentMax);
		
		for (int i = 0; i < vertexSize; i++) {
			graph.vertexList[i] = vertexList[i];
			graph.adjacencyList[i] = adjacencyList[i];
		}
		
		graph.vertexSize = vertexSize;
		return graph;
	}

}
