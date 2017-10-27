package assignment2;
// Assignment2 CSC 403
// Student Name: Abdulaziz Alqulaysh.



import stdlib.*;



import algs13.Queue;
/* ***********************************************************************
 *
 *  A symbol table implemented with a binary search tree.
 *
 *
 *************************************************************************/
public class A2BST<K extends Comparable<? super K>, V> {
	private Node<K,V> root;             // root of BST
	private int height;
//	public int height ;		//sorting the height of that node. 
//	public int balance;	
	
	private static class Node<K extends Comparable<? super K>,V> {
		public final K key;       // sorted by key
		public V val;             // associated data
		public Node<K,V> left, right;  // left and right subtrees
		public int N;             // number of nodes in subtree
		public int height ;		//sorting the height of that node. 
		public int balance ;		//sorting the balance factor at that node.
		
		public Node(K key, V val, int N ) {
			this.key = key;
			this.val = val;
			this.N = N;
			
			
			
			
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node<K,V> x) {
		if (x == null) return 0;
		else return x.N;
	}

	/* *********************************************************************
	 *  Search BST for given key, and return associated value if found,
	 *  return null if not found
	 ***********************************************************************/
	// does there exist a key-value pair with given key?
	public boolean contains(K key) {
		return get(key) != null;
	}

	// return value associated with the given key, or null if no such key exists
	public V get(K key) { return get(root, key); }
	private V get(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/* *********************************************************************
	 *  Insert key-value pair into BST
	 *  If key already exists, update with new value
	 ***********************************************************************/
	public void put(K key, V val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node<K,V> put(Node<K,V> x, K key, V val) {
		if (x == null) return new Node<>(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0)
			x.left  = put(x.left,  key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/* *********************************************************************
	 *  Delete
	 ***********************************************************************/

	public void deleteMin() {
		if (isEmpty()) throw new Error("Symbol table underflow");
		root = deleteMin(root);
	}

	private Node<K,V> deleteMin(Node<K,V> x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMax() {
		if (isEmpty()) throw new Error("Symbol table underflow");
		root = deleteMax(root);
	}

	private Node<K,V> deleteMax(Node<K,V> x) {
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(K key) {
		root = delete(root, key);
	}

	private Node<K,V> delete(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Node<K,V> t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}


	/* *********************************************************************
	 *  Min and max
	 ***********************************************************************/
	public K min() {
		if (isEmpty()) return null;
		return min(root).key;
	}

	private Node<K,V> min(Node<K,V> x) {
		if (x.left == null) return x;
		else                return min(x.left);
	}

	public K max() {
		if (isEmpty()) return null;
		return max(root).key;
	}

	private Node<K,V> max(Node<K,V> x) {
		if (x.right == null) return x;
		else                 return max(x.right);
	}

	/* *********************************************************************
	 *  Range count and range search.
	 ***********************************************************************/
	public Iterable<K> keys() {
		Queue<K> q = new Queue<>();
		inOrder(root, q);
		return q;
	}

	private void inOrder(Node<K,V> x, Queue<K> q) {
		if (x == null) return;
		inOrder(x.left, q);
		q.enqueue(x.key);
		inOrder(x.right, q);
	}


	/* ***************************************************************************
	 *  Visualization
	 *****************************************************************************/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (K key: levelOrder())
			sb.append (key + " ");
		return sb.toString ();
	}

	// level order traversal
	public Iterable<K> levelOrder() {
		Queue<K> keys = new Queue<>();
		Queue<Node<K,V>> queue = new Queue<>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node<K,V> x = queue.dequeue();
			if (x == null) continue;
			keys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return keys;
	}

	public void drawTree() {
		if (root != null) {
			StdDraw.setPenColor (StdDraw.BLACK);
			StdDraw.setCanvasSize(1200,700);
			drawTree(root, .5, 1, .25, 0);
		}
	}
	private void drawTree (Node<K,V> n, double x, double y, double range, int depth) {
		int CUTOFF = 10;
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text (x-0.010, y, n.key.toString ());
	
		
		//////////////////////////////////////////////////////////
		StdDraw.text(x-0.004, y, ":");	
		String numberAsStringHeight = Integer.toString(n.height);
		String numberAsStringbalance = Integer.toString(n.balance);
		StdDraw.text(x+0.002, y,numberAsStringHeight);
		StdDraw.text(x+0.010, y, "/");
		StdDraw.text(x+0.020, y,numberAsStringbalance);
		//////////////////////////////////////////////////////////
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius (.007);
		if (n.left != null && depth != CUTOFF) {
			StdDraw.line (x-range, y-.08, x-.01, y-.01);
			drawTree (n.left, x-range, y-.1, range*.5, depth+1);
		}
		if (n.right != null && depth != CUTOFF) {
			StdDraw.line (x+range, y-.08, x+.01, y-.01);
			drawTree (n.right, x+range, y-.1, range*.5, depth+1);
		}
	}


	
	
	public int Height(Node<K,V> node){
	
		   if (node == null)
		   {
	        return -1;
		   }
	    return 1 + Math.max(Height(node.left), Height(node.right));
		}

	

	public int Height(){return Height(root);}
	
	
	    
	
	
	 private void setHeight () {
		
		 if (root == null)
			return;
		 
		 Node<K,V> node;
		 Queue<Node<K,V>> q = new Queue<>();
		 root.height = Height(root)  ;
		 q.enqueue(root);
		 while( q.size() > 0){
			node = q.dequeue();
			if (node.left != null){
				node.left.height = Height(node.left); 
				q.enqueue(node.left);
			}
			if (node.right != null){
				node.right.height =  Height(node.right);
				q.enqueue(node.right);
			}
			
		}
	}
		 
		 
	 
	 
	 
	 
	private void setBalance() {
		
		if (root == null)
			return;
		
		Queue<Node<K,V>> q = new Queue<>();
		q.enqueue(root);
		
		while (!q.isEmpty()) {
		Node<K,V> node = q.dequeue();
		if(node != null)
		{
		if (node.right != null && node.left != null)
		node.balance = node.left.height - node.right.height ;
		else if (node.right != null )
		node.balance = -1 - node.right.height;
		else if (node.left != null )
		node.balance = -1 - node.left.height;
		else
		continue;
		}
		if (node.left != null)
		q.enqueue(node.left);
		if (node.right != null)
		q.enqueue(node.right);
		}

				
		
	}
	
	/* ***************************************************************************
	 *  Test client
	 *****************************************************************************/
	public static void main(String[] args) {
		StdIn.fromString ("D F B G E A C");
		A2BST<String, Integer> st = new A2BST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		st.setHeight();
		st.setBalance();
		st.drawTree ();
		StdDraw.save("data/balancedtree.png");
		

		StdIn.fromString ("G A B C E F D H");
		st = new A2BST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		st.setHeight();
		st.setBalance();
		st.drawTree ();
		StdDraw.save("data/unbalancedtree.png");
	}
}
