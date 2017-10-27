// Student Name: Abdulaziz Alqulaysh

package assignment1;

import stdlib.StdIn;
import algs35.ST;
import stdlib.StdAudio;


public class PlaySimpleSong {

	
	
	 public static void playTone(double frequency, double duration) {
			final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
			final double[] slices = new double[sliceCount+1];
			for (int i = 0; i <= sliceCount; i++) {
				slices[i] = Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
			}
			StdAudio.play(slices);
		}
	
	
	
	public static void main(String[] args) {

		
		
		 StdIn.fromFile("data/notes_frequencies.txt");
		
		
		 ST<String, String> TonesTable = new ST<String, String>();
		 while (!StdIn.isEmpty()) {
			 
			  String fisrttempS = StdIn.readString();
			  String fisrttempD = StdIn.readString();
			  TonesTable.put(fisrttempS , fisrttempD);
			
		 }
		 
		 
		 StdIn.fromFile("data/sample_simple_song.txt");

		 
		 while (!StdIn.isEmpty()) {
	            String tone = StdIn.readString();
	            String frequency = TonesTable.get(tone);
	            double aDoublefrequency = Double.parseDouble(frequency);
	            
	            double duration = StdIn.readDouble();
	           
	            PlaySimpleSong.playTone(aDoublefrequency, duration);
		 }
		 
		 
		 
		 
		

	}
}

