
// Student Name: Abdulaziz Alqulaysh

package assignment1;


import stdlib.StdIn;
import algs35.ST;

public class ComputeGPA {

	public static void main(String[] args) {
		
		
		
		String[] letters = { "A+", "A",  "A-", "B+", "B",  "B-", "C+", "C",  "C-", "D",  "F"  };
        double[] grades =  { 4.33, 4.00, 3.67, 3.33, 3.00, 2.67, 2.33, 2.00, 1.67, 1.00, 0.00 };
        
        // creates a symbol table and fills it with letters and grades.
        ST<String, Double> letterGrades = new ST<String, Double>();
        for (int i = 0; i < letters.length; i++) {
            letterGrades.put(letters[i], grades[i]);
        }
        
        StdIn.fromFile("data/grades.txt");
        
        // reads a sequence of letter grades from standard input (a text file), computes and prints the GPA.
        double sum = 0.0;
        int counter = 0;
        while (!StdIn.isEmpty()) {
            String grade = StdIn.readString();
            double temp = letterGrades.get(grade);
            sum = sum + temp;
            counter++;
        }
        double gpa = sum / counter;
        System.out.println("Your GPA is: " + gpa);
        
        
        
        
	
		

	}

}
