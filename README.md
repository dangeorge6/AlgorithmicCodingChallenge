Backend Coding Test

I'm using gradle as a build tool and jUnit tests to test my solutions for various inputs. Run 'gradle test' to run through the test cases.

I kept both solutions as static functions in src/main/java/LIChallenge.java. Tests are in src/test/java/LIChallengeTest.java.

Based on the instructions, I'm assuming that time efficiency is the number 1 priority, followed by memory consumption and, finally, code readability. There are times in these solutions where I sacrificed readability and elegance in order to improve function speed. There are also times where a more object oriented approach could have provided better code readability, but I preferred to keep memory overhead low and instead chose to go with data structures that would consume the smallest amount of memory.

Problem 1:
I used a length 26 boolean array to keep track of letters in the string. Boolean array as opposed to a standard HashMap will minimize memory consumption here. Unicode stores alphabetic chars consecutively, so you can use the int representation of char 'a' to find a 0-25 index for a given char. Loop through string, if the char isn't in our array, change the value to true for that char. Also keep track of how many unique chars have been found as you iterate. If we reach 26, it's a pangram and no need to continue. This way, for very long strings, we'll stop before iterating through the entire string in most cases. After finishing the string iteration, just loop through the boolean array and build a string of chars that are still marked false. Note, use a StringBuilder instead of string concatenation as you go through the boolean array to minimize memory usage.

O(n) worst case


Problem 2:
Here my goal was to iterate through the particles the least number of times to keep things time efficient. I could have modeled the Particle as a class for more code readability, but I wanted to use as little memory as possible. Particles are in two lists of integers for right and left particles. Each node represents a left or right particle and it's starting position in the chamber. Iterate through the input string to populate each lists. Use a little math to find the particle that will take the longest to exit, and how many time units that would be (don't forget to cast to floating point numbers for division). I chose to make this calculation in tandem with populating these lists instead of, say, creating a separate findTimeUnitsBeforeAllExit() function. Doing this would have meant another iteration through all the nodes and, while it would be easier code to read, time efficiency is my priority here. Iterate for each time unit needed for all particles to exit. Use a StringBuilder to build each string representation on the fly. Use a simple equation with the speed to determine where that particle will be at that time unit. Place an X in that place in the string.

O(chamberLength^2/speed) worst case. 

Worst case would be a chamber full with particles moving at a speed of 1. So from the math done in the problem, we know that the max time units we have to iterate through is about chamberLength/speed. For each time unit, we need to iterate through all the particles present. If the entire chamber is full of particles, particles present = chamberLength. Multiple chamberLength with chamberLength/speed.