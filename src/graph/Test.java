package graph;

import queue.AbstractQueue;

public class Test {

	public static void main(String[] args) {
		//biGraphTest(new ListGraph<Character>(2));
		//directedGraphTest(new MatrixDirectedGraph<Character>(2));
		//biGraphWeightedTest(new MatrixBidirectedWeightedGraph<Character>(2));
		//topologicalSort();
		directedWeightedGraphTest(new LinkedGraph<Character>(2));
		//directedWeightedGraphTest(new MatrixDirectedWeightedGraph<Character>(2));
		//directedWeightedGraphTest(new MatrixDirectedWeightedGraph<Character>(2));
	}
	
	private static void directedWeightedGraphTest(DirectedGraph<Character> graph) {
		Character A = new Character('A');
		Character B = new Character('B');
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(new Character('C'));
		graph.addVertex(new Character('D'));
		graph.addVertex(new Character('E'));
		
		graph.addEdge(new Edge(0, 1, 50));
		graph.addEdge(new Edge(0, 3, 80));
		graph.addEdge(new Edge(1, 2, 60));
		graph.addEdge(new Edge(1, 3, 90));
		graph.addEdge(new Edge(2, 4, 40));
		graph.addEdge(new Edge(3, 2, 20));
		graph.addEdge(new Edge(3, 4, 70));
		graph.addEdge(new Edge(4, 1, 50));
		
		Path<Character>[] list = graph.dijkstraAlgorithm(4);
		for (int i = 0; i < list.length; i++) {
			list[i].path.forEach(s -> {
				System.out.print(s + " ");
			});
			System.out.println(list[i].distance);
		}
		System.out.println(graph.toString());
		AbstractQueue<Character>[] connectivities = graph.getConnectivities();
		for (int i = 0; i < connectivities.length; i++) {
			connectivities[i].display();
		}
		graph.deleteVertex(1);
		System.out.println(graph.toString());
		
	}
	
	private static void directedGraphTest(DirectedGraph<Character> graph) {
		Character A = new Character('A');
		Character B = new Character('B');
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(new Character('C'));
		graph.addVertex(new Character('D'));
		graph.addVertex(new Character('E'));
		
		graph.addEdge(new Edge(0, 2));
		graph.addEdge(new Edge(1, 0));
		graph.addEdge(new Edge(1, 4));
		graph.addEdge(new Edge(4, 2));
		graph.addEdge(new Edge(3, 4));
		
		System.out.println(graph.toString());
		AbstractQueue<Character>[] connectivities = graph.getConnectivities();
		for (int i = 0; i < connectivities.length; i++) {
			connectivities[i].display();
		}
		
	}
	
	private static void biGraphWeightedTest(BidirectedGraph<Character> graph) {
		Character A = new Character('A');
		Character B = new Character('B');
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(new Character('C'));
		graph.addVertex(new Character('D'));
		graph.addVertex(new Character('E'));
		graph.addVertex(new Character('F'));
		
		graph.addEdge(new Edge(0, 1, 6.0));
		graph.addEdge(new Edge(0, 3, 4));
		graph.addEdge(new Edge(1, 2, 10));
		graph.addEdge(new Edge(1, 3, 7));
		graph.addEdge(new Edge(1, 4, 7));
		graph.addEdge(new Edge(2, 3, 8));
		graph.addEdge(new Edge(2, 4, 5));
		graph.addEdge(new Edge(2, 5, 6));
		graph.addEdge(new Edge(3, 4, 12));
		graph.addEdge(new Edge(4, 5, 7));
		
		System.out.println(graph.toString());
		System.out.println("depthFirstSearch");
		graph.depthFirstSearch().display();
		System.out.println("breadthFirstSearch");
		graph.breadthFirstSearch().display();
		
		BidirectedGraph<Character> mst = graph.getMinimumSpanningTree(0);
		System.out.println("MinimumSpanningTree");
		System.out.println(mst.toString());
		System.out.println("breadthFirstSearch in mst");
		mst.breadthFirstSearch().display();
		
		System.out.println("create cycle between A and F");
		graph.addEdge(new Edge(0, 5, 9000));
		System.out.println(graph.toString());
		graph.breadthFirstSearch().display();
	}
	
	private static void biGraphTest(BidirectedGraph<Character> graph) {
		Character A = new Character('A');
		Character B = new Character('B');
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(new Character('C'));
		graph.addVertex(new Character('D'));
		graph.addVertex(new Character('E'));
		graph.addVertex(new Character('F'));
		graph.addVertex(new Character('G'));
		graph.addVertex(new Character('X'));
		
		graph.addEdge(new Edge(0, 2));
		graph.addEdge(new Edge(0, 3));
		graph.addEdge(new Edge(3, 1));
		graph.addEdge(new Edge(2, 4));
		graph.addEdge(new Edge(2, 7));
		graph.addEdge(new Edge(2, 5));
		graph.addEdge(new Edge(2, 6));
		
		System.out.println(graph.toString());
		graph.depthFirstSearch().display();
		graph.breadthFirstSearch().display();
		
		System.out.println("create cycle between A and B");
		graph.addEdge(new Edge(0, 1));
		System.out.println(graph.toString());
		graph.breadthFirstSearch().display();
		
		BidirectedGraph<Character> mst = graph.getMinimumSpanningTree(0);
		System.out.println(mst.toString());
		mst.breadthFirstSearch().display();
	}
	
	private static void topologicalSort() {
		DirectedGraph<Character> graph = new MatrixDirectedGraph<>(20);
		graph.addVertex('A'); // 0
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4
		graph.addVertex('F'); // 5
		graph.addVertex('G'); // 6
		graph.addVertex('H'); // 7
		
		graph.addEdge(new Edge(0, 3));
		graph.addEdge(new Edge(0, 4));
		graph.addEdge(new Edge(1, 4));
		graph.addEdge(new Edge(2, 5));
		graph.addEdge(new Edge(3, 6));
		graph.addEdge(new Edge(4, 6));
		graph.addEdge(new Edge(5, 7));
		graph.addEdge(new Edge(6, 7));
		
		System.out.println(graph.toString());
		graph.topologicalSort().display();
	}

}
