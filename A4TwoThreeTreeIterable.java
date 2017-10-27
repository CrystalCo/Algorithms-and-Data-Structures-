/* A4TwoThreeTree.java

 *
 * This is a straightforward and partial implementation of 2-3 trees, which are
 * binary search trees that stay balanced after insertions and deletions.
 * This code performs insertions and updates but not deletions.
 *
 * For background on 2-3 trees, please review the lecture material and the
 * presentation in section 3.3 of the textbook.
 *
 * This was written for Assignment 4 in CSC 403, Spring, 2016, by John Rogers.
 */

//Abdulaziz Alqulaysh
//Assignment4
package assignment4;

import java.util.LinkedList;


import stdlib.*;

public class A4TwoThreeTreeIterable<K extends Comparable<? super K>, V> implements Iterable<K> {
	private static final int maxChildren = 3;
	private Node<K,V> root;

	private static class Node<K extends Comparable<? super K>, V> {
		LinkedList<K> keys;
		LinkedList<V> values;
		LinkedList<Node<K,V>> children;

		// New nodes are always 2-nodes
		public Node(K key, V value) {
			keys = new LinkedList<K>();
			keys.add(key);
			values = new LinkedList<V>();
			values.add(value);
			children = new LinkedList<Node<K,V>>();
			children.add(null);
			children.add(null);
		}

		public boolean is4Node() {
			return children.size() > maxChildren;
		}

		public boolean isLeaf() {
			for (Node<K,V> child: children) {
				if (child != null) return false;
			}
			return true;
		}
	}
	
	public A4TwoThreeTreeIterable() {
		root = null;
	}

	public V get(K key) {
		return get(key, root);
	}

	public V get(K key, Node<K,V> node) {
		if (node == null) return null;
		for (int i = 0; i < node.keys.size(); i++) {
			int comparison = key.compareTo(node.keys.get(i));
			if (comparison == 0) {
				return node.values.get(i);
			}
			else if (comparison < 0) {
				return get(key, node.children.get(i));
			}
		}
		return get(key, node.children.getLast());
	}

	public void put(K key, V value) {
		if (root == null) {
			root = new Node<K,V>(key, value);
			return;
		}
		root = put(key, value, root);
		if (root.is4Node()) {
			root = split4Node(root);
		}
	}

	public Node<K,V> put(K key, V value, Node<K,V> node) {
		if (node.isLeaf()) {
			// New keys are only inserted into leaf nodes
			int i = 0;
			while (i < node.keys.size()) {
				int comparison = key.compareTo(node.keys.get(i));
				if (comparison == 0) {
					node.values.set(i, value);
					return node;
				}
				else if (comparison < 0) {
					break;
				}
				i++;
			}
			// i is an index to where the new key, value pair should go
			node.keys.add(i, key);
			node.values.add(i, value);
			node.children.add(null);
			return node;
		}

		// The node is not a leaf so we must continue
		// searching down the tree for the leaf where
		// this new key will go.
		int i = 0;
		while (i < node.keys.size()) {
			int comparison = key.compareTo(node.keys.get(i));
			if (comparison == 0) {
				node.values.set(i, value);
				return node;
			}
			else if (comparison < 0) {
				node.children.set(i, put(key, value, node.children.get(i)));
				break;
			}
			i++;
		}

		if (i == node.keys.size()) {
			node.children.set(i, put(key, value, node.children.get(i)));
		}

		// Upon returning from inserting the new value, each
		// node in the return path must check whether the child
		// at or below which the insertion took place has become
		// a 4-node.

		if (node.children.get(i).is4Node()) {
			// If so, the node must be split into three
			// 2-nodes, one a parent and the other two 
			// children.  A reference to the parent is returned
			// so that its value may be added to its parent.
			Node<K,V> newNode = split4Node(node.children.get(i));
			node.keys.add(i, newNode.keys.get(0));
			node.values.add(i, newNode.values.get(0));
			node.children.set(i, newNode.children.get(0));
			node.children.add(i+1, newNode.children.get(1));
		}

		return node;
	}

	// Splitting a 4-node means creating a new 2-node as
	// a parent to two new 2-nodes
	public Node<K,V> split4Node(Node<K,V> node) {
		assert node.is4Node();
		Node<K,V> newParent = new Node<K,V>(node.keys.get(1), node.values.get(1));
		Node<K,V> newLeft = new Node<K,V>(node.keys.get(0), node.values.get(0));
		Node<K,V> newRight = new Node<K,V>(node.keys.get(2), node.values.get(2));
		newLeft.children.set(0, node.children.get(0));
		newLeft.children.set(1, node.children.get(1));
		newRight.children.set(0, node.children.get(2));
		newRight.children.set(1, node.children.get(3));
		newParent.children.set(0, newLeft);
		newParent.children.set(1, newRight);
		return newParent;
	}

	public void print() {
		print(root, 0);
	}

	private void print(Node<K,V> node, int indent) {
		if (node == null) return;
		for (int i = 0; i < node.keys.size(); i++) {
			print(node.children.get(i), indent+1);
			for (int j = 0; j < indent; j++) StdOut.print("\t");
			StdOut.println(node.keys.get(i)+":"+node.values.get(i));
		}
		print(node.children.get(node.keys.size()), indent+1);
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
			buildKeyList(keyList, node.children.getFirst());
			keyList.addAll(node.keys);
			buildKeyList(keyList, node.children.getLast());
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
}
