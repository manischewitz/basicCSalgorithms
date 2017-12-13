package graph;

class Edge implements java.lang.Comparable<Edge> {

	int from;
	int to;
	double distance;
	
	Edge(int from, int to, double distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	
	Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(Edge e) {
		if (distance == e.distance) {
			return 0;
		}
		return (distance > e.distance) ? 1 : -1;
	}
	
	
}
