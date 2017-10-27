/*
 * This is an implementation of a binary search tree that
 * is iterable.  More specifically, the iterator returned 
 * iterates over the tree's keys in ascending order.
 * 
 *  It was written for CSC 403, Spring, 2016, by
 *  John Rogers.
 */
package assignment4;

import java.util.LinkedList;
import stdlib.*;

public class SimplerBSTIterable<K extends Comparable<? super K>, V> implements Iterable<K> {
	private Node<K,V> root;             // root of BST

	private static class Node<K extends Comparable<? super K>,V> {
		public K key;       // sorted by key
		public V val;             // associated data
		public Node<K,V> left, right;  // left and right subtrees

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}
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
		//assert check();
	}

	private Node<K,V> put(Node<K,V> x, K key, V val) {
		if (x == null) return new Node<>(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0)
			x.left  = put(x.left,  key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val   = val;
		return x;
	}

	/* *********************************************************************
	 *  Delete
	 ***********************************************************************/

	public void deleteMin() {
		if (root == null) throw new Error("Symbol table underflow");
		root = deleteMin(root);
		//assert check();
	}

	private Node<K,V> deleteMin(Node<K,V> x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		return x;
	}

	public void deleteMax() {
		if (root == null) throw new Error("Symbol table underflow");
		root = deleteMax(root);
		//assert check();
	}

	private Node<K,V> deleteMax(Node<K,V> x) {
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
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
			// Checking for leaf node.
			if (x.right == null && x.left == null){
				// This is a leaf node.
				return null;
			} else if (x.right == null) {
				// One child, to the left.
				return x.left;
			} else if (x.left == null) {
				// One child, to the right.
				return x.right;
			} else {
				// Node x has two children.
				Node<K,V> rightTreeMinNode = findMin(x.right);
				x.key = rightTreeMinNode.key;
				x.val = rightTreeMinNode.val;
				x.right = delete(x.right, rightTreeMinNode.key);
			}
		}
		return x;
	}
	
	private Node<K,V> findMin(Node<K,V> x) {
		if (x.left == null) return x;
		else return findMin(x.left);
	}

	public java.util.Iterator<K> iterator() {
		return new Iterator();
	}
	
	private class Iterator implements java.util.Iterator<K> {
		
		private LinkedList<K> keyList;
		private java.util.Iterator<K> keyListIterator;
		
		public Iterator() {
			keyList = new LinkedList<K>();
			buildKeyList(keyList);
			keyListIterator = keyList.iterator();
		}
		
		private void buildKeyList(LinkedList<K> keyList) {
			buildKeyList(keyList, root);
		}
		
		private void buildKeyList(LinkedList<K> keyList, Node<K,V> node) {
			if (node == null) return;
			buildKeyList(keyList, node.left);
			keyList.add(node.key);
			buildKeyList(keyList, node.right);
		}
		
		public boolean hasNext() {
			return keyListIterator.hasNext();
		}
		
		public K next() {
			return keyListIterator.next();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/* ***************************************************************************
	 *  Visualization
	 *****************************************************************************/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (K key: this)
			sb.append (key + " ");
		return sb.toString ();
	}

	/* ***************************************************************************
	 *  Test client
	 *****************************************************************************/
	public static void main(String[] args) {
		StdIn.fromFile ("data/tale.txt");

		SimplerBSTIterable<String, Integer> st = new SimplerBSTIterable<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		
		for (String key: st) {
			StdOut.println(key + ": " + st.get(key));
		}
	}
}
