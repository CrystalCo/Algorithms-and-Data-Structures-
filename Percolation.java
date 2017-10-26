package algs15.perc;
import stdlib.*;
import algs15.*;

//*******************************************************
//HW6 CSC402
//Professor name: Radha Jagadeesan
//Student name: Abdulaziz Alqulaysh
//********************************************************
// Uncomment the import statements above.
// You can test this using InteractivePercolationVisualizer and PercolationVisualizer
// All methods should make at most a constant number of calls to the UF data structure,
// except percolates(), which may make up to N calls to the UF data structure.
public class Percolation {
	
	// create N-by-N grid, with all sites blocked 
	
	private int N;
	private boolean[] grid;
	private UF uf;
	
	
	public Percolation(int N) {

		 this.N = N;
	     this.grid = new boolean[N*N];
	     this.uf = new WeightedUF(N*N + 2);
	     for(int i = 0; i < N*N; i++) {
	       this.grid[i] = false; 
	     }
	}
	
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j)
	{
	    int pos = (i * N) + j;
	     
	     if (!isOpen(i, j)) {
	       grid[pos] = true;
	       
	      
	       if (i > 0) { 
	         if (isOpen(i-1, j)) {
	           uf.union(pos + 1, pos-N + 1);
	         }
	       } else { 
	        uf.union(0, pos + 1);
	       }
	       
	      if (i < N-1) { 
	         if (isOpen(i+1, j)) {
	           uf.union(pos + 1, pos+N + 1);
	         }
	       } else { 
	        uf.union(N*N+1, pos+1);
	       }
	       
	      if (j > 0 && isOpen(i, j-1)) { 
	         uf.union(pos + 1, pos-1 + 1);
	       }
	       
	       if (j < N-1 && isOpen(i, j+1)) { 
	         uf.union(pos + 1, pos+1 + 1);
	       }
	     }
	}
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		 return !isFull(i, j);
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		return !grid[i*N+j];
	}
	// does the system percolate?
	public boolean percolates() {
		 return uf.connected(0,N*N+1);
	}
}
