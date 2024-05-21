package millionaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Azmaan, Yash
 */
public class Start {

    // Method to start the game
    public static void Start() throws SQLException {
        // welcoming the user 
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("*  Who Want's to be a Millionaire  *");
        System.out.println("*                                  *");
        System.out.println("************************************");
       
        try (Scanner scanner = new Scanner(System.in)) {
            // loop for the game
            while (true) {
                System.out.println("Enter 'x' to exit at any time");
                System.out.println("1) Start Game");
                System.out.println("2) Rules");
                System.out.println("3) Leaderboard");
                System.out.println("4) Feedback");
                System.out.println("Use The numbers to choose");
                
                String userInput = scanner.next();

                if ("1".equals(userInput)) {
                    try {
                        //if user decides to play the game
                        Game.Game();
                    } catch (IOException e) {
                        System.err.println("An error occurred while playing the game: " + e.getMessage());
                    }
                    
                    break;
                    // if user wants to see the rules
                } else if ("2".equals(userInput)) {
                    
                    //object for rules
                    Rules rules = new Rules();
                    try {
                        rules.init();
                        String gameRules = rules.getGameRules();
                        System.out.println("Game Rules:");
                        System.out.println(gameRules);
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + e.getMessage());
                    } catch (IOException e) {
                        System.err.println("IOException: " + e.getMessage());
                    }
                    // If the user chooses to view the leaderboard, call the displayLeaderboard
                    // method from the Leaderboard class
                } else if ("3".equals(userInput)) {
                    Leaderboard.displayLeaderboard();

                } else if ("4".equals(userInput)) {
                    scanner.nextLine();
                    System.out.println("Please input your feedback: \n Press enter to continue.\n");
                    String feedbackInput = scanner.nextLine();
                    try {
                        Feedback.feedback(feedbackInput);
                        System.out.println("Thanks for you feedback");
                    } catch (IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                    }
                } else if ("x".equalsIgnoreCase(userInput)) {
                    // If the user enters 'x', exit the method
                    System.out.println("Exiting game...");
                    break;

                } else {
                    // Handling invalid user input
                    System.out.println("Invalid Input!!!");
                }
            }

        }

    }
}
