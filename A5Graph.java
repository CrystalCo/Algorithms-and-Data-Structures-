package assignment5;
import stdlib.*;
import algs13.Bag;
import algs13.Stack;

/**
 *  The <tt>A5Graph</tt> class represents an undirected graph of 
 *  labeled vertices.  Each vertex is referenced by an integer from
 *  0 to V-1 and has a String label.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of the neighbors adjacent to a vertex.
 *  
 *  It was adapted from the Graph class of section 4.1 of the textbook
 *  for assignment 5 in CSC 403, Spring, 2016.
 */
public class A5Graph {
	private int V;
	private int E;
	private final Bag<Integer>[] adj;
	private String[] label;
	private int[] color;

	/**
	 * Create a graph from input stream.
	 */
	
	
	@SuppressWarnings("unchecked")
	public A5Graph(In in) {
		this.V = in.readInt();
		this.label = new String[this.V];

		for (int v = 0; v < V; v++) {
			label[v] = in.readString();
		}
		
		int inputE = in.readInt();
		this.color = new int[this.V];
		this.adj = new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
			color[v] = 0;
		}

		for (int i = 0; i < inputE; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
		
	}

	/**
	 * Return the number of vertices in the graph.
	 */
	public int V() { return V; }

	/**
	 * Return the number of edges in the graph.
	 */
	public int E() { return E; }


	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}


	/**
	 * Return the list of neighbors of vertex v as an Iterable.
	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v];
	}

	/*
	 * Get the color of vertex v.
	 */
	public int getColor(int v) {
		return color[v];
	}
	
	/*
	 * Set the color of vertex v.
	 */
	public void setColor(int v, int color) {
		this.color[v] = color;
	}
	
	/*
	 * Check whether the graph has a proper coloring.
	 * A proper coloring means that every vertex has 
	 * a color (that is, no vertex has its color set to 0)
	 * and that every pair of adjacent vertices have 
	 * different colors.  
	 */
	public boolean hasProperColoring() {
		for (int v = 0; v < V(); v++) {
			if (getColor(v) == 0) return false;
			for (int w: adj(v)) {
				if (getColor(w) == getColor(v)) return false;
			}
		}
		return true;
	}
	
	/**
	 * Return a string representation of the graph.
	 */
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(label[v] + "(" + color[v] + "): ");
			boolean firstPrinted = false;
			for (int w : adj[v]) {
				if (firstPrinted) {
					s.append(", ");
				}
				else {
					firstPrinted = true;
				}
				s.append(label[w] + "(" + color[w] + ")");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}
