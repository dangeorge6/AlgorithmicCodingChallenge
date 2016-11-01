import java.util.*;

public class LIChallenge {
	final private static int LETTERS_IN_ALPHABET = 26;
	
	//Challenge 1
	public static String getMissingLetters(String input){
		if(input == null) return null;
		boolean[] lettersInString = new boolean[LETTERS_IN_ALPHABET];	//holds whether letter is in string or not
		int uniqueLetters = 0;	//store letters as we go so we can short circuit if we hit 26
		
		for(int i = 0; i < input.length() ; i++) { 
		    char c = input.charAt(i);
			if(Character.isLetter(c)){
				//alphabetic chars are stored in alphabetic order, so subtracting from 'a' will get a 0-25 index for our boolean array
				int offset = Character.toLowerCase(c) - 'a';
				if(!lettersInString[offset]){
					if(uniqueLetters == LETTERS_IN_ALPHABET){
						//we've been here 26 times, so it's a pangram. 
						//So even for very long string, in a majority of cases it will perform much better than the O(n) worst case. 
						return "";	
					}
					lettersInString[offset] = true;		//mark that letter is in string
					uniqueLetters++;
				}
			}
		}
		
		return getStringForLettersArray(lettersInString);
	}
	
	private static String getStringForLettersArray(boolean[] lettersInString) {
		StringBuilder sb = new StringBuilder();		//Strings are immutable in Java, using StringBuilder to save memory and dynamically build return string.
		for(int i=0; i<lettersInString.length;i++){
			if(!lettersInString[i]){
				//letter wasn't in string, so add it to the answer string
				char c = (char)(i + 'a');	//find the ASCII value and cast back to char
				sb.append(c);
			}
		}
		return sb.toString();
	}

	
	//Challenge 2
	public static String[] animate(int speed, String init){
		if(init == null) return null;
		if(speed == 0) throw new IllegalArgumentException("particles will stay in place forever at a speed of 0!");	
		
		int chamberLength = init.length();
		List<Integer> leftParticlePos = new ArrayList<Integer>();
		List<Integer> rightParticlePos = new ArrayList<Integer>();
		//populate left and right particle positions, and also get the max number of time units before all particles have left chamber
		int iterationsBeforeAllExit = initParticlePositions(init,speed,leftParticlePos,rightParticlePos);
		String[] returnArr = new String[iterationsBeforeAllExit+1];
			
		for(int t = 0; t<=iterationsBeforeAllExit;t++){
			String chamberStringRep = getParticlePositionsAtTime(chamberLength, speed, leftParticlePos,rightParticlePos, t);
			returnArr[t] = chamberStringRep;
		}

		return returnArr;
	}

	private static String getParticlePositionsAtTime(int chamberLength, int speed, List<Integer> leftParticlePos, List<Integer> rightParticlePos,
			int time) {
		char[] c = new char[chamberLength];
		Arrays.fill(c,'.');		//init return array with all .
		
		for(int i =0;i<leftParticlePos.size();i++){
			int leftPos = leftParticlePos.get(i) - (speed*time);
			if(leftPos >= 0) c[leftPos] = 'X';
		}
		
		for(int i =0;i<rightParticlePos.size();i++){
			int rightPos = rightParticlePos.get(i) + (speed*time);
			if(rightPos < chamberLength) c[rightPos] = 'X';
		} 
		
		return new String(c);
	}

	private static int initParticlePositions(String init, int speed, List<Integer> leftParticlePos,
			List<Integer> rightParticlePos) {
		//create left and right lists for particles and their initial index in chamber
		//might as well calculate the number of time units until all exit the chamber while we're iterating. 
		//Goal here is to minimize how many times we have to iterate through chamber
		int chamberLength = init.length();
		int IterationsForAllToExit = 0;
		for(int i = 0; i<init.length();i++){
			char c = init.charAt(i);
			double iterationsToParticleExit = 0;
			if(c == 'R'){
				iterationsToParticleExit = Math.ceil((double)(chamberLength -i)/(double)speed);
				rightParticlePos.add(i);
			} else if (c == 'L'){
				iterationsToParticleExit = (double)(i+1)/(double)speed;
				leftParticlePos.add(i);
			} else if(c != '.'){
				throw new IllegalArgumentException();	//init String must have a bad character
			}
			IterationsForAllToExit = Math.max(IterationsForAllToExit, (int)iterationsToParticleExit);
		}
		return IterationsForAllToExit;
	}
	
}
