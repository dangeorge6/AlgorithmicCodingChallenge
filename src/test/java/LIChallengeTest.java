import static org.junit.Assert.*;

import org.junit.Test;

public class LIChallengeTest {

	//Tests for challenge 1
	@Test
	public void getMissingLettersShouldReturnNothingForPangram() {
		String input = "A quick brown fox jumps over the lazy dog";
		String output = LIChallenge.getMissingLetters(input);
		
		assertEquals("", output);
	}
	
	@Test
	public void getMissingLettersShouldReturnMissingLetters() {
		String input = "A slow yellow fox crawls under the proactive dog";
		String output = LIChallenge.getMissingLetters(input);
		
		assertEquals("bjkmqz", output);
		
		input = "Lions, and tigers, and bears, oh my!";
		output = LIChallenge.getMissingLetters(input);
		
		assertEquals("cfjkpquvwxz", output);		
	}
	
	@Test
	public void getMissingLettersShouldReturnAllLettersForEmptyString() {
		String input = "";
		String output = LIChallenge.getMissingLetters(input);
		
		assertEquals("abcdefghijklmnopqrstuvwxyz", output);		
	}
	
	//Tests for challenge 2
	@Test
	public void animateShouldReturnCorrectlyForSingleParticle() {
		String[] output = LIChallenge.animate(2, "..R....");
		String[] expectedOutput = 	{ "..X....",	//formatting vertically for clarity
				
									  "....X..",
				
			    					  "......X",
				
									  "......." };
		assertArrayEquals( expectedOutput, output );		
	}
	
	@Test
	public void animateShouldReturnCorrectlyForTwoParticlesPassingThroughEachOther() {
		String[] output = LIChallenge.animate(3, "RR..LRL");
		String[] expectedOutput = 	{ "XX..XXX",

									  ".X.XX..",
									
									  "X.....X",
									
									  "......." };
		assertArrayEquals( expectedOutput, output );		
	}
	
	@Test
	public void animateShouldReturnCorrectlyForManyParticlesPassingThroughEachOther() {
		String[] output = LIChallenge.animate(2, "LRLR.LRLR");
		String[] expectedOutput = 	{ "XXXX.XXXX",

									  "X..X.X..X",
									
									  ".X.X.X.X.",
									
									  ".X.....X.",
									
									  "........." };
		assertArrayEquals( expectedOutput, output );		
	}
	
	@Test
	public void animateShouldReturnCorrectlyForFastParticlesThatExitImmediately() {
		String[] output = LIChallenge.animate(10, "RLRLRLRLRL");
		String[] expectedOutput = 	{ "XXXXXXXXXX",

									  ".........." };
		assertArrayEquals( expectedOutput, output );		
	}
	
	@Test
	public void animateShouldReturnCorrectlyForNoParticles() {
		String[] output = LIChallenge.animate(1, "...");
		String[] expectedOutput = 	{ "..." };
		assertArrayEquals( expectedOutput, output );		
	}
	
	@Test
	public void animateShouldReturnCorrectlyForLargeNumberOfParticles() {
		String[] output = LIChallenge.animate(1, "LRRL.LR.LRR.R.LRRL.");
		String[] expectedOutput = 	{ "XXXX.XX.XXX.X.XXXX.",

									  "..XXX..X..XX.X..XX.",
									
								  	  ".X.XX.X.X..XX.XX.XX",
									
									  "X.X.XX...X.XXXXX..X",
									
									  ".X..XXX...X..XX.X..",
									
									  "X..X..XX.X.XX.XX.X.",
									
									  "..X....XX..XX..XX.X",
									
									  ".X.....XXXX..X..XX.",
									
									  "X.....X..XX...X..XX",
									
									  ".....X..X.XX...X..X",
									
									  "....X..X...XX...X..",
									
									  "...X..X.....XX...X.",
									
									  "..X..X.......XX...X",
									
									  ".X..X.........XX...",
									
									  "X..X...........XX..",
									
									  "..X.............XX.",
									
									  ".X...............XX",
									
									  "X.................X",
									
									  "..................." };
		assertArrayEquals( expectedOutput, output );		
	}

}
