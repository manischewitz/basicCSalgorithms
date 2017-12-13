package graph;


@SuppressWarnings("unchecked")
public class MatrixGraph<T> extends BidirectedGraph<T>{

	private boolean[][] adjacencyMatrix;
	
	public MatrixGraph(int vertex) {
		super(vertex);
		adjacencyMatrix = new boolean[currentMax][currentMax];
	}
	
	@Override
	public T deleteVertex(final int no) {
		if (no >= vertexSize || no < 0) {
			throw new IllegalArgumentException();
		}
		final int last = vertexSize - 1;
		T temp = vertexList[no].payload;
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
		return temp;
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
	
	@Override
	protected int noSuccessors() {
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
	
	//shallow copy
	@Override
	public MatrixGraph<T> clone() {
		MatrixGraph<T> graph = new MatrixGraph<>(currentMax);
		
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
	protected void ensureCapacity(int newCapacity) {
		Vertex<T>[] newVertexList = new Vertex[newCapacity];
		for (int i = 0; i < currentMax; i++) {
			newVertexList[i] = vertexList[i];
		}
		
		boolean[][] newAdjacencyMatrix = new boolean[newCapacity][newCapacity];;
		for (int i = 0; i < currentMax; i++) {
			for (int j = 0; j < currentMax; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
	
		currentMax *= 2;
		vertexList = newVertexList;
		adjacencyMatrix = newAdjacencyMatrix;
	}

	@Override
	public void addEdge(Edge e) {
		adjacencyMatrix[e.from][e.to] = true;
		adjacencyMatrix[e.to][e.from] = true;
	}
	
	@Override
	public void removeEdge(Edge e) {
		adjacencyMatrix[e.from][e.to] = false;
		adjacencyMatrix[e.to][e.from] = false;
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
	public BidirectedGraph<T> getMinimumSpanningTree(int startFrom) {
		BidirectedGraph<T> tree = new MatrixGraph<>(vertexSize);
		if (vertexSize == 0) {
			return tree;
		}
		
		vertexList[0].wasVisited = true;
		stack.push(0);
		tree.addVertex(vertexList[0].payload);
		
		while (!stack.isEmpty()) {
			int currentVertex = stack.peek();
			int vertex = getAdjancedUnvisitedVertex(currentVertex);
			
			if (vertex == -1) {
				stack.pop();
			} else {
				vertexList[vertex].wasVisited = true;
				stack.push(vertex);
				
				tree.addVertex(vertexList[vertex].payload);
				tree.addEdge(new Edge(currentVertex, vertex));
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			vertexList[i].wasVisited = false;
		}
		return tree;
	}
	
/*
	private class Vertex<X> {
		X payload;
		boolean wasVisited;
		
		public Vertex(X payload) {
			this.payload = payload;
		}
	}
	
	private Vertex<T>[] vertexList;
	private boolean[][] adjacencyMatrix;
	private int vertexSize;
	private AbstractStack<Integer> stack;
	private int currentMax;
	private AbstractQueue<Integer> queue;
	
	public MatrixGraph(int vertex) {
		currentMax = vertex;
		vertexList = new Vertex[currentMax];
		adjacencyMatrix = new boolean[currentMax][currentMax];
		stack = new ArrayStack<Integer>();
		queue = new ArrayQueue<>();
	}
	
	private void ensureCapacity(int newCapacity) {
		Vertex<T>[] newVertexList = new Vertex[newCapacity];
		for (int i = 0; i < currentMax; i++) {
			newVertexList[i] = vertexList[i];
		}
		
		boolean[][] newAdjacencyMatrix = new boolean[newCapacity][newCapacity];;
		for (int i = 0; i < currentMax; i++) {
			for (int j = 0; j < currentMax; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
	
		currentMax *= 2;
		vertexList = newVertexList;
		adjacencyMatrix = newAdjacencyMatrix;
	}
	
	public void addVertex(T vertex) {
		if (currentMax == vertexSize) {
			ensureCapacity(currentMax * 2);
		}
		vertexList[vertexSize] = new Vertex<>(vertex);
		vertexSize++;
	}
	
	public void addBidirectedEdge(int from, int to) {
		adjacencyMatrix[from][to] = true;
		adjacencyMatrix[to][from] = true;
	}
	
	public void addDirectedEdge(int from, int to) {
		adjacencyMatrix[from][to] = true;
	}
	
	private int getAdjancedUnvisitedVertex(int v) {
		for (int j = 0; j < vertexSize; j++) {
			if (adjacencyMatrix[v][j] && !vertexList[j].wasVisited) {
				return j;
			}
		}
		return -1;
	}
	
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
	
	public MatrixGraph<T> getMinimumSpanningTree() {
		MatrixGraph<T> tree = new MatrixGraph<>(vertexSize);
		if (vertexSize == 0) {
			return tree;
		}
		
		vertexList[0].wasVisited = true;
		stack.push(0);
		tree.addVertex(vertexList[0].payload);
		
		while (!stack.isEmpty()) {
			int currentVertex = stack.peek();
			int vertex = getAdjancedUnvisitedVertex(currentVertex);
			
			if (vertex == -1) {
				stack.pop();
			} else {
				vertexList[vertex].wasVisited = true;
				stack.push(vertex);
				
				tree.addVertex(vertexList[vertex].payload);
				tree.addBidirectedEdge(currentVertex, vertex);
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			vertexList[i].wasVisited = false;
		}
		return tree;
	}
	
	public int size() {
		return vertexSize;
	}
	
	public AbstractQueue<T> topologicalSort() {
		MatrixGraph<T> sorted = this.clone();
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
	
	//shallow copy
	@Override
	public MatrixGraph<T> clone() {
		MatrixGraph<T> graph = new MatrixGraph<>(currentMax);
		
		for (int i = 0; i < vertexSize; i++) {
			graph.vertexList[i] = vertexList[i];
		}
		
		for (int i = 0; i < vertexSize; i++) {
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
	*/
}
