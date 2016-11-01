
public class LIChallenge {
	final static int LETTERS_IN_ALPHABET = 26;
	
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
		return null;
	}
	
}
