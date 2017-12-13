package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import recursion.RecursionProblems;
// O(V!)
public class TravellingSalesmanProblem {
	
	private static List<int[]> ways;
	private static int[] currentWay;
	private static int size;
	
	public synchronized static int[] solve(double[][] matrix, int[] vertexList, int start) {
		if (start >= vertexList.length || start < 0) {
			throw new IllegalArgumentException();
		}
		
		size = vertexList.length - 1;
		currentWay = new int[size];
		for (int i = start; i < size; i++) {
			vertexList[i] = vertexList[i + 1];
		}
		for (int i = 0; i < size; i++) {
			currentWay[i] = vertexList[i];
		}
		ways = new ArrayList<>(RecursionProblems.factorial(size));
		permutations(size);
		
		int[] lessExpensivePath = null;
		double minimumWeight = Double.POSITIVE_INFINITY;
		double weight;
		
		Iterator<int[]> iterator = ways.iterator();
		while(iterator.hasNext()) {
			int[] way = iterator.next();
			weight = 0;
			int i;
			
			weight += matrix[start][way[0]];
			for (i = 1; i < size - 1; i++) {
				weight += matrix[way[i]][way[i + 1]];
			}
			weight += matrix[way[i]][start];
			if (weight < minimumWeight) {
				minimumWeight = weight;
				lessExpensivePath = way;
			}
		}
		return lessExpensivePath;
	}
	
	private static void permutations(int newSize) {
		if (newSize != 1) {
			for (int i = 0; i < newSize; i++) {
				permutations(newSize - 1);
				if (newSize == 2) {
					ways.add(Arrays.copyOf(currentWay, size));
				}
				rotate(newSize);
			}
		}
	}
	
	private static void rotate(int newSize) {
		int j;
		final int position = size - newSize;
		int temp = currentWay[position];
		for (j = position + 1; j < size; j++) {
			currentWay[j - 1] = currentWay[j];
		}
		currentWay[j - 1] = temp;
	}

	public synchronized static List<int[]> hamiltonianCycles(double[][] matrix, int[] vertexList, int start) {
		if (start >= vertexList.length || start < 0) {
			throw new IllegalArgumentException();
		}
		
		size = vertexList.length - 1;
		currentWay = new int[size];
		for (int i = start; i < size; i++) {
			vertexList[i] = vertexList[i + 1];
		}
		for (int i = 0; i < size; i++) {
			currentWay[i] = vertexList[i];
		}
		ways = new ArrayList<>(RecursionProblems.factorial(size));
		permutations(size);
		
		List<int[]> paths = new ArrayList<>();
		double weight;
		Iterator<int[]> iterator = ways.iterator();
		while(iterator.hasNext()) {
			int[] way = iterator.next();
			weight = 0;
			int i;
			
			weight += matrix[start][way[0]];
			for (i = 1; i < size - 1; i++) {
				weight += matrix[way[i]][way[i + 1]];
			}
			weight += matrix[way[i]][start];
			if (weight < Double.POSITIVE_INFINITY) {
				paths.add(way);
			}
		}
		return paths;
	}
	
	public static void main(String[] args) {
		final double inf = Double.POSITIVE_INFINITY;
		double[][] matrix = {
				{inf,91,62,55,inf},
				{91,inf,44,inf,31},
				{62,44,inf,52,45 },
				{55,inf,52,inf,83},
				{inf,31,45,83,inf}
				};
		// A=0,B=1,C=2,D=3,E=4
		int[] way = TravellingSalesmanProblem.solve(matrix, new int[]{0,1,2,3,4}, 0);
		for (int i = 0; i < way.length; i++) {
			System.out.println(way[i]+" ");
		}
		
		List<int[]> list = TravellingSalesmanProblem.hamiltonianCycles(matrix, new int[]{0,1,2,3,4}, 2);
		System.out.println(list.size());
		list.forEach((value) -> {
			System.out.println();
			for (int i = 0; i < value.length; i++) {
				System.out.println(value[i]+" ");
			}
		});
	}
}
