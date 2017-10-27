//Abdulaziz Alqulaysh
//Assignment4


package assignment4;
import java.util.*;
public class A4HashTable<K>{
	private static final int INIT_CAPACITY = 4;
	private int N;         	  // number of key-value pairs in the symbol table
	private int M;         	  // size of linear probing table
	private K[] keys;    	  // the keys
	boolean[] array ;
	
	
	// create an empty hash table - use 16 as default size
	public A4HashTable() {
		this(INIT_CAPACITY);
	}

	// create linear proving hash table of given capacity
	@SuppressWarnings("unchecked")
	public A4HashTable(int length) {
		M = length;
		keys = (K[])   new Object[M];
		array = new boolean[M];
		Arrays.fill(array, true);
	}

	// return the number of key-value pairs in the symbol table
	public int size() { return N; }

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// does a key-value pair with the given key exist in the symbol table?
	public boolean contains(K key) { 
		
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
		{
			if (keys[i] == null && array[i] == true)
				continue;
			else if (keys[i] == null && array[i] == false)
				continue;
			else if (keys[i].equals(key))
				return true;
		}
		return false;
	}

	
	// hash function for keys - returns value between 0 and M-1
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	
	public void put(K key) {
		
		if( contains(key)) return;
		
		
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
		
		}
		keys[i] = key;
	
		N++;
	}
	
	
	public void delete(K key) {
		if (!contains(key)) return;

		// find position i of key
		int i = 0;
		while (!key.equals(keys[i])) {
			i = (i + 1) % M;
		}

		// delete key and associated value
		keys[i] = null;
		array[i] = false;

		N--;

	
	}
	
	
		
	
	
}
