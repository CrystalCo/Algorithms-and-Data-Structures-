package assignment4;

import stdlib.*;

public class A4TestTwoThreeTreeIterable {

	public static void main(String[] args) {
		A4TwoThreeTreeIterable<Character, Integer> frequencies = new A4TwoThreeTreeIterable<Character, Integer>();
		String inputFilename = "data/tale.txt";
		StdIn.fromFile(inputFilename);
		String text = StdIn.readAll();
		for(int i = 0; i < text.length(); i++) {
			char character = Character.toLowerCase(text.charAt(i));
			if (Character.isAlphabetic(character)) {
				if (frequencies.get(character) == null) {
					frequencies.put(character, 0);
				}
				frequencies.put(character, frequencies.get(character)+1);
			}
		}
		
		StdOut.println("Letter frequency table for the book in " + inputFilename);
		for (Character c: frequencies) {
			StdOut.printf("%c\t%6d\n", c, frequencies.get(c));
		}

	}

}
