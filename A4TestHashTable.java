package assignment4;

import stdlib.*;

public class A4TestHashTable {

	public static void main(String[] args) {
		A4HashTable<String> states = new A4HashTable<String>(101);
		states.put("Alabama");
		states.put("Alaska");
		states.put("Arizona");
		states.put("Arkansas");
		states.put("California");
		states.put("Colorado");
		states.put("Connecticut");
		states.put("Delaware");
		states.put("Florida");
		states.put("Georgia");
		states.put("Hawaii");
		states.put("Idaho");
		states.put("Illinois");
		states.put("Indiana");
		states.put("Iowa");
		states.put("Kansas");
		states.put("Kentucky");
		states.put("Louisiana");
		states.put("Maine");
		states.put("Maryland");
		states.put("Massachusetts");
		states.put("Michigan");
		states.put("Minnesota");
		states.put("Mississippi");
		states.put("Missouri");
		states.put("Montana");
		states.put("Nebraska");
		states.put("Nevada");
		states.put("New Hampshire");
		states.put("New Jersey");
		states.put("New Mexico");
		states.put("New York");
		states.put("North Carolina");
		states.put("North Dakota");
		states.put("Ohio");
		states.put("Oklahoma");
		states.put("Oregon");
		states.put("Pennsylvania");
		states.put("Rhode Island");
		states.put("South Carolina");
		states.put("South Dakota");
		states.put("Tennessee");
		states.put("Texas");
		states.put("Utah");
		states.put("Vermont");
		states.put("Virginia");
		states.put("Washington");
		states.put("West Virginia");
		states.put("Wisconsin");
		states.put("Wyoming");
		
	
		
		StdOut.println("Number of entries in the hash table: " + states.size());
		
		String[] testStrings = {"Rhode Island", "Rode Island", "North Dakota", "north dakota", "Superior", "Alabama", "Virginie", "Alaska", "Hawaii", "Illinois"};

		for (String testString: testStrings) {
			StdOut.println(testString + " in the table? " + states.contains(testString));
		}
		
		
		
	
		
		
		StdOut.println("Removing Alaska");
		states.delete("Alaska");
		StdOut.println("Removing Hawaii");
		states.delete("Hawaii");
		StdOut.println("Removing Superior");
		states.delete("Superior");

		StdOut.println("Number of entries in the hash table: " + states.size());
	
		for (String testString: testStrings) {
			StdOut.println(testString + " in the table? " + states.contains(testString));
		}
		
	

		
		
		
	}
}
