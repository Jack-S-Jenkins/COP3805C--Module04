
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.util.logging.*;

public class Guess2 {

	static Logger logger = Logger.getLogger(Guess2.class.getName());

	// YOU MAY ASSUME THE COMMENTS CORRECTLY DESCRIBE WHAT SHOULD HAPPEN.
	public static void main(String[] args) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));	
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		logger.setLevel(Level.FINE);
		FileHandler fh;

		// Stops error from being displayed in console window and only show up in the log file
		logger.setUseParentHandlers(false);

		try {  

			// This block configure the logger with handler and formatter  
			fh = new FileHandler("C:/Users/jack.jenkins/Desktop/logger.log");  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  
		
			// the following statement is used to log any messages  
			logger.info("Starting the Logger \n\n");  
		
		  } catch (SecurityException e) {  
			e.printStackTrace();  
		  } catch (IOException e) {  
			e.printStackTrace();  
		  }  

		//THESE DECLARATIONS ARE CORRECT.
		Random ranGen = new Random(8);	// seeded to make debugging easier
		final int sides = 6;			// number of sides for a die
		Scanner userInput = new Scanner(System.in);
		int userguess = -1;				// user's guess,  1 - 6 inclusive
		int rolled = -1;				// number rolled, 1 - 6 inclusive
		int computerPoints = 0;			// computer's score, 0 - 5 max
		int humanPoints = 0;			// human user's score, 0 - 5 max
		boolean rightGuess = false;		// flag for correct guess
		int numGuesses = 0;				// counts the number of guesses per round

		//MAKE SURE THE PROGRAM PLAYS BY THESE RULES!!!
		System.out.println("Welcome to the Guess Game!\n\n RULES:");
		System.out.println("1. We will play five rounds.");
		System.out.println("2. Each round you will guess the number rolled on a six-sided die.");
		System.out.println("3. If you guess the correct value in three or fewer tries\n" +
			"   then you score a point, otherwise I score a point.");
		System.out.println("4. Whoever has the most points after five rounds wins.");
		
		// play five rounds
		for (int r = 1; r <= 5; r++) {

			// roll the die to start the round
			System.out.println("\n\nROUND " + r);
			System.out.println("-------");
			rolled = ranGen.nextInt(sides+1);
			System.out.println("The computer has rolled the die.");
			System.out.println("You have three guesses.");

			// loop gives user up to three guesses
			rightGuess = false;
			numGuesses = 0;
			while ((numGuesses <= 2) && (rightGuess == false)) {

				// input & validation: must be in range 1 to 6 inclusive
				do {
					System.out.print("\nWhat is your guess [1-6]? ");

					userguess = userInput.nextInt();

					if ((userguess < 1 || userguess > 6)) {
						logger.warning("User put in an incorrect value of " + userguess + "\n");
						System.out.println("   Please enter a valid guess [1-6]!");
					}
				} while (userguess < 1 || userguess > 6); 

				// did the user guess right?
				if (rolled == userguess) {
					logger.info("User Guessed Correctly for Round " + r + "\n");
					System.out.println("   Correct!");
					rightGuess = true;
				} else {
					logger.info("User Guessed Incorrectly for ROUND " + r + "\n");
					System.out.println("   Incorrect guess.");
				}
				
				numGuesses++;
			}

			// if the user guessed right, they get a point
			// otherwise the computer gets a point
			if (rightGuess) {
				humanPoints++;
			} else {
				computerPoints++;
			}

			// display the answer and scores
			System.out.println("\n*** The correct answer was: " + rolled + " ***\n");
			System.out.println("Scores:");
			System.out.println("  You: \t\t" + humanPoints);
			System.out.println("  Computer: \t" + computerPoints);
			System.out.println("");
		}

		// tell the user if they won or lost
		if (computerPoints > humanPoints) {
			logger.info("*** User Lost! ***");
			System.out.println("*** You Lose! ***");
		} else {
			logger.info("*** User Won! ***");
			System.out.println("*** You Win! ***");
		}

		System.out.println("Thanks for playing the Guess Game!");
	} // end main

}  // end class Guess
