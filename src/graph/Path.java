package graph;

import java.util.ArrayList;
import java.util.List;

public class Path<T> {
	
	public List<T> path;
	public double distance;
	
	Path (double d) {
		path = new ArrayList<>();
		distance = d;
	}
}
