package graph;

import java.util.ArrayList;
import java.util.List;

class Vertex<T> {
	
	T payload;
	boolean wasVisited;
	List<Edge> edges; 
	
	Vertex(T payload) {
		this.payload = payload;
		this.edges = new ArrayList<>();
	}
	
}
