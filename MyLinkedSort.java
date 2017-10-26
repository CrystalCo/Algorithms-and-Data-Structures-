package algs13;
import stdlib.*;


//HW 9 CSC402
//Professor name:Radha Jagadeesan
//Student name: Abdulaziz Alqulaysh
// PROBLEM 2.2.17
public class MyLinkedSort {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinkedSort () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    private static void print (String s, MyLinkedSort b) {
        StdOut.print (s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println ();
    }
    private static void print (String s, MyLinkedSort b, double i) {
        StdOut.print (s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println (": " + i);
    }

    public void append(Node last) 
    {
        first = last;
    }
    
  
    public Node MergeSort(Node firsthead) 
    {
        if (firsthead == null || firsthead.next == null)
            return firsthead;
        
        
        Node l = firsthead;
        Node r = firsthead.next;
        
       
        while ((r != null) && (r.next != null)) 
        {
        	firsthead = firsthead.next;
            r = (r.next).next;
        }
        r = firsthead.next;
        firsthead.next = null;
        return merge(MergeSort(l), MergeSort(r));
    }
 
    public Node merge(Node l, Node r) 
    {
        Node temp = new Node();
        Node Sechead = temp;
        Node m = Sechead;
        
        while ((l != null) && (r != null)) 
        {
            if (l.item <= r.item) 
            {
                m.next = l;
                m = l;
                l = l.next;
            }
            else 
            {
               m.next = r;
                m = r;
                r = r.next;
            }
        }
        m.next = (l == null) ? r : l;
        
        
        return Sechead.next;
    }

    
   
    private void sort(){ //TODO

    	
    	append(MergeSort(first));
    	
    	 }

    public static void main (String args[]) {
        int[] a1 = new int[20];
		for (int i = 0; i < a1.length; i++)
			a1[i] = i;
		StdRandom.shuffle (a1);
        MyLinkedSort b1 = new MyLinkedSort ();
        for (int i:a1) b1.add(i);
        MyLinkedSort.print("before sort",b1);
        b1.sort();
        MyLinkedSort.print("after sort",b1);
        int[] a2 = new int[200];
		for (int i = 0; i < a2.length; i++)
			a2[i] = i;
		StdRandom.shuffle (a2);
        MyLinkedSort b2 = new MyLinkedSort ();
        for (int i:a1) b2.add(i);
        MyLinkedSort.print("before sort",b2);
        b2.sort();
        MyLinkedSort.print("after sort",b2);
         
    }
}

