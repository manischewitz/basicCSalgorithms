package graph;

public class WarshallFloyd {

	// O(V^3) where V is vertex
	public static boolean[][] warshallAlgorithm(final boolean[][] adjacencyMatrix) {
		final int size = adjacencyMatrix.length;
		boolean[][] warshallMatrix = new boolean[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (adjacencyMatrix[i][j]) {
					for (int k = 0; k < size; k++) {
						if (adjacencyMatrix[k][i]) {
							warshallMatrix[k][j] = true;
						}
					}
				}
			}
		}
		return warshallMatrix;
	}
	
	// O(V^3) where V is vertex
	public static double[][] floydAlgorithm(final double[][] adjacencyMatrix) {
		final int size = adjacencyMatrix.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (adjacencyMatrix[i][j] != Double.POSITIVE_INFINITY) {
					for (int k = 0; k < size; k++) {
						double newDistance = adjacencyMatrix[k][i] + adjacencyMatrix[i][j];
						if (newDistance < adjacencyMatrix[k][j]) {
							adjacencyMatrix[k][j] = newDistance;
						}
					}
				}
			}
		}
		return adjacencyMatrix;
	}
	
	public static void main(String...strings) {
		MatrixDirectedGraph<Character> graph = new MatrixDirectedGraph<Character>(2);
		graph.addVertex(new Character('A'));
		graph.addVertex(new Character('B'));
		graph.addVertex(new Character('C'));
		graph.addVertex(new Character('D'));
		graph.addVertex(new Character('E'));
		
		graph.addEdge(new Edge(0, 2));
		graph.addEdge(new Edge(1, 0));
		graph.addEdge(new Edge(1, 4));
		graph.addEdge(new Edge(4, 2));
		graph.addEdge(new Edge(3, 4));
		
		System.out.println(graph.toString());
		boolean[][] warshallMatrix = warshallAlgorithm(graph.getAdjacencyMatrix());
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < graph.vertexSize; i++) {
			for (int j = 0; j < graph.vertexSize; j++) {
				if (warshallMatrix[i][j]) {
					sb.append("1 ");
				} else {
					sb.append("0 ");
				}
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
		
		final double inf = Double.POSITIVE_INFINITY;
		double[][] aMatrix = {{inf,inf,inf,inf}, {70,inf,inf,10}, {30,inf,inf,inf}, {inf,inf,20,inf}};
		floydAlgorithm(aMatrix);
		sb = new StringBuffer();
		
		for (int i = 0; i < aMatrix.length; i++) {
			for (int j = 0; j < aMatrix.length; j++) {
				if (aMatrix[i][j] == Double.POSITIVE_INFINITY) {
					sb.append("inf ");
				} else {
					sb.append(aMatrix[i][j]);
					sb.append(" ");
				}
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
		
	}
}
