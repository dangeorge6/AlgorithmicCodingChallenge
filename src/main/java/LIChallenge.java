import java.util.*;

public class LIChallenge {
	final private static int LETTERS_IN_ALPHABET = 26;
	
	//Challenge 1
	public static String getMissingLetters(String input){
		
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
		
		List<String> returnList = new ArrayList<String>(); //blank string list to store animations at each time
		int chamberLength = init.length();
		List<Integer> leftParticlePos = new ArrayList<Integer>();
		List<Integer> rightParticlePos = new ArrayList<Integer>();
		initParticlePositions(init,leftParticlePos,rightParticlePos);
		int[] particlesRemainingInChamber = {leftParticlePos.size() + rightParticlePos.size()};
		if(particlesRemainingInChamber[0] == 0) return new String[]{init};		//if there weren't any particles, just give back an array with original string
		
		for(int t = 0; particlesRemainingInChamber[0]>0;t++){
			String chamberStringRep = getParticlePositionsAtTime(chamberLength, speed, leftParticlePos,rightParticlePos, t, particlesRemainingInChamber);
			returnList.add(chamberStringRep);
		}
		
		//convert List to array since this is what the problem states as the return type
		String[] returnArr = new String[returnList.size()];
		returnArr = returnList.toArray(returnArr);
		return returnArr;
	}

	private static String getParticlePositionsAtTime(int chamberLength, int speed, List<Integer> leftParticlePos, List<Integer> rightParticlePos,
			int time, int[] particlesRemainingInChamber) {
		char[] c = new char[chamberLength];
		Arrays.fill(c,'.');		//fill return array with .
		int particlesInChamber = leftParticlePos.size() + rightParticlePos.size();
		for(int i =0;i<leftParticlePos.size();i++){
			int leftPos = leftParticlePos.get(i) - (speed*time);
			if(leftPos < 0){
				//exited chamber, decrement items in chamber
				particlesInChamber--;
			} else {
				c[leftPos] = 'X';
			}

		}
		for(int i =0;i<rightParticlePos.size();i++){
			int rightPos = rightParticlePos.get(i) + (speed*time);
			if(rightPos >= chamberLength){
				//exited chamber, decrement items in chamber
				particlesInChamber--;
			} else {
				c[rightPos] = 'X';
			}
		}
		//pass back to calling function how many are left in chamber. Using array to simulate pass by reference. 
		//This way the caller can get back particles left in chamber after each time in order to stop after all have left
		//I'm aiming to minimize runtime over all else, so I want to 
		particlesRemainingInChamber[0] = particlesInChamber;	 
		
		return new String(c);
	}

	private static void initParticlePositions(String init, List<Integer> leftParticlePos,
			List<Integer> rightParticlePos) {
		for(int i = 0; i<init.length();i++){
			char c = init.charAt(i);
			if(c == 'R'){
				rightParticlePos.add(i);
			} else if (c == 'L'){
				leftParticlePos.add(i);
			} else if(c != '.'){
				throw new IllegalArgumentException();	//init String must have a bad character
			}
		}
		
	}
	
}
