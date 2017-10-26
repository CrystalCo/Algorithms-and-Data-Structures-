package algs24;

import stdlib.StdIn;
import stdlib.StdOut;

//*******************************************************
//HW8 CSC402
//Professor name: Radha Jagadeesan
//Student name: Abdulaziz Alqulaysh
//********************************************************
/**
 *  The <tt>PtrHeap</tt> class is the priorityQ class from Question 2.4.24.
 *  It represents a priority queue of generic keys.
 *  
 *  It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */


public class PtrHeap<K extends Comparable<? super K>> {
	 
	private K[] pq;                    // store items at indices 1 to N
	private int N;                       // number of items on priority queue
	private int MAXN;
	
	@SuppressWarnings("unchecked")
	/** Create an empty priority queue  */
	public PtrHeap(int initCapacity) { 
		
		MAXN = initCapacity;
		pq = (K[]) new Comparable[initCapacity + 1];
		N = 0;
	}

	/** Is the priority queue empty? */
	public boolean isEmpty() { return N == 0; }

	/** Is the priority queue full? */
	//public boolean isFull()  { return N == MAXN; }

	/** Return the number of items on the priority queue. */
	public int size() { return N; }

	/**
	 * Return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K max() {
		
	if (isEmpty()) throw new Error("Priority queue underflow");
	return pq[1];
		
	}

	/** Add a new key to the priority queue. */
	public void insert(K x) { 
		//if (isFull()) throw new Error("Priority queue overflow");
		pq[++N] = x;

		
	}

	/**
	 * Delete and return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K delMax() {
		if (N == 0) throw new Error("Priority queue underflow");
		K max = pq[1];
		pq[N+1] = null; 
		return max;
	}

	private void showHeap() {
	    // a method to print out the heap
		// useful for debugging
		for (int i = 1; i <= N; i++)
			StdOut.print (pq[i] + " ");
		StdOut.println ();
	}

	public static void main(String[] args) {
		PtrHeap<String> pq = new PtrHeap<>(100);
		StdIn.fromString("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print ("pq:  "); pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-")) StdOut.println("max: " + pq.delMax());
			else pq.insert(item);
		}
		StdOut.println("(" + pq.size() + " left on pq)");

	}

}

