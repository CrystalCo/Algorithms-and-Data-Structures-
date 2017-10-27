/*
 * Use this program to test your methods for assignment 3.  
 * You won't need to modify this.  Just run it.  
 */
package assignment3;
//Student Name : Abdulaziz Alqulaysh.
//Assignment 3.
import stdlib.StdOut;

public class A3UnitTest {

	
	public static void main(String[] args) {
		A3TwoThreeTreeCorrect<String, Integer> tree1 = new A3TwoThreeTreeCorrect<String, Integer>();
		tree1.put("A", 1);
		tree1.put("B", 2);
		tree1.put("C", 3);
		tree1.put("D", 4);
		tree1.put("E", 5);
		tree1.put("F", 6);
		tree1.put("G", 7);
		
//		tree1.print();
		
		StdOut.println("*** Results for correctly formed 2-3 tree ***");
		StdOut.println("All nodes 2-3: " + tree1.hasOnly23Nodes());
		StdOut.println("Tree has symmetric order: " + tree1.hasSymmetricOrder());
		StdOut.println("Has leaves at same depth: " + tree1.hasLeavesAtSameDepth());
		StdOut.println();

		A3TwoThreeTreeWrongOrder<String, Integer> tree2 = new A3TwoThreeTreeWrongOrder<String, Integer>();
		tree2.put("A", 1);
		tree2.put("B", 2);
		tree2.put("C", 3);
		tree2.put("D", 4);
		tree2.put("E", 5);
		tree2.put("F", 6);
		tree2.put("G", 7);
		
//		tree2.print();
		StdOut.println("*** Results for 2-3 tree with key order reversed ***");
		StdOut.println("All nodes 2-3: " + tree2.hasOnly23Nodes());
		StdOut.println("Tree has symmetric order: " + tree2.hasSymmetricOrder());
		StdOut.println("Has leaves at same depth: " + tree1.hasLeavesAtSameDepth());
		StdOut.println();
		
		A3TwoThreeTreeBSTPut<String, Integer> tree3 = new A3TwoThreeTreeBSTPut<String, Integer>();
		tree3.put("B", 1);
		tree3.put("A", 2);
		tree3.put("C", 3);
		tree3.put("D", 4);
		tree3.put("E", 5);
		tree3.put("F", 6);
		tree3.put("G", 7);
		
//		tree3.print();
		StdOut.println("*** Results for 2-3 tree with BST put ***");
		StdOut.println("All nodes 2-3: " + tree3.hasOnly23Nodes());
		StdOut.println("Tree has symmetric order: " + tree3.hasSymmetricOrder());
		StdOut.println("Has leaves at same depth: " + tree3.hasLeavesAtSameDepth());
		StdOut.println();

		A3TwoFourTree<String, Integer> tree4 = new A3TwoFourTree<String, Integer>();
		tree4.put("A", 1);
		tree4.put("B", 2);
		tree4.put("C", 3);
		tree4.put("D", 4);
		tree4.put("E", 5);
		tree4.put("F", 6);
		tree4.put("G", 7);
		tree4.put("H", 8);
		tree4.put("I", 9);
		
//		tree4.print();
		StdOut.println("*** Results for 2-4 tree ***");
		StdOut.println("All nodes 2-3: " + tree4.hasOnly23Nodes());
		StdOut.println("Tree has symmetric order: " + tree4.hasSymmetricOrder());
		StdOut.println("Has leaves at same depth: " + tree4.hasLeavesAtSameDepth());
		StdOut.println();
		
		
	
		
		
	}
}
