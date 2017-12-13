package graph;

public interface Graph<T> {

	public void addEdge(Edge edge);
	
	public void removeEdge(Edge edge);
	
	public int size();
	
	public T deleteVertex(int no);
	
	public void addVertex(T vertex);
	
	public T vertexWithNoSuccessors();
	
}
