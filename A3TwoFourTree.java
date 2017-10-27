/* A3TwoThreeTree.java
 *
 * This is a straightforward and partial implementation of 2-3 trees, which are
 * binary search trees that stay balanced after insertions and deletions.
 * This code performs insertions and updates but not deletions.
 *
 * For background on 2-3 trees, please review the lecture material and the
 * presentation in section 3.3 of the textbook.
 *
 * This was written for Assignment 3 in CSC 403, Spring, 2016, by John Rogers.
 */

package assignment3;
//Student Name : Abdulaziz Alqulaysh.
//Assignment 3.
import java.util.LinkedList;

import algs13.Queue;

import stdlib.*;

public class A3TwoFourTree<K extends Comparable<? super K>, V> {
	private int maxChildren;
	private Node root;

	private class Node {
		LinkedList<K> keys;
		LinkedList<V> values;
		LinkedList<Node> children;

		// New nodes are always 2-nodes
		public Node(K key, V value) {
			keys = new LinkedList<K>();
			keys.add(key);
			values = new LinkedList<V>();
			values.add(value);
			children = new LinkedList<Node>();
			children.add(null);
			children.add(null);
		}

		public boolean is4Node() {
			return children.size() > maxChildren;
		}

		public boolean isLeaf() {
			for (Node child: children) {
				if (child != null) return false;
			}
			return true;
		}
	}
	
	public A3TwoFourTree() {
		maxChildren = 4;
		root = null;
	}

	public V get(K key) {
		return get(key, root);
	}

	public V get(K key, Node node) {
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
			root = new Node(key, value);
			return;
		}
		root = put(key, value, root);
		if (root.is4Node()) {
			root = split4Node(root);
		}
	}

	public Node put(K key, V value, Node node) {
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
			Node newNode = split4Node(node.children.get(i));
			node.keys.add(i, newNode.keys.get(0));
			node.values.add(i, newNode.values.get(0));
			node.children.set(i, newNode.children.get(0));
			node.children.add(i+1, newNode.children.get(1));
		}

		return node;
	}

	// Splitting a 4-node means creating a new 2-node as
	// a parent to two new 2-nodes
	public Node split4Node(Node node) {
		assert node.is4Node();
		Node newParent = new Node(node.keys.get(1), node.values.get(1));
		Node newLeft = new Node(node.keys.get(0), node.values.get(0));
		Node newRight = new Node(node.keys.get(2), node.values.get(2));
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

	public void print(Node node, int indent) {
		if (node == null) return;
		for (int i = 0; i < node.keys.size(); i++) {
			print(node.children.get(i), indent+1);
			for (int j = 0; j < indent; j++) StdOut.print("\t");
			StdOut.println(node.keys.get(i)+":"+node.values.get(i));
		}
		print(node.children.get(node.keys.size()), indent+1);
	}

	
	private boolean hasOnly23NodesCondition = true;

	public void hasOnly23NodesMethod(Node node){
		
		if (node == null)
			return;
		
		if (node.children == null)
		{
			if (node.keys.size() > 2)
			hasOnly23NodesCondition = false;
		}
		
		else if (node.keys.size() == 1) 
		{
			hasOnly23NodesMethod(node.children.get(0));
			
				if (node.keys.size() > 2)
				hasOnly23NodesCondition = false;
				hasOnly23NodesMethod(node.children.get(1));
		}
		else if (node.keys.size() == 2) 
		{
			hasOnly23NodesMethod(node.children.get(0));
			if (node.keys.size() > 2)
			hasOnly23NodesCondition =false;
			hasOnly23NodesMethod(node.children.get(1));
			
			if (node.keys.size() > 2)
			hasOnly23NodesCondition =false;
			hasOnly23NodesMethod(node.children.get(2));
		}
		else 
			hasOnly23NodesCondition = false;
	}
	
	
		
	
	
	public LinkedList<K> TKeys = new LinkedList<K>();

	public void hasSymmetricOrderMethod(Node node)
	{
		if (node == null)
			return;
		
		
		if (node.children == null)
		{
			for(int i = 0 ; i < node.keys.size() ; i++)
			{
				TKeys.add(node.keys.get(i));
			}
		}
		else if (node.keys.size() == 1) 
		{
			hasSymmetricOrderMethod(node.children.get(0));
			for(int i = 0 ; i < node.keys.size() ; i++)
			{
				TKeys.add(node.keys.get(i));
			}
			hasSymmetricOrderMethod(node.children.get(1));
		}
		else if (node.keys.size() == 2) 
		{
			hasSymmetricOrderMethod(node.children.get(0));
			TKeys.add(node.keys.get(0));
			hasSymmetricOrderMethod(node.children.get(1));
			TKeys.add(node.keys.get(1));
			hasSymmetricOrderMethod(node.children.get(2));
		}
		else if (node.keys.size() > 2) 
			for(int i = 0 ; i < node.keys.size() ; i++)
			{
				TKeys.add(node.keys.get(i));
			}
	}
	
	
	
	
	
	public boolean hasLeavesAtSameDepthCondition = false; 
	
	public void hasLeavesAtSameDepthMethod(Node node) {
		
		
		
		 Queue<Node> q = new Queue<>();
		 
		 q.enqueue(root);
		 while( q.size() > 0){
			node = q.dequeue();
			if (node.children.getFirst() != null && node.children.getLast()!= null ){
				if ( node.children.getFirst().isLeaf() == true && node.children.getLast().isLeaf() == true){
					hasLeavesAtSameDepthCondition = true;
				}
			
				
				q.enqueue(node.children.getFirst());
				q.enqueue(node.children.getLast());
				}
			
		}
		 
	}		
	public boolean hasOnly23Nodes() {
		// *** Your code goes here.
		
		hasOnly23NodesMethod(root);
		return hasOnly23NodesCondition;
	
	}
	
	public boolean hasSymmetricOrder() {
		// *** Your code goes here.
		
		hasSymmetricOrderMethod(root);
		for(int i = 0 ; i < TKeys.size() ; i++)
		{
			for(int j= i+1 ; j < TKeys.size() ; j++)
			{
				if((TKeys.get(i).compareTo(TKeys.get(j))) > 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
	public boolean hasLeavesAtSameDepth() {
		// *** Your code goes here.
		hasLeavesAtSameDepthMethod(root);
		return hasLeavesAtSameDepthCondition;
	}
}