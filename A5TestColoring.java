package assignment5;

//Student Name : Abdulaziz Alqulaysh

import algs13.Queue;

/*
 * For assignment 5, you will modify this program by adding
 * the methods described below.  You may also add any other
 * methods you need.
 * 
 * Keep in mind that when an A5Graph is constructed, the 
 * vertices are assigned the color 0, which is be interpreted
 * as meaning that the vertex doesn't have a color.
 */
import stdlib.*;

public class A5TestColoring {
	
	
	
		public static void dfsColor(A5Graph g) {
			
			DFS(g, 0);
		}

		
		private static void DFS(A5Graph g, int start){
			if ( g.getColor(start) != 0) return;

			for(int i = 1 ; i < g.V() ; i++)
			{	
				boolean ColorAvailable = true;

				for(int v : g.adj(start))
				{
					if(g.getColor(v) == i) 
						ColorAvailable = false;
				}
			
				if(ColorAvailable)
				{
			
				g.setColor(start, i); 
				break;
				}
			}

			if(g.V() == start) 
				return;
		
			for(int v : g.adj(start))
				DFS(g, v);
		}


	
	
		public static void bfsColor(A5Graph g) {

			Queue<Integer> queue = new Queue<Integer>();

			g.setColor(0, 1); 
			queue.enqueue(0);

			while (!queue.isEmpty())
			{
				int x = queue.dequeue();
				for (int v: g.adj(x))
			{
					
					if (g.getColor(v) == 0)
					{
						for(int i = 1 ; i < g.V() ; i++)
						{
							boolean coloravailable = true;

							for(int vv : g.adj(v))
							{
								if(g.getColor(vv) == i) 
									coloravailable = false;
							}

							if(coloravailable) 
							{
								g.setColor(v, i); 
								break;
							}
						}
						queue.enqueue(v);
					}
			}
		
			}
	
		}
	
	
	
	
	


	public static void main(String[] args) {
		StdOut.println("--- Coloring an even cycle ---");
		A5Graph evencycle = new A5Graph(new In("data/evencycle.txt"));
		dfsColor(evencycle);
		StdOut.println("-- After DFS coloring --");
		StdOut.println(evencycle);
		StdOut.println("Proper coloring? " + evencycle.hasProperColoring());
		StdOut.println();
		
		evencycle = new A5Graph(new In("data/evencycle.txt"));
		bfsColor(evencycle);
		StdOut.println("-- After BFS coloring --");
		StdOut.println(evencycle);
		StdOut.println("Proper coloring? " + evencycle.hasProperColoring());
		StdOut.println();
		
		StdOut.println("--- Coloring an odd cycle ---");
		A5Graph oddcycle = new A5Graph(new In("data/oddcycle.txt"));
		dfsColor(oddcycle);
		StdOut.println("-- After DFS coloring --");
		StdOut.println(oddcycle);
		StdOut.println("Proper coloring? " + oddcycle.hasProperColoring());
		StdOut.println();
		oddcycle = new A5Graph(new In("data/oddcycle.txt"));
		bfsColor(oddcycle);
		StdOut.println("-- After BFS coloring --");
		StdOut.println(oddcycle);
		StdOut.println("Proper coloring? " + oddcycle.hasProperColoring());
		StdOut.println();

		StdOut.println("--- Coloring the midwest ---");
		A5Graph midwest = new A5Graph(new In("data/midwest.txt"));
		dfsColor(midwest);
		StdOut.println("-- After DFS coloring --");
		StdOut.println(midwest);
		StdOut.println("Proper coloring? " + midwest.hasProperColoring());
		
		midwest = new A5Graph(new In("data/midwest.txt"));
		bfsColor(midwest);
		StdOut.println("-- After BFS coloring --");
		StdOut.println(midwest);
		StdOut.println("Proper coloring? " + midwest.hasProperColoring());
	}
}
